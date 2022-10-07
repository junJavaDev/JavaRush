package ua.com.javarush.quest.ogarkov.questdelta.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.commons.lang3.StringUtils;
import ua.com.javarush.quest.ogarkov.questdelta.entity.*;
import ua.com.javarush.quest.ogarkov.questdelta.repository.AnswerRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestionRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public enum QuestService {

    INSTANCE;

    private final char KEY_WIN = '+';
    private final char KEY_LOSE = '-';
    private final char KEY_QUESTION = '#';
    private final char KEY_ANSWER = '>';
    private final String DELIMITERS = String.valueOf(new char[]{KEY_QUESTION, KEY_ANSWER});

    private final String questionPattern = "((?<=(^|\\n|\\r))(?=.*" + KEY_QUESTION + "))";
    private final String questionPartsPattern =
            "((?<=(^|\\n|\\r))(?=.*[" + DELIMITERS
                    + "]))|[" + DELIMITERS + "]";

    private final Repository<Quest> questRepository = QuestRepository.getInstance();
    private final Repository<Question> questionRepository = QuestionRepository.getInstance();
    private final Repository<Answer> answerRepository = AnswerRepository.getInstance();
    private final ImageService imageService = ImageService.INSTANCE;

    public Collection<Quest> find(Quest pattern) {
        return questRepository.find(pattern);
    }

    public Optional<Quest> get(long id) {
        return questRepository.get(id);
    }

    public Collection<Quest> getAll() {
        return questRepository.getAll();
    }

    public Collection<Quest> getAll(int pageNumber, int pageSize) {
        return questRepository.getAll(pageNumber, pageSize);
    }


    public Quest create(HttpServletRequest request) throws ServletException, IOException {
        Quest emptyQuest = createEmptyQuest(request);
        Quest filledQuest = fillQuest(request, emptyQuest);
        update(filledQuest);
        parseContent(request, filledQuest);
        parseTwine(request, filledQuest);
        return filledQuest;
    }

    public void update(HttpServletRequest req) throws ServletException, IOException {
        long id = ReqParser.getLong(req, "id");
        Optional<Quest> optQuest = get(id);
        if (optQuest.isPresent()) {
            Quest filledQuest = fillQuest(req, optQuest.get());
            update(filledQuest);
            String content = req.getParameter("content");
            parseContent(content, filledQuest);
        }
    }

    private void parseContent(HttpServletRequest req, Quest existQuest) {
        String content = req.getParameter("content");
        parseContent(content, existQuest);
    }

    private void parseContent(String content, Quest existQuest) {
        Map<String, Question> questionMap = new HashMap<>();
        Map<Answer, String> answerMap = new HashMap<>();
        List<Question> parsedQuestions = new ArrayList<>();

        if (content != null && !content.isEmpty()) {
            String[] questions = content.split(questionPattern);
            for (String questionContent : questions) {
                String[] questionParts = questionContent.split(questionPartsPattern);
                Map.Entry<String, GameState> questionKey = parseKey(questionParts[0]);
                String name = questionKey.getKey();
                Question question = Question.with()
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
                questionDelete(existQuest, firstQuestion);
                existQuest.setFirstQuestionId(parsedQuestions.get(0).getId());
            }
            for (Question parsedQuestion : parsedQuestions) {
                existQuest.getQuestions().add(parsedQuestion);
            }

        }
    }

    private void questionDelete(Quest quest, Question question) {
        for (Answer answer : question.getAnswers()) {
            answerRepository.delete(answer);
        }

        Collection<Answer> answers = answerRepository.find(Answer.with().nextQuestionId(question.getId()).build());
        for (Answer answer : answers) {
            Optional<Question> questionWithAnswer = questionRepository.get(answer.getQuestionId());
            questionWithAnswer.ifPresent(value -> value.getAnswers().remove(answer));
            answerRepository.delete(answer);
        }
        if (question.getImage() != null) {
            imageService.deleteImage(question.getImage());
        }
        quest.getQuestions().remove(question);
        questionRepository.delete(question);
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

    private String trimCommand(String text) {
        int lastCharIndex = text.length() - 1;
        char lastChar = text.charAt(lastCharIndex);
        return lastChar == KEY_WIN || lastChar == KEY_LOSE
                ? trimLast(text)
                : text;
    }

    private String trimLast(String text) {
        return text.substring(0, text.length() - 1);
    }

    public void create(Quest quest) {
        questRepository.create(quest);
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }

    public void delete(Quest quest) {
        questRepository.delete(quest);
    }

    private Quest createEmptyQuest(HttpServletRequest request) {
        User author = ReqParser.getUser(request).orElseThrow();
        Question firstQuestion = Question.with().gameState(GameState.PLAY).build();
        questionRepository.create(firstQuestion);
        Quest quest = Quest.with()
                .firstQuestionId(firstQuestion.getId())
                .authorId(author.getId())
                .build();
        quest.getQuestions().add(firstQuestion);
        create(quest);
        return quest;
    }

    private Quest fillQuest(HttpServletRequest request, Quest existQuest) throws ServletException, IOException {
        long id = existQuest.getId();
        Long authorId = existQuest.getAuthorId();
        List<Question> questions = existQuest.getQuestions();
        long firstQuestionId = existQuest.getFirstQuestionId();
        String image = existQuest.getImage();
        String name = request.getParameter("name");
        String text = request.getParameter("text");
        Part part = request.getPart("image");
        Quest quest = Quest.with()
                .id(id)
                .firstQuestionId(firstQuestionId)
                .authorId(authorId)
                .questions(questions)
                .image(image)
                .name(name)
                .text(text)
                .build();

        String newImage = "quests/" + id + "/quest_image" + ReqParser.getFileExtension(part.getSubmittedFileName());
        boolean isUploaded = imageService.uploadImage(newImage, part.getInputStream());
        if (isUploaded) {
            quest.setImage(newImage);
        }
        return quest;
    }

    private void parseTwine(HttpServletRequest request, Quest filledQuest) throws ServletException, IOException {

        Part data = request.getPart("twine");
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
            parseContent(formattedContent.toString(), filledQuest);
        }
    }

}
