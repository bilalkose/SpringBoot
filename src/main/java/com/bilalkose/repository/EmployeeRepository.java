package com.bilalkose.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//JpaRepository>Crud Repository

@Repository
public interface EmployeeRepository extends CrudRepository {
}
