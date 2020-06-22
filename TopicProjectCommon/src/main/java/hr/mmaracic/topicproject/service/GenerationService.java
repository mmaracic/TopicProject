/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.mmaracic.topicproject.service;

import hr.mmaracic.topicproject.model.AccountBalance;
import hr.mmaracic.topicproject.model.AccountEntry;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mmaracic
 */
@Slf4j
@Service
@Transactional
public class GenerationService {
    
    public static long noAccounts  = 3l;

    public AccountEntry generateEntry() {
        AccountEntry accountEntry = new AccountEntry();
        long randomAmount = ThreadLocalRandom.current().nextLong(1l, 100l);
        accountEntry.setAmount(randomAmount);
        long randomAccountId = ThreadLocalRandom.current().nextLong(1, noAccounts);
        accountEntry.setAccountId(randomAccountId);
        boolean randomBoolean = ThreadLocalRandom.current().nextBoolean();
        accountEntry.setIsError(Boolean.FALSE);
        return accountEntry;
    }

    public AccountBalance generateBalance(Long accountId) {
        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setAccountId(accountId);
        accountBalance.setBalance(0l);
        accountBalance.setClosed(Boolean.FALSE);
        return accountBalance;
    }
}
