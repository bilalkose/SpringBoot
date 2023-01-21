package com.bilalkose.data.repository;

import com.bilalkose.data.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//JpaRepository>Crud Repository

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
