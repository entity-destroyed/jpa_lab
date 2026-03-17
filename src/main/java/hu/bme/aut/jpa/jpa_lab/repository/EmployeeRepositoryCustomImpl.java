package hu.bme.aut.jpa.jpa_lab.repository;

import hu.bme.aut.jpa.jpa_lab.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void promoteJobTitle(Long id) {
        Employee employee = em.find(Employee.class, id);
        if (employee == null) {
            throw new IllegalArgumentException("Invalid employee id: " + id);
        }
        String newJob = employee.getJob();
        if (employee.getJob().startsWith("Assistant")) {
            newJob = employee.getJob().replaceFirst("^Assistant ", "");
            newJob = newJob.substring(0, 1).toUpperCase() + newJob.substring(1);
        } else if (!employee.getJob().startsWith("Senior") && !employee.getJob().startsWith("Lead")) {
            newJob = "Senior " + employee.getJob();
        } else if (employee.getJob().startsWith("Senior")) {
            newJob = employee.getJob().replaceFirst("^Senior", "Lead");
        } else {
            throw new IllegalArgumentException("Cannot promote job title");
        }
        employee.setJob(newJob);
    }
}
