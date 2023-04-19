package ru.junjavadev.dao;

import org.hibernate.SessionFactory;
import ru.junjavadev.domain.FilmText;

public class FilmTextDAO extends GenericDAO<FilmText> {
    public FilmTextDAO(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
