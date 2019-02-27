package pl.sda.hibernate.pl.sda.hibernate.impl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DefaultAfterCompanyWasCreatedValidator.class})
@Documented
public @interface AfterCompanyWasCreated {

    String message() default "This employee could not be hired before the company was created!";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};


}
