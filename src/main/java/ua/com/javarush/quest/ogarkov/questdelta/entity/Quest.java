package ua.com.javarush.quest.ogarkov.questdelta.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Quest extends AbstractEntity{
    Long id;
    String name;
    Long authorId;
    String text;
    String image;
    Long firstQuestionId;
    Collection<Question> questions;
}
