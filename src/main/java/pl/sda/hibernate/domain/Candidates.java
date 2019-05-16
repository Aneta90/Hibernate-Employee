package pl.sda.hibernate.domain;

import org.hibernate.annotations.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@FilterDefs({
        @FilterDef(name = "byStatus", parameters = @ParamDef(name = "status", type = "string")),
        @FilterDef(name = "byExperience", parameters = @ParamDef(name = "experience", type = "long"))
})
@Filters({
        @Filter(name = "byStatus", condition = ":status = jobApplicationStatus"),
        @Filter(name = "byExperience", condition = ":experience >= previousExperienceInYears")
})
public class Candidates {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private String jobApplicationStatus;
    private long previousExperienceInYears;

    public Candidates() {
    }

    public Candidates(String name, String surname, String jobApplicationStatus, long previousExperienceInYears) {
        this.name = name;
        this.surname = surname;
        this.jobApplicationStatus = jobApplicationStatus;
        this.previousExperienceInYears = previousExperienceInYears;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJobApplicationStatus() {
        return jobApplicationStatus;
    }

    public void setJobApplicationStatus(String jobApplicationStatus) {
        this.jobApplicationStatus = jobApplicationStatus;
    }

    public long getPreviousExperienceInYears() {
        return previousExperienceInYears;
    }

    public void setPreviousExperienceInYears(long previousExperienceInYears) {
        this.previousExperienceInYears = previousExperienceInYears;
    }

    @Override
    public String toString() {
        return "Candidates{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", jobApplicationStatus='" + jobApplicationStatus + '\'' +
                ", previousExperienceInYears=" + previousExperienceInYears +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidates that = (Candidates) o;

        if (previousExperienceInYears != that.previousExperienceInYears) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        return jobApplicationStatus != null ? jobApplicationStatus.equals(that.jobApplicationStatus) : that.jobApplicationStatus == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (jobApplicationStatus != null ? jobApplicationStatus.hashCode() : 0);
        result = 31 * result + (int) (previousExperienceInYears ^ (previousExperienceInYears >>> 32));
        return result;
    }
}
