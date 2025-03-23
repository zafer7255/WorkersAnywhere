package com.workersAnywhere.WorkersAnywhere.RepositoryDAO;

import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {
    Customer findByUsername(String username);
}
