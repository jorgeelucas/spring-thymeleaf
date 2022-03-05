package br.com.letscode.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.letscode.dto.CadastrarPedidoDTO;
import br.com.letscode.dto.RetornoCadastroPedidoDTO;
import br.com.letscode.dto.RetornoPedidoDTO;
import br.com.letscode.entity.PedidoEntidade;
import br.com.letscode.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final EmailService emailService;

    public RetornoCadastroPedidoDTO cadastrarPedido(CadastrarPedidoDTO cadastrarPedidoDTO) {

        emailService.enviar(cadastrarPedidoDTO.getEmail());

        PedidoEntidade entidade = new PedidoEntidade();
        entidade.setProduto(cadastrarPedidoDTO.getProduto());
        entidade.setDescricao(cadastrarPedidoDTO.getDescricao());
        entidade.setValor(cadastrarPedidoDTO.getValor());

        LocalDate dataEntrega = calcularDataEntrega(cadastrarPedidoDTO.getEndereco());
        entidade.setDataEntrega(dataEntrega);

        PedidoEntidade entidadeSalva = repository.salvar(entidade);

        RetornoCadastroPedidoDTO dto = fromEntidadeToRetornoCadastroPedidoDTO(entidadeSalva);

        return dto;
    }

    public List<RetornoPedidoDTO> listarTodosOsPedidos() {

        List<PedidoEntidade> entidades = repository.getAll();

        List<RetornoPedidoDTO> listaRetorno = entidades.stream()
            .map(entidade -> fromEntidadeToRetornoPedidoDTO(entidade))
            .collect(Collectors.toList());

        return listaRetorno;
    }

    public List<RetornoPedidoDTO> listarTodosOsPedidosFiltrado(String produto) {
        List<PedidoEntidade> entidades = repository.getAll();

        List<RetornoPedidoDTO> listaRetorno = entidades.stream()
            .map(entidade -> fromEntidadeToRetornoPedidoDTO(entidade))
            .filter(pedido -> pedido.getProduto().contains(produto))
            .collect(Collectors.toList());

        return listaRetorno;
    }

    public RetornoPedidoDTO buscarPorId(Long id) {

        // busquei do banco, veio entidade
        PedidoEntidade entidade = repository.getPorId(id);

        //transformei minha entidade em dto
        RetornoPedidoDTO dto = fromEntidadeToRetornoPedidoDTO(entidade);

        // retornei o dto
        return dto;
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

    private RetornoCadastroPedidoDTO fromEntidadeToRetornoCadastroPedidoDTO(PedidoEntidade entidade) {
        RetornoCadastroPedidoDTO retornoCadastroPedidoDTO = new RetornoCadastroPedidoDTO();
        retornoCadastroPedidoDTO.setId(entidade.getId());
        retornoCadastroPedidoDTO.setProduto(entidade.getProduto());
        retornoCadastroPedidoDTO.setDescricao(entidade.getDescricao());
        retornoCadastroPedidoDTO.setDataEntrega(entidade.getDataEntrega());
        retornoCadastroPedidoDTO.setValor(entidade.getValor());
        return retornoCadastroPedidoDTO;
    }
}
