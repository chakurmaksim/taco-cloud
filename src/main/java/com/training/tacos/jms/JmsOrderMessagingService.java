package com.training.tacos.jms;

import com.training.tacos.jms.message.OrderMessage;
import com.training.tacos.service.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

    private JmsTemplate jmsTemplate;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendOrder(OrderDto order, String status) {
        OrderMessage message = new OrderMessage(status, order, LocalDateTime.now(ZoneId.of("CET")));
        jmsTemplate.convertAndSend(message);
    }
}
