package hello.model;

import hello.model.UserType;

public record Customer(long id, String firstName, String lastName) {
    private UserType userType = null;

    @Override
    public String toString() {
        return """
                Customer{
                    id=%d,
                    firstName='%s',
                    lastName='%s'
                }
                """.formatted(id, firstName, lastName);
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setId(long id) {
        throw new UnsupportedOperationException("Cannot set id on an immutable record");
    }

    public void setFirstName(String firstName) {
        throw new UnsupportedOperationException("Cannot set firstName on an immutable record");
    }

    public void setLastName(String lastName) {
        throw new UnsupportedOperationException("Cannot set lastName on an immutable record");
    }
}