package com.cjcaram.clients.repository;

import com.cjcaram.clients.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    List<Client> findByActiveTrue();

    Set<Client> findAllByIdIn(Set<Long> ids);
}
