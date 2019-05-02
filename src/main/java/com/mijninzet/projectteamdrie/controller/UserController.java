package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.dao.AdministratorDAO;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private SessionFactory sessionFactory;
    private AdministratorDAO administratorDAO = new AdministratorDAO(sessionFactory);


}
