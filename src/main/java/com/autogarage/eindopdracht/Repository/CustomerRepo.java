package com.autogarage.eindopdracht.Repository;

import com.autogarage.eindopdracht.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
