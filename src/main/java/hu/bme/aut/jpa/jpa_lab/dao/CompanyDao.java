package hu.bme.aut.jpa.jpa_lab.dao;

import hu.bme.aut.jpa.jpa_lab.entity.Company;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDao extends AbstractDao<Company> {

    public CompanyDao() {
        super(Company.class);
    }

    @Transactional
    public Company findByIdWithEmployees(Long id) {
        Company company = em.find(Company.class, id);
        company.getEmployees().size();
        return company;
    }
}