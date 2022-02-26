package br.com.letscode.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class PedidoEntidade {

    private Long id;
    private String produto;
    private BigDecimal valor;
    private LocalDate dataEntrega;
    private String descricao;

    public PedidoEntidade(Long id, String produto, BigDecimal valor,
                          LocalDate dataEntrega, String descricao) {
        this.id = id;
        this.produto = produto;
        this.valor = valor;
        this.dataEntrega = dataEntrega;
        this.descricao = descricao;
    }

    public PedidoEntidade() {
    }

}
