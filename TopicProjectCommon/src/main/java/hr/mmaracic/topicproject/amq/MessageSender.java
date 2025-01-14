/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.mmaracic.topicproject.amq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
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
public class MessageSender {

    @NonNull
    private JmsTemplate jmsTemplate;

    @NonNull
    private ObjectMapper objectMapper;
    
    public void sendMessage(String destination, String groupId, Object message) {
        jmsTemplate.send(destination, (Session session) -> {
            try {
                String strMessage = objectMapper.writeValueAsString(message);
                TextMessage textMessage = session.createTextMessage(strMessage);
                textMessage.setJMSType(message.getClass().getName());
                textMessage.setStringProperty("JMSXGroupID", groupId);
                return textMessage;
            } catch (JsonProcessingException ex) {
                log.error("Could not parse message ", message.toString());
            }
            return null;
        });
    }
}
