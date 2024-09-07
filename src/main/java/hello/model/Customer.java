package hello.model;

import hello.model.UserType;

public record Customer(long id, String firstName, String lastName) {
    private UserType userType;

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

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}