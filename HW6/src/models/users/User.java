package models.users;

import java.security.spec.InvalidKeySpecException;

import models.roles.RoleBase;
import utils.securities.Hash;

/**
 * The User class.
 */
public abstract class User implements RoleBase {
    // The id of the user.
    private int id;

    // The name of the user.
    private String name;

    // The username of the user.
    private String username;

    // The hashed password of the user.
    private byte[] password; 

    // The role of the user.
    private int permission;

    /**
     * The constructor of User.
     * 
     * @param id The id of the user.
     * @param name The name of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param permission The permission of the user.
     */
    public User(int id, String name, String username, String password, int permission) {
        setId(id);
        setName(name);
        setUsername(username);
        setPassword(password);
        setPermission(permission);
    }

    // A abstract method to get formatted string of user information.
    public abstract String getInfoString();

    @Override
    public void setId(int paramInt) {
        this.id = paramInt;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setName(String paramString) {
        name = paramString;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setPassword(String paramString) {
        try {
            password = Hash.hash(paramString);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkPassword(String paramString) {
        try {
            return Hash.check(paramString, password);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setPermission(int paramInt) {
        permission = paramInt;
    }

    @Override
    public int getPermission() {
        return permission;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }
}
