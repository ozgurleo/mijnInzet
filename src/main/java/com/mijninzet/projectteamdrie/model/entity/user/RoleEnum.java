package com.mijninzet.projectteamdrie.model.entity.user;

import com.mijninzet.projectteamdrie.model.entity.WebPage;

public enum RoleEnum {

    DEFAULT("UNKNOWN", null),

    TEACHER("TEACHER", WebPage.getWindowsTeacher()),

    ADMINISTRATOR("ADMIN", WebPage.getWindowsAdministrator()),

    //eigenaar onderwijseenheid is coordinator

    COORDINATOR("COORDINATOR", WebPage.getWindowsCoordinator()),

    SCHEDULER("SCHEDULAR", WebPage.getWindowsScheduler()),

    MANAGER("MANAGER", WebPage.getWindowsManager());    private String roleDescriptor;

    private WebPage[] webPages;    RoleEnum(String roleDescriptor, WebPage[] webPages) {

        this.roleDescriptor = roleDescriptor;

        this.webPages = webPages;

    }    public static RoleEnum[] getRoles() {

        RoleEnum[] roles = {TEACHER, ADMINISTRATOR, COORDINATOR, SCHEDULER, MANAGER};

        return roles;

    }    public static RoleEnum getRoleByDescription(String description) {

        switch (description) {

            case "Teacher":

                return TEACHER;

            case "Administrator":

                return ADMINISTRATOR;

            case "COORDINATOR":

                return COORDINATOR;

            case "Scheduler":

                return SCHEDULER;

            case "Manager":

                return MANAGER;

            default:

                System.err.println("Deze rol bestaat niet");

                return null;

        }

    }    // getters

    public String getRoleDescriptor() {

        return roleDescriptor;

    }    public WebPage[] getWebPages() {

        return webPages;

    }    @Override

    public String toString() {

        return String.format("Role: %s", roleDescriptor);

    }

}
