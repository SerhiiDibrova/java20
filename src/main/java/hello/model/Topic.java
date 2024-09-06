package hello.model;

import java.util.Objects;

public class Topic {
    private String id;
    private String subjectName;
    private String subjectDescription;

    public Topic() {}

    public Topic(String id, String subjectName, String subjectDescription) {
        this.id = Objects.requireNonNull(id);
        this.subjectName = Objects.requireNonNull(subjectName);
        this.subjectDescription = Objects.requireNonNull(subjectDescription);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Objects.requireNonNull(id);
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = Objects.requireNonNull(subjectName);
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = Objects.requireNonNull(subjectDescription);
    }
}