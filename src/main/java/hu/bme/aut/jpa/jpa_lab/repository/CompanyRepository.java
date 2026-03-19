package hu.bme.aut.jpa.jpa_lab.repository;

import hu.bme.aut.jpa.jpa_lab.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT DISTINCT c FROM Company c JOIN c.employees e WHERE e.salary >= :salary")
    List<Company> findCompaniesByEmployeeSalaryGreaterThanOrEqual(int salary);

    @Query("SELECT c FROM Company c WHERE SIZE(c.employees) >= :minCount")
    List<Company> findCompaniesWithAtLeastEmployees(int minCount);

    @Query("SELECT e.job, AVG(e.salary) FROM Employee e WHERE e.company.id = :companyId GROUP BY e.job")
    List<Object[]> findAverageSalariesByPosition(Long companyId);

}