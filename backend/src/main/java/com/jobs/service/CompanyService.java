package com.jobs.service;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jobs.domain.Company;
import com.jobs.domain.dto.Meta;
import com.jobs.domain.dto.ResultPaginationDTO;
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

    public Company handleGetCompanyById(Long id) {
        return this.companyRepository.findById(id).orElse(null);
    }

    public ResultPaginationDTO handleGetAllCompanies(Pageable pageable) {
        Page<Company> pageCompany = this.companyRepository.findAll(pageable);
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        Meta meta = new Meta();
        meta.setPage(pageable.getPageNumber());
        meta.setPageSize(pageable.getPageSize());

        meta.setPages(pageCompany.getTotalPages());
        meta.setTotal(pageCompany.getTotalElements());

        resultPaginationDTO.setMeta(meta);
        resultPaginationDTO.setResult(pageCompany.getContent());

        return resultPaginationDTO;

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
