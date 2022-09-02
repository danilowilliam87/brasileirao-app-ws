package com.io.sports.brasileiraoapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@EqualsAndHashCode
@ToString
@Entity(name = "info_partidas")
public class InfoPartida {

    @Id
    private Long numeroPartida;
    @Column(name = "time_mandante", nullable = false)
    private String timeMandante;
    @Column(name = "time_visitante", nullable = false)
    private String timeVisitante;
    @Column(name = "local_partida", nullable = false)
    private String localDaPartida;
    @Column(name = "data_partida")
    private String dataPartida;
    @Column(name = "horario_partida", nullable = false)
    private String horarioPartida;
    @Column(name = "gols_mandante", nullable = false)
    private int golsMandante;
    @Column(name = "gols_visitante", nullable = false)
    private int golsVisitante;
    @ElementCollection
    @CollectionTable(name = "autor_gol_mandante",joinColumns = @JoinColumn(name = "numeropartida_id"))
    private List<String> autorGolMandate;
    @ElementCollection
    @CollectionTable(name = "autor_gol_visitante",joinColumns = @JoinColumn(name = "numeropartida_id"))
    private List<String> autorGolVisitante;
    @OneToOne
    private Competicao competicao;
}
