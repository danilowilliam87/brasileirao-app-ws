package com.io.sports.brasileiraoapp.repository;

import com.io.sports.brasileiraoapp.domain.InfoPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfoPartidaRepository extends JpaRepository<InfoPartida, Long> {

    @Query(value = "select i from info_partidas i where i.competicao.id = :idCompeticao " +
            " and i.numeroPartida >= :primeiroJogo " +
            " and i.numeroPartida <= :ultimoJogo")
    List<InfoPartida> buscarJogosPorRodada(@Param("idCompeticao") Long idCompeticao,
                                           @Param("primeiroJogo") Long primeiroJogo,
                                           @Param("ultimoJogo") Long ultimoJogo);


    @Query("SELECT i from info_partidas i WHERE i.dataPartida LIKE %:ano AND (i.timeMandante = :timeA or i.timeVisitante = :timeA) " +
            "AND ( i.timeMandante = :timeB OR i.timeVisitante = :timeB)")
    List<InfoPartida> findConfrontos(@Param("ano") String ano,
                                     @Param("timeA") String timeA,
                                     @Param("timeB") String timeB);




    @Query("select i from info_partidas i where i.competicao.id = :idCompeticao " +
            "AND (i.timeMandante = :timeA OR i.timeVisitante = :timeA) " +
            "AND (i.timeMandante = :timeB OR i.timeVisitante = :timeB)")
    List<InfoPartida> findConfrontosByCompeticao(@Param("idCompeticao") Long idCompeticao,
                                                 @Param("timeA") String timeA,
                                                 @Param("timeB") String timeB);


    @Query("select i from info_partidas i where i.competicao.id = :idCompeticao " +
            "and i.numeroPartida = :numeroPartida")
    Optional<InfoPartida> findPartidaByNumeroAndCompeticao(@Param("numeroPartida") Long numeroPartida,
                                                          @Param("idCompeticao") Long idCompeticao);

    @Query("select i from info_partidas i where i.competicao.id = :idCompeticao")
    List<InfoPartida> findAllByCompeticao(@Param("idCompeticao") Long idCompeticao);


}
