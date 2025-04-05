package com.accounts.movements.repository;

import com.accounts.movements.dto.MovementsView;
import com.accounts.movements.entity.Movements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovementsRepository extends JpaRepository<Movements, Long> {

    @Query(value = """
        SELECT m.created_at::date as fecha,
               p."name" as cliente,
               ac.account_number as numero_cuenta,
               ac.account_type as tipo,
               ac.initial_balance as saldo_inicial,
               ac.status as estado,
               m.value as movimiento,
               m.current_balance as saldo_disponible
        FROM movements m
        JOIN account ac ON m.account_id = ac.id
        JOIN customer c ON ac.id_customer = c.id
        JOIN person p ON c.id = p.id
        WHERE m.created_at::date BETWEEN :startDate AND :endDate
        ORDER BY m.created_at::date ASC
        """, nativeQuery = true)
    List<MovementsView> findByDates(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query(value = """
        SELECT
          COALESCE((
            SELECT m.current_balance
            FROM movements m
            WHERE m.account_id = :accountId
            ORDER BY id DESC
            LIMIT 1
          ), 0) +
          COALESCE((
            SELECT initial_balance
            FROM account
            WHERE id = :accountId AND use_balance IS FALSE
          ), 0) AS saldo_actual
        """, nativeQuery = true)
    BigDecimal getCurrentBalance(
            @Param("accountId") Long accountId
    );

}
