package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.model.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByPhoneNumber(String phoneNumber);
}
