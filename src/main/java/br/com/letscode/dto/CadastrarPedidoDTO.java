package br.com.letscode.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarPedidoDTO {
    private String produto;
    private BigDecimal valor;
    private String descricao;
    private String endereco;
    private String email;
}
