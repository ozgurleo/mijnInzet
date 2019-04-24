package com.miijninzet.mijninzetprojectteamdrie.model.entity;

public enum Window {
    CREATE_USER( "Create New User"),
    MANAGE_USER("Change User"),
    CREATE_TASKS("Create Tasks"),
    OVERVIEW_TASKS_TEACHER("Overview my Tasks "),
    OVERVIEW_TASKS_MANAGER("Overview all Tasks "),
    MANAGE_TASKS_TEACHER("Change My Tasks"),
    MANAGE_TASKS_MANAGER("Change tasks"),
    VIEW_CREATED_SCHEDULE("View Generated Schedule");

    private static final Window[] WINDOWS_TEACHER = {MANAGE_TASKS_TEACHER, OVERVIEW_TASKS_TEACHER};
    private static final Window[] WINDOWS_ADMINISTRATOR = {CREATE_USER, MANAGE_USER};
    private static final Window[] WINDOWS_COORDINATOR = {VIEW_CREATED_SCHEDULE};
    private static final Window[] WINDOWS_SCHEDULER = {VIEW_CREATED_SCHEDULE};
    private static final Window[] WINDOWS_MANAGER = {MANAGE_TASKS_MANAGER, OVERVIEW_TASKS_TEACHER};

    private String description;

    Window(String screenDescription) {
        this.description = screenDescription;
    }

    public static Window getWindowByDescription(String description) {

        if (description.equals(CREATE_USER.getDescription())) {
            return CREATE_USER;
        } else if (description.equals(MANAGE_USER.getDescription())) {
            return MANAGE_USER;
        } else if (description.equals(CREATE_TASKS.getDescription())) {
            return CREATE_TASKS;
        } else if (description.equals(OVERVIEW_TASKS_TEACHER.getDescription())) {
            return OVERVIEW_TASKS_TEACHER;
        } else if (description.equals(OVERVIEW_TASKS_MANAGER.getDescription())) {
            return OVERVIEW_TASKS_MANAGER;
        } else if (description.equals(MANAGE_TASKS_TEACHER.getDescription())) {
            return MANAGE_TASKS_TEACHER;
        } else if (description.equals(MANAGE_TASKS_MANAGER.getDescription())) {
            return MANAGE_TASKS_MANAGER;
        } else if (description.equals(VIEW_CREATED_SCHEDULE.getDescription())) {
            return VIEW_CREATED_SCHEDULE;
        } else {
            return null;
        }
    }

    public String getDescription() {
        return description;
    }

    public static Window[] getWindowsTeacher() {
        return WINDOWS_TEACHER;
    }

    public static Window[] getWindowsAdministrator() {
        return WINDOWS_ADMINISTRATOR;
    }

    public static Window[] getWindowsCoordinator() {
        return WINDOWS_COORDINATOR;
    }

    public static Window[] getWindowsScheduler() {
        return WINDOWS_SCHEDULER;
    }

    public static Window[] getWindowsManager() {
        return WINDOWS_MANAGER;
    }

    @Override
    public String toString() {
        return String.format("Scherm heeft functie %s", description);
    }
}


