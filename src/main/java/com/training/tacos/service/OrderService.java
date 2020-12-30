package com.training.tacos.service;

import com.training.tacos.jms.OrderMessagingService;
import com.training.tacos.service.dto.OrderDto;
import com.training.tacos.data.model.Order;
import com.training.tacos.data.repository.OrderRepository;
import com.training.tacos.service.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepo;
    private OrderMapper orderMapper;
    private OrderMessagingService messagingService;
    private static final String PROCESS_STATUS = "process";

    @Autowired
    public OrderService(OrderRepository orderRepo, OrderMapper orderMapper, OrderMessagingService messagingService) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
        this.messagingService = messagingService;
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        Order newOrder = orderMapper.convertToEntity(orderDto);
        Order savedOrder = orderRepo.save(newOrder);
        messagingService.sendOrder(orderDto, PROCESS_STATUS);
        return orderMapper.convertToDto(savedOrder);
    }
}
