package hu.bme.aut.jpa.jpa_lab.service;

import hu.bme.aut.jpa.jpa_lab.entity.Company;
import hu.bme.aut.jpa.jpa_lab.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getMinSalaryCompanies(int salary) {
        List<Company> companies = companyRepository.findCompaniesByEmployeeSalaryGreaterThanOrEqual(salary);

        return companies;
    }

    public List<Company> getCompaniesWithAtLeastEmployees(int minCount) {
        return companyRepository.findCompaniesWithAtLeastEmployees(minCount);
    }

    public List<Object[]> getAverageSalariesByPosition(Long companyId) {
        return companyRepository.findAverageSalariesByPosition(companyId);
    }
}
