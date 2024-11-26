package com.oocl.springbootemployee.exception;

public class EmployeeAgeNotValidException extends RuntimeException {

    public static final String EMPLOYEE_AGE_NOT_VALID = "Employee age not valid";

    public EmployeeAgeNotValidException() {
        super(EMPLOYEE_AGE_NOT_VALID);
    }

}
