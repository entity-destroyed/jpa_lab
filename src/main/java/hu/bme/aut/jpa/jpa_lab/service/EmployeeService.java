package hu.bme.aut.jpa.jpa_lab.service;

import hu.bme.aut.jpa.jpa_lab.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public void printByNameAndJob(String name, String job) {
        employeeRepository.findByNameContainingIgnoreCaseAndJobContainingIgnoreCase(name, job)
                .forEach(System.out::println);
    }

    @Transactional
    public void promoteById(Long id) {
        employeeRepository.promoteSalaryById(id);
        employeeRepository.promoteJobTitle(id);
    }
}
