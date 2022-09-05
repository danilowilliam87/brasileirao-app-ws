package com.io.sports.brasileiraoapp.repository;

import com.io.sports.brasileiraoapp.domain.InfoPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoPartidaRepository extends JpaRepository<InfoPartida, Long> {

    @Query(value = "select i from info_partidas i where i.competicao.id = :idCompeticao " +
            " and i.numeroPartida >= :primeiroJogo " +
            " and i.numeroPartida <= :ultimoJogo")
    List<InfoPartida> buscarJogosPorRodada(@Param("idCompeticao") Long idCompeticao,
                                      @Param("primeiroJogo") Long primeiroJogo,
                                      @Param("ultimoJogo") Long ultimoJogo);


    @Query("select i from info_partidas i where i.dataPartida like %:ano " +
            "and i.timeMandante = :timeA " +
            "and i.timeVisitante = :timeB " +
            "or i.timeMandante = :timeB " +
            "and i.timeVisitante = :timeA")
    List<InfoPartida> findConfrontos(@Param("ano") String ano,
                                     @Param("timeA") String timeA,
                                     @Param("timeB") String timeB);


    @Query("select i from info_partidas i where i.competicao.id = :idCompeticao " +
            "and i.timeMandante = :timeA " +
            "and i.timeVisitante = :timeB " +
            "or i.timeMandante = :timeB " +
            "and i.timeVisitante = :timeA")
    List<InfoPartida> findConfrontosByCompeticao(@Param("idCompeticao") Long idCompeticao,
                                                 @Param("timeA") String timeA,
                                                 @Param("timeB") String timeB);

    /*

    @Query("select i from InfoPartida i where i.competicao.id :idCompeticao " +
            "and i.timeMandante :timeA " +
            "and i.timeVisitante :timeB " +
            "or i.timeMandante :timeB " +
            "and i.timeVisitante :timeA")
    List<InfoPartida> findConfrontosByCompeticaoAndAno(@Param("idCompeticao") Long idCompeticao,
                                                       @Param("timeA") String timeA,
                                                       @Param("timeB") String timeB);

    @Query("select i from InfoPartida i where i.competicao.id = :idCompeticao " +
            "and i.numeroPartida = :numeroPartida")
    InfoPartida findPartidabyNumeroAndCompeticao(@Param("numeroPartida") Long numeroPartida,
                                          @Param("idCompeticao") Long idCompeticao);
                                          */



}
