package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.listener;


import org.springframework.stereotype.Component;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PedidoServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper.PedidoEventMapper;

import io.awspring.cloud.sqs.annotation.SqsListener;


@Component
public class SqsPedidoAdapter {


    private final PedidoServicePort pedidoServicePort;
    private final PedidoEventMapper pedidoEventMapper;


    public SqsPedidoAdapter(PedidoServicePort pedidoServicePort, PedidoEventMapper pedidoEventMapper) {
        this.pedidoServicePort = pedidoServicePort;
        this.pedidoEventMapper = pedidoEventMapper;
    }


    @SqsListener("${queue.order-events}")
    public void listen(PedidoEventDTO dto) {
        System.out.println("Mensagem SQS Recebida: " + dto.toString());


        PedidoBO pedidoBO = pedidoEventMapper.toBO(dto);
        pedidoServicePort.criarPedido(pedidoBO);
        System.out.println("Pedido processado com sucesso pelo Domínio!");
    }
}
