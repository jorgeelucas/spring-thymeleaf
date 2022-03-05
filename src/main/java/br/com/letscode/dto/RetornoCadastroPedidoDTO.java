package br.com.letscode.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetornoCadastroPedidoDTO {
    private Long id;
    private String produto;
    private BigDecimal valor;
    private LocalDate dataEntrega;
    private String descricao;
}
