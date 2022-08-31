package com.io.sports.brasileiraoapp.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartidaGoogleDTO implements Serializable {

    private String dataPartida;
    private String mandante;
    private String visitante;
    private String estadio;
    private String horarioPartida;
    private String urlLogoMandante;
    private String urlLogoVisistante;
}
