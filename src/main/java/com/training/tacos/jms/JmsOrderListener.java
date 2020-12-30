package com.training.tacos.jms;

import com.training.tacos.jms.message.OrderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsOrderListener {

    private KitchenUI kitchenUI;

    @Autowired
    public JmsOrderListener(KitchenUI kitchenUI) {
        this.kitchenUI = kitchenUI;
    }

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(OrderMessage message) {
        kitchenUI.displayOrder(message);
    }
}
