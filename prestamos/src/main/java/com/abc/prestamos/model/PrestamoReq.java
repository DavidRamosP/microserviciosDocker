package com.abc.prestamos.model;

import com.abc.prestamos.entity.Prestamo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
@Getter
@ToString
public class PrestamoReq {
  private Integer nroPrestamo;
  @NotNull(message = "El monto de desembolso es requerido")
  private BigDecimal montoDesembolso;
  @NotNull(message = "La tasa es requerida")
  private BigDecimal tea;
  @NotNull(message = "El nro. de cuotas es requerido")
  private Integer nroCuotas;
  @NotNull(message = "La frecuencia es requerida")
  private Prestamo.Frecuencia frecuencia;
}