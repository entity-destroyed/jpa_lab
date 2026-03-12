package hu.bme.aut.jpa.jpa_lab.dao;

import hu.bme.aut.jpa.jpa_lab.entity.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDao {

    @PersistenceContext
    private EntityManager em;
    @Transactional
    public Company create(Company company) {
        em.persist(company);
        return company;
    }
    @Transactional
    public Company update(Company company) {
        return em.merge(company);
    }
    @Transactional
    public void deleteById(Long id) {
        Company company = em.find(Company.class, id);
        em.remove(company);
    }
    public Company findById(Long id) {
        return em.find(Company.class, id);
    }
    public List<Company> findAll() {
        return em.createQuery("select c from Company c",
                Company.class).getResultList();
    }
}
