package com.io.sports.brasileiraoapp.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@EqualsAndHashCode
@ToString
@Entity(name = "info_partidas")
public class InfoPartida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_partida")
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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "autor_gol_mandante",joinColumns = @JoinColumn(name = "numeropartida_id"))
    private Set<String> autorGolMandante;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "autor_gol_visitante",joinColumns = @JoinColumn(name = "numeropartida_id"))
    private Set<String> autorGolVisitante;
    @ManyToOne
    private Competicao competicao;
}
