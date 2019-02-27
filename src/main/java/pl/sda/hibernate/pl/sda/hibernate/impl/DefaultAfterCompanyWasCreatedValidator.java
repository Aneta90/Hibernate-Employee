package pl.sda.hibernate.pl.sda.hibernate.impl;

import pl.sda.hibernate.domain.EmployeeInfoValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DefaultAfterCompanyWasCreatedValidator implements ConstraintValidator<AfterCompanyWasCreated,EmployeeInfoValidator> {


    @Override
    public void initialize(AfterCompanyWasCreated constraintAnnotation) {}

    @Override
    public boolean isValid(EmployeeInfoValidator employeeValidated, ConstraintValidatorContext constraintValidatorContext) {
        return employeeValidated.getHiredDate().isAfter(LocalDate.of(2015,1,1));
    }
}
