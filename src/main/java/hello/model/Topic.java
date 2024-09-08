package hello.model;

import java.util.Objects;

public class Topic {
    private String id;
    private String subjectName;
    private String subjectDescription;

    public Topic() {}

    public Topic(String id, String subjectName, String subjectDescription) {
        this.id = id;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Topic) obj;
        return Objects.equals(this.id, that.id) &&
               Objects.equals(this.subjectName, that.subjectName) &&
               Objects.equals(this.subjectDescription, that.subjectDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectName, subjectDescription);
    }
}