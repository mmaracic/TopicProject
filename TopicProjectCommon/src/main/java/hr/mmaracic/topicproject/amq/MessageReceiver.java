/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.mmaracic.topicproject.amq;

import hr.mmaracic.topicproject.model.AccountEntry;
import hr.mmaracic.topicproject.service.ProcessingService;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mmaracic
 */
@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class MessageReceiver {
    
    @NonNull
    private ProcessingService processingService;

    @JmsListener(destination = "#{topicName}", containerFactory = "jmsFactory")
    public void onMessage(TextMessage message) throws JMSException, ClassNotFoundException{
        String type = message.getJMSType();
        String groupId = message.getStringProperty("JMSXGroupID");
        AccountEntry accountEntry = (AccountEntry) message.getBody((Class<AccountEntry>)Class.forName(type));
        processingService.storeAccountEntry(accountEntry);
        log.info(message.toString());
    }
}
