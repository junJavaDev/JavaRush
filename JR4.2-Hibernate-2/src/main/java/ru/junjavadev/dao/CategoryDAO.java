package ru.junjavadev.dao;

import org.hibernate.SessionFactory;
import ru.junjavadev.domain.Category;

public class CategoryDAO extends GenericDAO<Category>{
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
