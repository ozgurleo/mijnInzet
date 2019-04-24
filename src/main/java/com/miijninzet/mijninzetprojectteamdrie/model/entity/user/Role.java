package com.miijninzet.mijninzetprojectteamdrie.model.entity.user;


import com.miijninzet.mijninzetprojectteamdrie.model.entity.Window;

public enum Role {
    DEFAULT("Unknown", null),
    TEACHER("Teacher", Window.getWindowsTeacher()),
    ADMINISTRATOR("Administrator", Window.getWindowsAdministrator()),
    //eigenaar onderwijseenheid is coordinator
    COORDINATOR("Coordinator", Window.getWindowsCoordinator()),
    SCHEDULER("Scheduler", Window.getWindowsScheduler()),
    MANAGER("Manager", Window.getWindowsManager());

    private String roleDescriptor;
    private Window[] windows;

    Role(String roleDescriptor, Window[] windows) {
        this.roleDescriptor = roleDescriptor;
        this.windows = windows;
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

    public Window[] getWindows() {
        return windows;
    }

    @Override
    public String toString() {
        return String.format("Role: %s", roleDescriptor);
    }
}

