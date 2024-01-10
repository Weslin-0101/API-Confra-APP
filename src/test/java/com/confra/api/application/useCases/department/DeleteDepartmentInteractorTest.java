package com.confra.api.application.useCases.department;

import com.confra.api.application.gateways.department.DeleteDepartmentGateway;
import com.confra.api.exceptions.RequiredObjectsIsNullException;
import com.confra.api.mocks.MockDepartment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class DeleteDepartmentInteractorTest {
    @InjectMocks
    private DeleteDepartmentInteractor sut;
    @Mock
    private DeleteDepartmentGateway deleteDepartmentGateway;
    private MockDepartment mockDepartment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockDepartment = new MockDepartment();
        sut = new DeleteDepartmentInteractor(deleteDepartmentGateway);
    }

    @Test
    public void shouldDeleteADepartment() {
        var request = mockDepartment.mockDepartmentEntity();

        sut.deleteDepartment(request.getName());

        Mockito.verify(deleteDepartmentGateway).deleteDepartment(request.getName());
    }

    @Test
    public void shouldThrowResourceNotFoundExceptionIfDepartmentNameIsNull() {
        RequiredObjectsIsNullException exception = assertThrows(RequiredObjectsIsNullException.class,
                () -> sut.deleteDepartment(null));
        assertEquals("Nome do departamento é nulo ou vazio", exception.getMessage());
    }
}