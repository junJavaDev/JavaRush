package ru.junjavadev.dao;

import org.hibernate.SessionFactory;
import ru.junjavadev.domain.Inventory;

public class InventoryDAO extends GenericDAO<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
