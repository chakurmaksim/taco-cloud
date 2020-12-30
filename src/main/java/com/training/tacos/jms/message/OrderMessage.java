package com.training.tacos.jms.message;

import com.training.tacos.service.dto.OrderDto;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OrderMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Status status;
    private OrderDto order;
    private LocalDateTime dateTime;

    public enum Status {
        PROCESS, CANCEL, DONE
    }

    public OrderMessage(String statusDescription, OrderDto order, LocalDateTime dateTime) {
        this.status = Status.valueOf(statusDescription.toUpperCase());
        this.order = order;
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status.name();
    }

    public void setStatus(String statusDescription) {
        this.status = Status.valueOf(statusDescription.toUpperCase());;
    }
}
