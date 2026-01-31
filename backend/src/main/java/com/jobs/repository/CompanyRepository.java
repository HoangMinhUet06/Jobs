package com.jobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobs.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
