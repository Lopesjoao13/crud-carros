package br.edu.univille.crud.controller;

import br.edu.univille.crud.service.AluguelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
public class RelatorioController {

    private final AluguelService aluguelService;

    // O Spring injeta automaticamente o serviço de aluguel aqui
    public RelatorioController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @GetMapping("/relatorio")
    public ModelAndView relatorio() {
        var mv = new ModelAndView("relatorio"); // Aponta para o arquivo relatorio.html

        // Busca os dados agrupados (Marca -> Soma dos Valores)
        Map<String, Double> dadosGrafico = aluguelService.obterFaturamentoPorMarca();

        // Passa as marcas (labels) e os valores separadamente para o Chart.js ler no HTML
        mv.addObject("labels", dadosGrafico.keySet());
        mv.addObject("valores", dadosGrafico.values());

        return mv;
    }
}