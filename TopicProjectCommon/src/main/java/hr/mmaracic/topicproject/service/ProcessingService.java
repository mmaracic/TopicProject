/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.mmaracic.topicproject.service;

import hr.mmaracic.topicproject.model.AccountBalance;
import hr.mmaracic.topicproject.model.AccountEntry;
import hr.mmaracic.topicproject.repository.AccountEntryRepository;
import hr.mmaracic.topicproject.repository.AccountBalanceRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ProcessingService {

    @NonNull
    private AccountEntryRepository accountEntryRepository;

    @NonNull
    private AccountBalanceRepository accountBalanceRepository;

    @NonNull
    private GenerationService generationService;

    public void storeAccountEntry(AccountEntry accountEntry) {
        accountEntryRepository.save(accountEntry);
    }

    public void processAccountEntry(AccountEntry accountEntry) {
        Optional<AccountEntry> optEntry = accountEntryRepository.findById(accountEntry.getId());
        if (optEntry.isPresent()) {
            AccountEntry entry = optEntry.get();
            if (entry.getIsError()) {
                throw new IllegalStateException("Entry " + entry.getId() + " is an error one!");
            } else {
                entry.setProcessingTimestamp(OffsetDateTime.now());
                Optional<AccountBalance> optBalance = accountBalanceRepository.findByIdAndClosed(entry.getAccountBalance().getId(), false);
                if (optBalance.isEmpty()) {
                    optBalance = Optional.of(generationService.generateBalance(entry.getAccountId()));
                    accountBalanceRepository.save(optBalance.get());
                }
                AccountBalance accountBalance = optBalance.get();
                accountBalance.setBalance(accountBalance.getBalance() + entry.getAmount());
                accountBalance.getEntries().add(entry);
                entry.setProcessingTimestamp(OffsetDateTime.now());
                entry.setAccountBalance(accountBalance);
            }
        }
    }

    public void closeAccountBalance(OffsetDateTime closeTimestamp) {
        
    }

}
