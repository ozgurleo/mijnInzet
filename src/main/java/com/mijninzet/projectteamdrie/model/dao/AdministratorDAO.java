package com.mijninzet.projectteamdrie.model.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AdministratorDAO {
    private SessionFactory sessionFactory;
    private Configuration configuration = new Configuration();

    public AdministratorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
