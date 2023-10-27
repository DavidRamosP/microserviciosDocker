package com.abc.prestamos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer nroPrestamo;
  private BigDecimal montoDesembolso;
  private BigDecimal tea;
  private Integer nroCuotas;
  @Enumerated(EnumType.STRING)
  private Frecuencia frecuencia;
  public enum Frecuencia { MENSUAL, SEMANAL }
}