package com.bitrate.BitRate.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

// Futuras Implementações
@Embeddable
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class AvaliaCriticaId implements Serializable {

    private Long idCliente;

    private Long idCritica;
}