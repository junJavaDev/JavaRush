package ru.junjavadev.dao;

import org.hibernate.SessionFactory;
import ru.junjavadev.domain.Country;

public class CountryDAO extends GenericDAO<Country> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
