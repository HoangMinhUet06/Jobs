package com.jobs.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jobs.domain.Company;
import com.jobs.service.CompanyService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/companies")
    public ResponseEntity<?> createCompany(@Valid @RequestBody Company company) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.companyService.handleCreateCompany(company));
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getCompany() {
        List<Company> companies = this.companyService.handleGetCompany();
        return ResponseEntity.ok(companies);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(@Valid @RequestBody Company requestCompany) {
        Company updatedCompany = this.companyService.handleUpdateCompany(requestCompany);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") Long id) {
        this.companyService.handleDeleteCompany(id);
        return ResponseEntity.ok(null);
    }
}
