package com.io.sports.brasileiraoapp.repository;

import com.io.sports.brasileiraoapp.domain.Competicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompeticaoRepository extends JpaRepository<Competicao, Long> {
}
