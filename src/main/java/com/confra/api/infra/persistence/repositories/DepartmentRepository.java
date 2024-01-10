package com.confra.api.infra.persistence.repositories;

import com.confra.api.domain.DepartmentEntity;
import com.confra.api.infra.persistence.tables.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<DepartmentEntity> findByName(String name);
}
