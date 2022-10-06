package ua.com.javarush.quest.ogarkov.questdelta.service;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Temp {
    public static void main(String[] args) throws IOException {
        String dataStart = "<tw-passagedata";
        String dataEnd = "</tw-passagedata>";
        Path path = Path.of("D:/JavaRush quest.html");
        String html = Files.readString(path);
        int firstIndex = html.indexOf(dataStart);
        int lastIndex = html.lastIndexOf(dataEnd);
        String rawContent = html.substring(firstIndex, lastIndex);
        String[] rawQuestions = rawContent.split("(?="+dataStart+")");
        StringBuilder result = new StringBuilder();
        for (String question : rawQuestions) {
            String questionName = StringUtils.substringBetween(question, "name=\"", "\"");
            String[] answers = StringUtils.substringsBetween(question, "[[", "]]");
            String text = question
                    .replaceAll("</?tw-passagedata.*?>", "")
                    .replaceAll("\\[\\[.*?]]", "")
                    .trim();
            result.append(questionName).append('#').append(text).append('\n');
            if (answers != null) {
                for (String answer : answers) {
                    String[] answerNextQuestionName = answer.split("\\|");
                    String answerText = answerNextQuestionName[0];
                    String nextQuestion = answerNextQuestionName.length > 1
                            ? answerNextQuestionName[1]
                            : answerText;
                        result.append(nextQuestion).append('>').append(answerText).append('\n');
                }
            }
        }
    }
}
