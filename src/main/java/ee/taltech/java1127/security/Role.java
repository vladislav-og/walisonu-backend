package ee.taltech.java1127.security;

/**
 * to have roles for user table
 */
public enum Role {
    USER, ADMIN;

    public String toSpringRole(){
        return "ROLE_" + this.name();
    }

    public boolean isAdmin(){
        return this == ADMIN;
    }
}
