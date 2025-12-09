package com.example.new_starter.repo;

import com.example.new_starter.model.entity.EmploymentPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentPackageRepository extends JpaRepository<EmploymentPackage, Long> {

    boolean existsByName(String packageName);

    boolean existsByNameAndIdNot(String packageName, Long id);
}
