package com.accounts.movements.service;

import com.accounts.movements.dto.MovementsView;
import com.accounts.movements.entity.Movements;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IMovementsService {

    Movements create(Movements movements);

    Movements update(Movements movements);

    List<Movements> findAll();

    List<MovementsView> findByDates(
            LocalDate startDate, LocalDate endDate
    );

    void delete(Long id);

}
