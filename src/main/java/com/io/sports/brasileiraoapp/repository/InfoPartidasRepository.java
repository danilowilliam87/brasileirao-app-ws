package com.io.sports.brasileiraoapp.repository;

import com.io.sports.brasileiraoapp.domain.InfoPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoPartidasRepository extends JpaRepository<InfoPartida, Long> {
}
