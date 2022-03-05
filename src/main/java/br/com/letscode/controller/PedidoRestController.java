package br.com.letscode.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.dto.CadastrarPedidoDTO;
import br.com.letscode.dto.RetornoCadastroPedidoDTO;
import br.com.letscode.dto.RetornoPedidoDTO;
import br.com.letscode.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoRestController {

    private final PedidoService pedidoService;

    public PedidoRestController(final PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<RetornoCadastroPedidoDTO> cadastrarPedido(@RequestBody CadastrarPedidoDTO cadastrarPedidoDTO) {
        RetornoCadastroPedidoDTO retornoCadastroPedidoDTO = this.pedidoService.cadastrarPedido(cadastrarPedidoDTO);

        return ResponseEntity
            .created(URI.create("http://localhost:8080/pedidos/" + retornoCadastroPedidoDTO.getId()))
            .build();
//            .body(retornoCadastroPedidoDTO);
    }

    @GetMapping("/{id}")
    public RetornoPedidoDTO getRetornoDTO(@PathVariable("id") Long id) {
        System.out.println("buscando pedido de id: " + id);

        RetornoPedidoDTO retornoPedidoDTO = pedidoService.buscarPorId(id);

        return retornoPedidoDTO;
    }

    @GetMapping
    public List<RetornoPedidoDTO> getListaRetornoPedidoDTO(@RequestParam(value = "filtro", defaultValue = "") String filtroProduto) {
        return pedidoService.listarTodosOsPedidosFiltrado(filtroProduto);
    }


}
