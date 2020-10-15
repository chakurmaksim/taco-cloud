package com.training.tacos.service;

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

    @Autowired
    public OrderService(OrderRepository orderRepo, OrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        Order newOrder = orderMapper.convertToEntity(orderDto);
        Order savedOrder = orderRepo.save(newOrder);
        return orderMapper.convertToDto(savedOrder);
    }
}
