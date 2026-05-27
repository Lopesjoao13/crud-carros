package br.edu.univille.crud.controller;

import br.edu.univille.crud.entity.Cliente;
import br.edu.univille.crud.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index() {
        var mv = new ModelAndView("clientes");
        mv.addObject("clientes", service.findAll());
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView novoCliente() {
        var mv = new ModelAndView("formulario-cliente");
        mv.addObject("cliente", new Cliente());
        return mv;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute Cliente cliente) {
        service.save(cliente);
        return new ModelAndView("redirect:/clientes");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") long id) {
        var mv = new ModelAndView("formulario-cliente");
        mv.addObject("cliente", service.findById(id).orElse(new Cliente()));
        return mv;
    }

    @PostMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") long id) {
        service.deleteById(id);
        return new ModelAndView("redirect:/clientes");
    }
}