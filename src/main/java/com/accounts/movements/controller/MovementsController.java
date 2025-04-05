package com.accounts.movements.controller;

import com.accounts.movements.dto.MovementsView;
import com.accounts.movements.entity.Movements;
import com.accounts.movements.service.IMovementsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovementsController {

    private IMovementsService iMovementsService;

    public MovementsController(IMovementsService iMovementsService){
        this.iMovementsService = iMovementsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Movements save(@RequestBody Movements movements){
        return iMovementsService.create(movements);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    private List<Movements> findAll(){
        return iMovementsService.findAll();
    }

    @GetMapping("/reportes")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private List<MovementsView> reports(
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta
    ){
        return iMovementsService.findByDates(
                fechaDesde, fechaHasta
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id){
        iMovementsService.delete(id);
    }

}
