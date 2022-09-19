package ua.com.javarush.quest.ogarkov.questdelta.repository;

import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;

import java.util.Collection;

public class QuestionRepository extends AbstractRepository<Question> {

    public static class QuestionRepositoryHolder {
        public static final QuestionRepository HOLDER_INSTANCE = new QuestionRepository();
    }

    public static QuestionRepository getInstance() {
        return QuestionRepository.QuestionRepositoryHolder.HOLDER_INSTANCE;
    }

    private QuestionRepository() {
    }
    
    
//    public QuestionRepository() {
//        map.put(1L, Question.with()
//                .id(1L).questId("user").password("qwerty")
//                .avatar("no_image").role(Role.USER).language(Language.EN).build());
//    }
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
