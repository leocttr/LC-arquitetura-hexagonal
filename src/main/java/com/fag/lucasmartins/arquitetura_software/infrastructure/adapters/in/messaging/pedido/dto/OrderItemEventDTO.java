package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto;


public class OrderItemEventDTO {
   
    private Long sku;
    private Integer amount;


    public Long getSku() {
        return sku;
    }


    public void setSku(Long sku) {
        this.sku = sku;
    }


    public Integer getAmount() {
        return amount;
    }


    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
