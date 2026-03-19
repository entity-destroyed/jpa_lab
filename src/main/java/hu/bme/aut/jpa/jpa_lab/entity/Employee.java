package hu.bme.aut.jpa.jpa_lab.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "employee_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String job;
    private int salary;
    private LocalDateTime workStart;

    private String taxId;

    @ManyToOne
    private Company company;

    public Employee(Long id, String name, String job, int salary, LocalDateTime workStart, String taxId) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.workStart = workStart;
        this.taxId = taxId;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDateTime getWorkStart() {
        return workStart;
    }

    public void setWorkStart(LocalDateTime workStart) {
        this.workStart = workStart;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", salary=" + salary +
                ", workStart=" + workStart +
                '}';
    }
}
