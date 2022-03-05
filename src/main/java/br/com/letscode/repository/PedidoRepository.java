package br.com.letscode.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.letscode.entity.PedidoEntidade;

@Repository
public class PedidoRepository {

    private static List<PedidoEntidade> pedidos = new ArrayList<>();

    static {
        pedidos.addAll(Arrays.asList(new PedidoEntidade(1L, "Celular", BigDecimal.valueOf(799.0), LocalDate.now(), "iphone"),
            new PedidoEntidade(2L, "Computador", BigDecimal.valueOf(1200), LocalDate.now(), "Positivo"),
            new PedidoEntidade(3L, "Computador", BigDecimal.valueOf(1200), LocalDate.now(), "ASUS"),
            new PedidoEntidade(4L, "Computador", BigDecimal.valueOf(1200), LocalDate.now(), "IOS"),
            new PedidoEntidade(5L, "Cadeira gamer", BigDecimal.valueOf(1000), LocalDate.now(), "razr")));
    }

    public PedidoEntidade salvar(PedidoEntidade entidade) {

        entidade.setId(pedidos.get(pedidos.size() - 1).getId()+1);

        pedidos.add(entidade);

        return entidade;
    }

    public List<PedidoEntidade> getAll() {
        return pedidos;
    }

    public PedidoEntidade getPorId(Long id) {
        return pedidos.stream()
            .filter(pedido -> pedido.getId().equals(id))
            .findFirst().get();
    }

}
