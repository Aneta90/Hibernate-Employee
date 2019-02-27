package pl.sda.hibernate.domain;

import lombok.*;
import org.hibernate.annotations.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
}
