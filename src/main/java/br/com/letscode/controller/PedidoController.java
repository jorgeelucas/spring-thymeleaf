package br.com.letscode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import br.com.letscode.dto.CadastrarPedidoDTO;
import br.com.letscode.dto.RetornoPedidoDTO;
import br.com.letscode.service.PedidoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<RetornoPedidoDTO> todosOsPedidosDTO = service.listarTodosOsPedidos();

        model.addAttribute("pedidos", todosOsPedidosDTO);
        return "listar-pedidos";
    }

    @GetMapping("/cadastrar-pedido-form")
    public String cadastrarPedidoForm() {
        return "cadastrar-pedido-form";
    }

    @PostMapping("/cadastrar-pedido")
    public RedirectView cadastrarPedido(CadastrarPedidoDTO cadastrarPedidoDTO) {
        service.cadastrarPedido(cadastrarPedidoDTO);

        RedirectView view = new RedirectView("/pedidos/listar", true);

        return view;
    }
}
