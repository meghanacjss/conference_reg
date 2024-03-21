package com.example.conference_reg.repository;

import com.example.conference_reg.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,String> {

    Owner findByUsernameAndPassword(String username, String password);

    Optional<Owner> findByName(String username);
}
