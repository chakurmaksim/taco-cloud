package com.training.tacos.jms;

import com.training.tacos.jms.message.OrderMessage;

public interface OrderReceiver {

    OrderMessage receiveOrder(OrderMessage message);
}
