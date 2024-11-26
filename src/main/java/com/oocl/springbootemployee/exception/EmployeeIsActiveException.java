package com.oocl.springbootemployee.exception;

public class EmployeeIsActiveException extends RuntimeException {

    public static final String EMPLOYEE_STATUS_NOT_VALID = "Employee status not active";

    public EmployeeIsActiveException() {
        super(EMPLOYEE_STATUS_NOT_VALID);
    }

}
