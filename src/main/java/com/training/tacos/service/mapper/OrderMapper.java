package com.training.tacos.service.mapper;

import com.training.tacos.data.model.Order;
import com.training.tacos.service.dto.OrderDto;
import com.training.tacos.data.model.Taco;
import com.training.tacos.service.dto.TacoDto;
import com.training.tacos.data.repository.TacoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private ModelMapper modelMapper;
    private TacoRepository tacoRepository;

    @Autowired
    public OrderMapper(ModelMapper modelMapper, TacoRepository tacoRepository) {
        this.modelMapper = modelMapper;
        this.tacoRepository = tacoRepository;
    }

    public Order convertToEntity(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        List<Taco> tacos = new ArrayList<>();
        tacoRepository.findAllById(orderDto.getTacos().stream().map(TacoDto::getId)
                .collect(Collectors.toList())).forEach(tacos::add);
        order.setTacos(tacos);
        return order;
    }

    public OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
}
