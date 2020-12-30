package com.training.tacos.jms;

import com.training.tacos.service.dto.OrderDto;

public interface OrderMessagingService {

    void sendOrder(OrderDto order, String status);
}
