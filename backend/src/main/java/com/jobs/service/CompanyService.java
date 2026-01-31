package com.jobs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jobs.domain.Company;
import com.jobs.repository.CompanyRepository;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company handleCreateCompany(Company company) {
        // Business logic to create a company
        return this.companyRepository.save(company);
    }

    public List<Company> handleGetCompany() {
        return this.companyRepository.findAll();
    }

    public Company handleUpdateCompany(Company company) {
        Optional<Company> companyOptional = this.companyRepository.findById(company.getId());
        if (companyOptional.isPresent()) {
            Company currentCompany = companyOptional.get();
            currentCompany.setLogo(company.getLogo());
            currentCompany.setName(company.getName());
            currentCompany.setAddress(company.getAddress());
            currentCompany.setDescription(company.getDescription());

            return this.companyRepository.save(currentCompany);
        }
        return null;
    }

    public void handleDeleteCompany(Long id) {
        this.companyRepository.deleteById(id);
    }
}
