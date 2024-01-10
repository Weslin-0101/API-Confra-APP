package com.confra.api.application.useCases.department;

import com.confra.api.application.gateways.department.CreateDepartmentGateway;
import com.confra.api.domain.DepartmentEntity;
import com.confra.api.mocks.MockDepartment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class CreateDepartmentInteractorTest {
    @InjectMocks
    private CreateDepartmentInteractor sut;
    @Mock
    private CreateDepartmentGateway createDepartmentGateway;
    private MockDepartment mockDepartment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new CreateDepartmentInteractor(createDepartmentGateway);
        mockDepartment = new MockDepartment();
    }

    @Test
    public void shouldCreateDepartment() {
        var request = mockDepartment.mockDepartmentEntity();
        DepartmentEntity expectedResult = new DepartmentEntity();

        Mockito.when(createDepartmentGateway.createDepartment(request)).thenReturn(expectedResult);

        DepartmentEntity result = sut.createDepartment(request);

        assertNotNull(result);
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getDtRegistration(), result.getDtRegistration());
        assertEquals(expectedResult.getDtRegistration(), result.getDtRegistration());
        assertEquals(expectedResult.getDtRegistration(), result.getDtRegistration());
    }
}
