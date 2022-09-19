package ua.com.javarush.quest.ogarkov.questdelta.repository;

import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import java.util.Collection;

public class QuestionRepository extends AbstractRepository<Question> {


//    @Override
//    public Collection<Question> find(Question pattern) {
//        return map.values().stream()
//                .filter(question -> isOk(pattern, question, Question::getId)
//                        && isOk(pattern, question, Question::getQuestId)
//                        && isOk(pattern, question, Question::getImage)
//                        && isOk(pattern, question, Question::getAnswers)
//                        && isOk(pattern, question, Question::getText))
//                .toList();
//    }


    @Override
    public Collection<Question> find(Question pattern) {
        return super.find(
                pattern,
                Question::getId,
                Question::getQuestId,
                Question::getImage,
                Question::getGameState,
                Question::getText
        );
    }
}
