package com.io.sports.brasileiraoapp.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Data
@EqualsAndHashCode
@ToString
@Entity(name = "competicao")
public class Competicao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Competicao(String nome){
        this.nome = nome;
    }
}
