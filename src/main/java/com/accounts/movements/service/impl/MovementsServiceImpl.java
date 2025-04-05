package com.accounts.movements.service.impl;

import com.accounts.movements.dto.MessageExceptionDto;
import com.accounts.movements.dto.MovementsView;
import com.accounts.movements.entity.Account;
import com.accounts.movements.entity.Movements;
import com.accounts.movements.exception.UserManagementException;
import com.accounts.movements.repository.MovementsRepository;
import com.accounts.movements.service.IAccountService;
import com.accounts.movements.service.IMovementsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class MovementsServiceImpl implements IMovementsService {

    private MovementsRepository movementsRepository;
    private IAccountService iAccountService;

    public MovementsServiceImpl(
            MovementsRepository movementsRepository,
            IAccountService iAccountService
    ) {
        this.movementsRepository = movementsRepository;
        this.iAccountService = iAccountService;
    }

    public Movements create(Movements movements) {
        movements.setCreatedAt(
                OffsetDateTime.now()
        );

        BigDecimal previousBalance = movementsRepository.getCurrentBalance(
                movements.getAccountId()
        );

        System.out.println("previousBalance-> " + previousBalance);

        Account account = iAccountService.findById(
                movements.getAccountId()
        );

        if (!account.getUseBalance()) {
            Account accountUpdate = new Account();
            accountUpdate.setId(account.getId());
            accountUpdate.setUseBalance(true);
            accountUpdate.setInitialBalance(account.getInitialBalance());
            accountUpdate.setAccountType(account.getAccountType());
            accountUpdate.setAccountNumber(account.getAccountNumber());
            accountUpdate.setIdCustomer(account.getIdCustomer());

            iAccountService.update(
                    accountUpdate
            );
        }

        movements.setPreviousBalance(
                previousBalance
        );

        if (previousBalance.compareTo(movements.getValue()) >= 0) {
            movements.setCurrentBalance(
                    previousBalance.subtract(
                            movements.getValue()
                    )
            );
            return movementsRepository.save(movements);
        } else {
            throw new UserManagementException(
                    new MessageExceptionDto(
                            String.format("Saldo no disponible"),
                            HttpStatus.BAD_REQUEST
                    ), HttpStatus.BAD_REQUEST
            );
        }
    }

    public Movements update(Movements movements) {
        return movementsRepository.save(movements);
    }

    public List<Movements> findAll() {
        return movementsRepository.findAll();
    }

    public List<MovementsView> findByDates(
            LocalDate startDate, LocalDate endDate
    ) {
        return movementsRepository.findByDates(
                startDate,
                endDate
        );
    }

    public void delete(Long id) {
        movementsRepository.deleteById(id);
    }

}
