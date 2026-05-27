package br.edu.univille.crud.controller;

import br.edu.univille.crud.entity.Aluguel;
import br.edu.univille.crud.service.AluguelService;
import br.edu.univille.crud.service.CarroService;
import br.edu.univille.crud.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/alugueis")
public class AluguelController {

    private final AluguelService aluguelService;
    private final CarroService carroService;
    private final ClienteService clienteService;

    public AluguelController(AluguelService aluguelService, CarroService carroService, ClienteService clienteService) {
        this.aluguelService = aluguelService;
        this.carroService = carroService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public ModelAndView novoAluguel() {
        var mv = new ModelAndView("formulario-aluguel");
        mv.addObject("aluguel", new Aluguel());
        mv.addObject("listaCarros", carroService.findAll());
        mv.addObject("listaClientes", clienteService.findAll());
        return mv;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute Aluguel aluguel) {
        aluguelService.save(aluguel);
        return new ModelAndView("redirect:/relatorio"); // Redireciona direto para ver o gráfico atualizado!
    }
}