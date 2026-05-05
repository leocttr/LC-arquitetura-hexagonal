package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;


@Component
public class PedidoEventMapper {


    public PedidoBO toBO(PedidoEventDTO dto) {
        if (dto == null) {
            return null;
        }


        PedidoBO pedidoBO = new PedidoBO();
        pedidoBO.setCep(dto.getZipCode());


        PessoaBO pessoa = new PessoaBO();
        if (dto.getCustomerId() != null) {
            pessoa.setId(dto.getCustomerId().intValue());
        }
        pedidoBO.setPessoa(pessoa);


        if (dto.getOrderItems() != null) {
            List<PedidoProdutoBO> itensBO = new ArrayList<>();
           
            dto.getOrderItems().forEach(itemDTO -> {
                PedidoProdutoBO itemBO = new PedidoProdutoBO();


                ProdutoBO produto = new ProdutoBO();
                if (itemDTO.getSku() != null) {
                    produto.setId(itemDTO.getSku().intValue());
                }
                itemBO.setProduto(produto);


                if (itemDTO.getAmount() != null) {
                    itemBO.setQuantidade(itemDTO.getAmount());
                }


                itensBO.add(itemBO);
            });
           
            pedidoBO.setItens(itensBO);
        }


        return pedidoBO;
    }
}
