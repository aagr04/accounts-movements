package com.accounts.movements.consumer;

import com.accounts.movements.dto.EventMessageCustomer;
import com.accounts.movements.entity.Account;
import com.accounts.movements.service.IAccountService;
import com.accounts.movements.util.AccountType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class KafkaConsumerCustomerEventsService {

    private ObjectMapper mapper;

    private IAccountService iAccountService;

    public KafkaConsumerCustomerEventsService(
            ObjectMapper mapper, IAccountService iAccountService
    ){
        this.mapper = mapper;
        this.iAccountService = iAccountService;
    }

    @KafkaListener(topics = "customer-events", groupId = "group-local")
    public void customerEvents(String message) throws JsonProcessingException {
        System.out.println("RECEIVED_MESSAGE[" + message + "]");
        EventMessageCustomer eventMessage = mapper.readValue(message, EventMessageCustomer.class);

        switch (eventMessage.event()) {
            case "CREATE" -> {
                Account account = new Account();
                account.setIdCustomer(
                        eventMessage.idCustomer()
                );
                account.setAccountNumber(
                        eventMessage.idCustomer()
                );
                account.setAccountType(
                        AccountType.SAVING_ACCOUNT
                );
                account.setInitialBalance(
                        new BigDecimal("20.00")
                );
                account.setStatus(
                        true
                );
                iAccountService.create(account);
            }
            case "DELETE" -> {
                iAccountService.delete(eventMessage.idCustomer());
            }
            default -> {
                System.out.println("EVENT_UNRECOGNIZED[" + eventMessage.event() + "]");
            }
        }

    }

}
