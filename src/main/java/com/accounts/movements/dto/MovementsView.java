package com.accounts.movements.dto;

import java.time.LocalDate;

public interface MovementsView {
    LocalDate getFecha();
    String getCliente();
    Long getNumeroCuenta();
    String getTipo();
    Double getSaldoInicial();
    Boolean getEstado();
    Double getMovimiento();
    Double getSaldoDisponible();
}
