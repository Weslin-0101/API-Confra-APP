package com.confra.api.infra.persistence.repositories;

import com.confra.api.infra.persistence.tables.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
