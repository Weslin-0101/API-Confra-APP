package com.confra.api.infra.gateways.departmentGateway;

import com.confra.api.domain.DepartmentEntity;
import com.confra.api.infra.persistence.repositories.DepartmentRepository;
import com.confra.api.infra.persistence.tables.Department;
import com.confra.api.mocks.MockDepartment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentRepositoryGatewayTest {
    @InjectMocks
    private DepartmentRepositoryGateway sut;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private DepartmentEntityMapper departmentEntityMapper;
    private MockDepartment mockDepartment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockDepartment = new MockDepartment();
        departmentEntityMapper = Mockito.mock(DepartmentEntityMapper.class);
        sut = new DepartmentRepositoryGateway(departmentRepository, departmentEntityMapper);
    }

    @Test
    public void shouldCreateDepartment() {
        DepartmentEntity request = mockDepartment.mockDepartmentEntity();
        Department departmentPersistence = departmentEntityMapper.toEntity(request);

        Mockito.when(departmentRepository.save(departmentPersistence)).thenReturn(departmentPersistence);
        Mockito.when(departmentEntityMapper.toDomainObject(departmentPersistence)).thenReturn(request);

        DepartmentEntity result = sut.createDepartment(request);

        assertNotNull(result);
        assertEquals(request.getName(), result.getName());
        assertEquals(request.getDescription(), result.getDescription());
        assertEquals(request.getSupervisor(), result.getSupervisor());
        assertEquals(request.getDtRegistration(), result.getDtRegistration());
    }
}