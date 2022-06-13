package models.roles;

public abstract interface RoleBase {
    public abstract void setId(int paramInt);

    public abstract int getId();

    public abstract void setName(String paramString);

    public abstract String getName(); 

    public abstract void setUsername(String paramString);

    public abstract String getUsername(); 

    public abstract void setPassword(String paramString);

    public abstract boolean checkPassword(String paramString);

    public abstract void setPermission(int paramInt);

    public abstract int getPermission();
}
