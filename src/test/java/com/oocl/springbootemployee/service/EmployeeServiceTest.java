package com.oocl.springbootemployee.service;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.IEmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    @Test
    void should_return_the_given_employees_when_getAllEmployees() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        when(mockedEmployeeRepository.getAll()).thenReturn(List.of(new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0)));
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        List<Employee> allEmployees = employeeService.getAllEmployees();

        //then
        assertEquals(1, allEmployees.size());
        assertEquals("Lucy", allEmployees.get(0).getName());
    }

    @Test
    void should_return_the_created_employee_when_create_given_a_employee() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        Employee lucy = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.addEmployee(any())).thenReturn(lucy);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee createdEmployee = employeeService.create(lucy);

        //then
        assertEquals("Lucy", createdEmployee.getName());
    }

    @Test
    void should_return_the_update_employee_when_update_given_a_employee() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        Employee lucy = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.updateEmployee(any(), any())).thenReturn(lucy);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee createdEmployee = employeeService.update(lucy.getId(), lucy);

        //then
        assertEquals("Lucy", createdEmployee.getName());
    }

    @Test
    void should_return_EmployeeAgeNotValidException_when_create_employee_given_age_6() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee kitty = new Employee(1, "Kitty", 6, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.addEmployee(any())).thenReturn(kitty);

        //then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.create(kitty));
        verify(mockedEmployeeRepository, never()).addEmployee(any());
    }

    @Test
    void should_return_EmployeeAgeNotValidException_when_create_employee_given_age_66() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee kitty = new Employee(1, "Kitty", 66, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.addEmployee(any())).thenReturn(kitty);

        //then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.create(kitty));
        verify(mockedEmployeeRepository, never()).addEmployee(any());
    }


}
