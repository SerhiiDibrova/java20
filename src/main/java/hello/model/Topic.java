package hello.model;

public record Topic(String id, String subjectName, String subjectDescription) {
    public Topic {
        // No additional logic
    }

    public static Topic of(String id, String subjectName, String subjectDescription) {
        return new Topic(id, subjectName, subjectDescription);
    }
}