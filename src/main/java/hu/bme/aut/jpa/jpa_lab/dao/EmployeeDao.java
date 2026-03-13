package hu.bme.aut.jpa.jpa_lab.dao;

import hu.bme.aut.jpa.jpa_lab.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

//todo ms sql servert konfigurálni!

@Repository
public class EmployeeDao extends AbstractDao<Employee>{

    public EmployeeDao() {
        super(Employee.class);
    }

    public List<Employee> search(String keyword, int lowerSalaryLimit, int upperSalaryLimit) {
        return em.createQuery("select e from Employee e where " +
                "lower(e.name) like LOWER(CONCAT('%', :keyword, '%')) and " +
                "e.salary between :lowerSalaryLimit and :upperSalaryLimit",  Employee.class)
            .setParameter("keyword", keyword)
            .setParameter("lowerSalaryLimit", lowerSalaryLimit)
            .setParameter("upperSalaryLimit", upperSalaryLimit)
            .getResultList();
    }
}

