package com.mijninzet.projectteamdrie.model.entity.user;


import com.mijninzet.projectteamdrie.model.entity.WebPage;

public enum Role {
    DEFAULT("Unknown", null),
    TEACHER("Teacher", WebPage.getWindowsTeacher()),
    ADMINISTRATOR("Administrator", WebPage.getWindowsAdministrator()),
    //eigenaar onderwijseenheid is coordinator
    COORDINATOR("Coordinator", WebPage.getWindowsCoordinator()),
    SCHEDULER("Scheduler", WebPage.getWindowsScheduler()),
    MANAGER("Manager", WebPage.getWindowsManager());

    private String roleDescriptor;
    private WebPage[] webPages;

    Role(String roleDescriptor, WebPage[] webPages) {
        this.roleDescriptor = roleDescriptor;
        this.webPages = webPages;
    }

    public static Role[] getRoles() {
         Role[] roles = {TEACHER, ADMINISTRATOR, COORDINATOR, SCHEDULER, MANAGER};
        return roles;
    }

    public static Role getRoleByDescription(String description) {
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
    }

    // getters
    public String getRoleDescriptor() {
        return roleDescriptor;
    }

    public WebPage[] getWebPages() {
        return webPages;
    }

    @Override
    public String toString() {
        return String.format("Role: %s", roleDescriptor);
    }
}

