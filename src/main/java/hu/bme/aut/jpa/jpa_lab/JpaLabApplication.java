package hu.bme.aut.jpa.jpa_lab;

import hu.bme.aut.jpa.jpa_lab.entity.Company;
import hu.bme.aut.jpa.jpa_lab.entity.Employee;
import hu.bme.aut.jpa.jpa_lab.repository.CompanyRepository;
import hu.bme.aut.jpa.jpa_lab.repository.EmployeeRepository;
import hu.bme.aut.jpa.jpa_lab.service.CompanyService;
import hu.bme.aut.jpa.jpa_lab.service.EmployeeService;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class JpaLabApplication implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void run(String @NonNull ... args) throws Exception {
        List<Employee> employeeList = Stream.of(
                new Employee(null, "Bármi Áron", "Assistant sales representative", 380_000, LocalDateTime.of(2026, 1, 1, 8, 0, 0), "ABCD1234"),
                new Employee(null, "Cserepes Virág", "HR advisor", 450_000, LocalDateTime.of(2025, 5, 1, 8, 0, 0), "AAA111"),
                new Employee(null, "Nemoda Buda", "Senior product manager", 850_000, LocalDateTime.of(2021, 10, 15, 8, 0, 0), "BBB222")
        ).toList();

        Company company1 = new Company(null, "Abc kft.");
        Company company2 = new Company(null, "Cow company");
        Company company3 = new Company(null, "Sheep company");

        employeeList.get(0).setCompany(company1);
        employeeList.get(0).setCompany(company2);
        employeeList.get(1).setCompany(company2);
        employeeList.get(2).setCompany(company3);

        company1.getEmployees().add(employeeList.get(0));
        company2.getEmployees().add(employeeList.get(1));
        company3.getEmployees().add(employeeList.get(2));

        companyRepository.saveAll(List.of(company1, company2, company3));

        System.out.println(employeeRepository.findAll());

        System.out.println(employeeRepository.findAll());
        System.out.println("Number of all employees: " + employeeRepository.count());

        List<Employee> sortedEmployees = employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        System.out.println(sortedEmployees);

        Page<Employee> pagedEmployees = employeeRepository.findAll(PageRequest.of(0, 3, Sort.by("job")));
        System.out.println("Total elements: " + pagedEmployees.getTotalElements());
        System.out.println("Total pages: " + pagedEmployees.getTotalPages());
        System.out.println("Is first page: " + pagedEmployees.isFirst());
        System.out.println("Elements: " + pagedEmployees.getContent());

        List<Employee> job = employeeRepository.findBySalaryGreaterThan(450_000, PageRequest.of(0, 3, Sort.by("job"))).getContent();

        System.out.println(job);

        System.out.println(employeeRepository.findByNameStartingWithIgnoreCase("nem"));
        System.out.println(employeeRepository.findByWorkStartBetween(
                LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.of(2022, 1, 1, 0, 0, 0)
        ));

        employeeService.printByNameAndJob("e", "ad");


        //4. feladat
//        employeeRepository.findAll().forEach(e -> employeeRepository.promoteJobTitle(e.getId()));
//
//        System.out.println(employeeRepository.findAll());

        //5. feladat
//        try {
//            employeeRepository.findAll().forEach(e -> employeeService.promoteById(e.getId()));
//            System.out.println(employeeRepository.findAll());
//        } catch (Exception e) {
//
//        }
        //7. feladat

        List<Company> minSalaryCompanies = companyService.getMinSalaryCompanies(400_000);

        System.out.println("7. feladat");
        System.out.println("Min salary companies: " + minSalaryCompanies);
        System.out.println("==========");
        System.out.println("Companies with at least 2 employees: " + companyService.getCompaniesWithAtLeastEmployees(2));
        System.out.println("==========");
        System.out.println("Average salaries by position: ");
        List<Object[]> averageSalariesByPosition = companyService.getAverageSalariesByPosition(company2.getId());
        averageSalariesByPosition.stream().map(o -> o[0] + " " + o[1]).forEach(System.out::println);
        System.out.println("==========");

        System.out.println(employeeRepository.findAll());

    }

    static void main(String[] args) {
        SpringApplication.run(JpaLabApplication.class, args);
    }
}
