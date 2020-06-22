/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.mmaracic.topicproject.amq;

import javax.jms.Message;
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
public class MessageReceiver {

    @JmsListener(destination = "topicName", containerFactory = "jmsFactory")
    public void onMessage(Message message){
        log.info(message.toString());
    }
}
