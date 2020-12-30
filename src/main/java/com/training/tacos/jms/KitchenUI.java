package com.training.tacos.jms;

import com.training.tacos.jms.message.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KitchenUI {

    public void displayOrder(OrderMessage message) {
        log.info("Received order: {}", message);
    }
}
