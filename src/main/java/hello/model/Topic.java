package hello.model;

public record Topic(String id, String subjectName, String subjectDescription) {

    public Topic {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be empty or null");
        }
        if (subjectName == null || subjectName.isEmpty()) {
            throw new IllegalArgumentException("Subject name cannot be empty or null");
        }
        if (subjectDescription == null || subjectDescription.isEmpty()) {
            throw new IllegalArgumentException("Subject description cannot be empty or null");
        }
    }

}