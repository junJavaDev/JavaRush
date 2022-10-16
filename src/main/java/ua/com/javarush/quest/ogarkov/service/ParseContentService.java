package ua.com.javarush.quest.ogarkov.service;

import com.jcabi.aspects.Loggable;
import jakarta.servlet.http.Part;
import org.apache.commons.lang3.StringUtils;
import ua.com.javarush.quest.ogarkov.entity.Answer;
import ua.com.javarush.quest.ogarkov.entity.GameState;
import ua.com.javarush.quest.ogarkov.entity.Quest;
import ua.com.javarush.quest.ogarkov.entity.Question;
import ua.com.javarush.quest.ogarkov.repository.AnswerRepository;
import ua.com.javarush.quest.ogarkov.repository.QuestionRepository;
import ua.com.javarush.quest.ogarkov.repository.Repository;
import ua.com.javarush.quest.ogarkov.settings.Setting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Loggable(value = Loggable.DEBUG)
public enum ParseContentService {
    INSTANCE;
    private final Repository<Question> questionRepository = QuestionRepository.getInstance();
    private final Repository<Answer> answerRepository = AnswerRepository.getInstance();
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final Setting S = Setting.get();
    private final char KEY_LOSE = '-';
    private final char KEY_WIN = '+';
    private final char KEY_QUESTION = '#';
    private final char KEY_ANSWER = '>';
    private final String DELIMITERS = String.valueOf(new char[]{KEY_QUESTION, KEY_ANSWER});
    private final String questionPartsPattern =
            "((?<=(^|\\n|\\r))(?=.*[" + DELIMITERS
                    + "]))|[" + DELIMITERS + "]";

    public void parseContent(Quest existQuest, String content) {
        Map<String, Question> questionMap = new HashMap<>();
        Map<Answer, String> answerMap = new HashMap<>();
        List<Question> parsedQuestions = new ArrayList<>();

        if (content != null && !content.isEmpty()) {
            String questionPattern = "((?<=(^|\\n|\\r))(?=.*" + KEY_QUESTION + "))";
            String[] questions = content.split(questionPattern);
            for (String questionContent : questions) {
                String[] questionParts = questionContent.split(questionPartsPattern);
                Map.Entry<String, GameState> questionKey = parseKey(questionParts[0]);
                String name = questionKey.getKey();
                Question question = Question.with()
                        .questId(existQuest.getId())
                        .name(name)
                        .gameState(questionKey.getValue())
                        .text(questionParts[1].trim())
                        .build();
                questionRepository.create(question);
                questionMap.put(name, question);
                parsedQuestions.add(question);
                for (int i = 2; i < questionParts.length; i += 2) {
                    Answer answer = Answer.with()
                            .questionId(question.getId())
                            .text(questionParts[i + 1])
                            .build();
                    question.getAnswers().add(answer);
                    answerRepository.create(answer);
                    answerMap.put(answer, parseKey(questionParts[i]).getKey());
                }
            }
            for (Map.Entry<Answer, String> answerEntry : answerMap.entrySet()) {
                Answer answer = answerEntry.getKey();
                String key = answerEntry.getValue();
                long nextQuestionId = questionMap.get(key).getId();
                answer.setNextQuestionId(nextQuestionId);
            }
            if (existQuest.getQuestions().size() == 1) {
                Question firstQuestion = existQuest.getQuestions().get(0);
                questionService.delete(firstQuestion.getId());
                existQuest.setFirstQuestionId(parsedQuestions.get(0).getId());
            }
            for (Question parsedQuestion : parsedQuestions) {
                existQuest.getQuestions().add(parsedQuestion);
            }

        }
    }

    public void parseTwine(Quest filledQuest, Part data) throws IOException {
        InputStream inputStream = data.getInputStream();
        if (inputStream.available() > 0) {
            String dataStart = "<tw-passagedata";
            String dataEnd = "</tw-passagedata>";
            String html = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines().collect(Collectors.joining("\n"));
            int firstIndex = html.indexOf(dataStart);
            int lastIndex = html.lastIndexOf(dataEnd);
            String rawContent = html.substring(firstIndex, lastIndex);
            String[] rawQuestions = rawContent.split("(?=" + dataStart + ")");
            StringBuilder formattedContent = new StringBuilder();
            for (String question : rawQuestions) {
                String questionName = StringUtils.substringBetween(question, "name=\"", "\"");
                String[] answers = StringUtils.substringsBetween(question, "[[", "]]");
                String text = question
                        .replaceAll("</?tw-passagedata.*?>", "")
                        .replaceAll("\\[\\[.*?]]", "")
                        .trim();
                formattedContent.append(questionName).append('#').append(text).append('\n');
                if (answers != null) {
                    for (String answer : answers) {
                        String[] answerNextQuestionName = answer.split("\\|");
                        String answerText = trimCommand(answerNextQuestionName[0]);
                        String nextQuestion = answerNextQuestionName.length > 1
                                ? answerNextQuestionName[1]
                                : answerNextQuestionName[0];
                        formattedContent.append(nextQuestion).append('>').append(answerText).append('\n');
                    }
                }
            }
            parseContent(filledQuest, formattedContent.toString());
        }
    }

    private String trimCommand(String text) {
        int lastCharIndex = text.length() - 1;
        char lastChar = text.charAt(lastCharIndex);

        return lastChar == KEY_WIN || lastChar == KEY_LOSE
                ? trimLast(text)
                : text;
    }

    private Map.Entry<String, GameState> parseKey(String key) {
        String name = key;
        GameState gameState = GameState.PLAY;

        int lastCharIndex = key.length() - 1;
        char lastChar = key.charAt(lastCharIndex);

        if (lastChar == KEY_WIN) {
            name = trimLast(key);
            gameState = GameState.WIN;
        } else if (lastChar == KEY_LOSE) {
            name = trimLast(key);
            gameState = GameState.LOSE;
        }

        return Map.entry(name, gameState);
    }

    private String trimLast(String text) {
        return text.substring(0, text.length() - 1);
    }

}
