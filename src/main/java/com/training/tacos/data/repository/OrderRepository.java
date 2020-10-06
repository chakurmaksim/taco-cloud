package com.training.tacos.data.repository;

import com.training.tacos.data.model.Order;

public interface OrderRepository {

    Order save(Order order);
}
