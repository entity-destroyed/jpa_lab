package hu.bme.aut.jpa.jpa_lab;

import hu.bme.aut.jpa.jpa_lab.dao.CompanyDao;
import hu.bme.aut.jpa.jpa_lab.dao.EmployeeDao;
import hu.bme.aut.jpa.jpa_lab.entity.Company;
import hu.bme.aut.jpa.jpa_lab.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class JpaLabApplication implements CommandLineRunner {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private EmployeeDao employeeDao;
    static void main(String[] args) {
        SpringApplication.run(JpaLabApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Employee employee =
                new Employee(null, "Cserepes Virág",
                        "HR advisor", 420_000,
                        LocalDateTime.of(2026, 1, 1,
                                8, 0, 0));
        Company company = new Company(null, "ACME Kft.");
        company.getEmployees().add(employee);
        companyDao.create(company);
        Company retrievedCompany = companyDao.findById(company.getId());
        System.out.println("Employees of company " + retrievedCompany.getName() + ":" +
                retrievedCompany.getEmployees());

    }
}

