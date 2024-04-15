package com.restApi.jpaRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restApi.entity.Employee;

@Repository
public interface jpaRepository extends JpaRepository<Employee, Integer>{

}
