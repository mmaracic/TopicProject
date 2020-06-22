/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.mmaracic.topicproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.mmaracic.topicproject.amq.MessageSender;
import hr.mmaracic.topicproject.model.AccountEntry;
import hr.mmaracic.topicproject.service.GenerationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
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
public class ScheduledGeneratorService {
    
    @NonNull
    private String topicName;
    
    @NonNull
    private ObjectMapper objectMapper;
    
    @NonNull
    private GenerationService generationService;
    
    @NonNull
    private MessageSender messageSender;
    
    @Scheduled(fixedRate = 1000)
    public void generateEntry() throws JsonProcessingException {
        AccountEntry accountEntry = generationService.generateEntry();
        String strMessage = objectMapper.writeValueAsString(accountEntry);
        messageSender.sendMessage(topicName, strMessage);
    } 
}
