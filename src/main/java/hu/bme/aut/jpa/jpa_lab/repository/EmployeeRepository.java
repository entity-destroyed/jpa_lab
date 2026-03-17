package hu.bme.aut.jpa.jpa_lab.repository;

import hu.bme.aut.jpa.jpa_lab.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {

    Page<Employee> findBySalaryGreaterThan(int minSalary, Pageable pageable);

    List<Employee> findByNameStartingWithIgnoreCase(String name);

    List<Employee> findByWorkStartBetween(LocalDateTime from, LocalDateTime to);

    Stream<Employee> findByNameContainingIgnoreCaseAndJobContainingIgnoreCase(String name, String job);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query("UPDATE Employee e SET e.salary = e.salary * 2 WHERE e.id = :id")
    void promoteSalaryById(Long id);
}
