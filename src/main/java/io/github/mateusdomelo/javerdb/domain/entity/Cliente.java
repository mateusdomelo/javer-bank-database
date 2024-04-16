package io.github.mateusdomelo.javerdb.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nome;

    @Column
    private String telefone;

    @Column
    private boolean correntista;

    @Column(precision = 12, scale = 2)
    @JsonProperty("saldo_cc")
    private BigDecimal saldoCc;
}
