package ru.junjavadev.dao;

import org.hibernate.SessionFactory;
import ru.junjavadev.domain.Language;

public class LanguageDAO extends GenericDAO<Language> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
