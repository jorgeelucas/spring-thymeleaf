package br.com.letscode.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.letscode.dto.CadastrarPedidoDTO;
import br.com.letscode.dto.RetornoPedidoDTO;
import br.com.letscode.entity.PedidoEntidade;
import br.com.letscode.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final EmailService emailService;

    public void cadastrarPedido(CadastrarPedidoDTO cadastrarPedidoDTO) {

        emailService.enviar(cadastrarPedidoDTO.getEmail());

        PedidoEntidade entidade = new PedidoEntidade();
        entidade.setProduto(cadastrarPedidoDTO.getProduto());
        entidade.setDescricao(cadastrarPedidoDTO.getDescricao());
        entidade.setValor(cadastrarPedidoDTO.getValor());

        LocalDate dataEntrega = calcularDataEntrega(cadastrarPedidoDTO.getEndereco());
        entidade.setDataEntrega(dataEntrega);

        repository.salvar(entidade);
    }

    public List<RetornoPedidoDTO> listarTodosOsPedidos() {

        List<PedidoEntidade> entidades = repository.getAll();

        List<RetornoPedidoDTO> listaRetorno = entidades.stream()
            .map(entidade -> fromEntidadeToRetornoPedidoDTO(entidade))
            .collect(Collectors.toList());

        return listaRetorno;
    }

    private LocalDate calcularDataEntrega(String estado) {
        Map<String, Integer> frete = new HashMap<>();
        frete.put("SP", 12);
        frete.put("DF", 4);

        return LocalDate.now().plusDays(frete.get(estado));
    }

    private RetornoPedidoDTO fromEntidadeToRetornoPedidoDTO(PedidoEntidade entidade) {
        RetornoPedidoDTO pedidoDTO = new RetornoPedidoDTO();
        pedidoDTO.setProduto(entidade.getProduto());
        pedidoDTO.setDataEntrega(entidade.getDataEntrega());
        pedidoDTO.setDescricao(entidade.getDescricao());
        pedidoDTO.setValor(entidade.getValor());
        return pedidoDTO;
    }
}
