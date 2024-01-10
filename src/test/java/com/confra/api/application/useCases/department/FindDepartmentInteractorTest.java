package com.confra.api.application.useCases.department;

import com.confra.api.application.gateways.department.FindDepartmentGateway;
import com.confra.api.domain.DepartmentEntity;
import com.confra.api.exceptions.RequiredObjectsIsNullException;
import com.confra.api.mocks.MockDepartment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class FindDepartmentInteractorTest {
    @InjectMocks
    private FindDepartmentInteractor sut;
    @Mock
    private FindDepartmentGateway findDepartmentGateway;
    private MockDepartment mockDepartment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockDepartment = new MockDepartment();
        sut = new FindDepartmentInteractor(findDepartmentGateway);
    }

    @Test
    public void shouldFindDepartment() {
        var request = mockDepartment.mockDepartmentEntity();
        var expectedResult = mockDepartment.mockDepartmentEntity();

        Mockito.when(findDepartmentGateway.findDepartmentById(request.getName())).thenReturn(expectedResult);

        DepartmentEntity result = sut.findDepartment(request.getName());

        assertNotNull(result);
        assertEquals(expectedResult.getName(), request.getName());
    }

    @Test
    public void shouldThrowRequiredObjectsIsNullExceptionWhenNameIsNull() {
        RequiredObjectsIsNullException exception = assertThrows(RequiredObjectsIsNullException.class, () -> sut.findDepartment(null));
        assertEquals("Name is null", exception.getMessage());
    }
}