package com.confra.api.application.useCases.department;

import com.confra.api.application.gateways.department.UpdateDepartmentGateway;
import com.confra.api.domain.DepartmentEntity;
import com.confra.api.exceptions.RequiredObjectsIsNullException;
import com.confra.api.mocks.MockDepartment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UpdateDepartmentInteractorTest {
    @InjectMocks
    private UpdateDepartmentInteractor sut;
    @Mock
    private UpdateDepartmentGateway updateDepartmentGateway;
    private MockDepartment mockDepartment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockDepartment = new MockDepartment();
        sut = new UpdateDepartmentInteractor(updateDepartmentGateway);
    }

    @Test
    public void shouldUpdateDepartment() {
        DepartmentEntity department = mockDepartment.mockDepartmentEntity();
        DepartmentEntity newDepartment = new DepartmentEntity(
                "Departamento teste",
                "nova descrição",
                "supervisor teste",
                new Date()
        );

        Mockito.when(updateDepartmentGateway.updateDepartment(department.getName(), newDepartment)).thenReturn(newDepartment);

        var result = sut.updateDepartment(department.getName(), newDepartment);

        assertNotNull(result);
        assertEquals(newDepartment.getName(), result.getName());
        assertEquals(department.getName(), result.getName());
        assertNotEquals(department.getDescription(), result.getDescription());
    }

    @Test
    public void shouldThrowIfNameProvideIsNull() {
        RequiredObjectsIsNullException exception = assertThrows(RequiredObjectsIsNullException.class,
                () -> sut.updateDepartment(null, mockDepartment.mockDepartmentEntity()));
        assertEquals("Name is null", exception.getMessage());
    }
}