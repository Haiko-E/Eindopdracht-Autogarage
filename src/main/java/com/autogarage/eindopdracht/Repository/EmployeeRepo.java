package com.autogarage.eindopdracht.Repository;

import com.autogarage.eindopdracht.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
