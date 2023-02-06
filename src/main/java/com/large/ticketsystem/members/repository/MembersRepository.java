package com.large.ticketsystem.members.repository;

import com.large.ticketsystem.members.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembersRepository extends JpaRepository<Members, Long> {
    Optional<Members> findByUsername(String username);

    Optional<Members> findById(Long id);

}
