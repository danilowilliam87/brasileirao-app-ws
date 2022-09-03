package com.io.sports.brasileiraoapp.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Data
@EqualsAndHashCode
@ToString
@Entity(name = "competicaop")
public class Competicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Competicao(String nome){
        this.nome = nome;
    }
}
