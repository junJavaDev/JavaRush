package ua.com.javarush.quest.ogarkov.questdelta.repository;

import ua.com.javarush.quest.ogarkov.questdelta.entity.Answer;

import java.util.Collection;

public class AnswerRepository extends AbstractRepository<Answer> {
    @Override
    public Collection<Answer> find(Answer pattern) {
        return super.find(
                pattern,
                Answer::getId,
                Answer::getQuestionId,
                Answer::getCorrect,
                Answer::getText
        );
    }
}
