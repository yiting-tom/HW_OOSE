package models.users;

import models.roles.AdminRole;

/**
 * The Admin class.
 */
public class Admin extends User implements AdminRole {
    /**
     * The constructor of Admin.
     * the admin would have permission status of 1.
     * 
     * @param id The id of the admin.
     * @param name The name of the admin.
     * @param username The username of the admin.
     * @param password The password of the admin.
     */
    public Admin(int id, String name, String username, String password) {
        super(id, name, username, password, 1);
    }

    @Override
    public String getInfoString() {
        return "name: " + getName() +
               "\nusername: " + getUsername();
    }
}
