package hello.model;

public record Topic(String id, String subjectName, String subjectDescription) {
    public Topic {
        if (id == null || subjectName == null || subjectDescription == null) {
            throw new NullPointerException("All fields must be non-null");
        }
    }

    public static Topic of(String id, String subjectName, String subjectDescription) {
        return new Topic(id, subjectName, subjectDescription);
    }

    public static Topic of(String id, String subjectName) {
        return new Topic(id, subjectName, "");
    }

    public static Topic empty() {
        return new Topic("", "", "");
    }
}