package com.example.shopapi.repository;

import com.example.shopapi.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findByClientNameAndClientSurname(String name, String surname);

    @Query(value = "SELECT * FROM client c ORDER BY c.registration_date OFFSET :offset LIMIT :limit", nativeQuery = true)
    List<Client> findAllWithPagination(@Param("offset") int offset, @Param("limit") int limit);
}