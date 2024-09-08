package hello.model;

/**
 * Enum representing different types of users in the system.
 */
public enum UserType {
    /**
     * A regular user with limited privileges.
     */
    USER,
    
    /**
     * An administrator with elevated privileges.
     */
    ADMIN,
    
    /**
     * Represents all types of users, including both regular and admin users.
     */
    ALL
}