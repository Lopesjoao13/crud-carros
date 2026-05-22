package br.edu.univille.crud.controller;

import br.edu.univille.crud.entity.Carro;
import br.edu.univille.crud.service.CarroService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class CarroController {

    private final CarroService service;

    // Pasta onde as imagens serão salvas (dentro de static/imagens)
    private static final String UPLOAD_DIR = "C:/uploads/imagens/";
    public CarroController(CarroService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(
            @RequestParam(defaultValue = "marca") String sort,
            @RequestParam(defaultValue = "asc") String dir) {

        Sort.Direction direcao = dir.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort ordenacao = Sort.by(direcao, sort);

        var mv = new ModelAndView("index");
        mv.addObject("lista", service.findAll(ordenacao));
        mv.addObject("sort", sort);
        mv.addObject("dir", dir);
        mv.addObject("dirOposto", dir.equals("asc") ? "desc" : "asc");
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView novoCarro() {
        var mv = new ModelAndView("novo-carro");
        mv.addObject("carro", new Carro());
        return mv;
    }

//    @PostMapping("/salvar")
//    public ModelAndView salvarCarro(@ModelAttribute Carro carro,
//                                    @RequestParam("arquivo") MultipartFile arquivo) throws IOException {
//
//        // Se o usuário enviou um arquivo de imagem
//        if (!arquivo.isEmpty()) {
//            // Gera um nome único para evitar conflitos
//            String nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
//            Path caminho = Paths.get(UPLOAD_DIR + nomeArquivo);
//            Files.createDirectories(caminho.getParent());
//            Files.write(caminho, arquivo.getBytes());
//            carro.setImagemUrl("/imagens/" + nomeArquivo);
//        }
//
//        service.save(carro);
//        return new ModelAndView("redirect:/");
//    }

    @PostMapping("/salvar")
    public ModelAndView salvarCarro(@ModelAttribute Carro carro,
                                    @RequestParam("arquivo") MultipartFile arquivo) throws IOException {

        System.out.println("Arquivo recebido: " + arquivo.getOriginalFilename());
        System.out.println("Tamanho: " + arquivo.getSize());

        if (!arquivo.isEmpty()) {
            String nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
            Path caminho = Paths.get(UPLOAD_DIR + nomeArquivo);
            System.out.println("Salvando em: " + caminho.toAbsolutePath());
            Files.createDirectories(caminho.getParent());
            Files.write(caminho, arquivo.getBytes());
            carro.setImagemUrl("/imagens/" + nomeArquivo);
            System.out.println("URL salva: " + carro.getImagemUrl());
        }

        service.save(carro);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") long id) {
        var mv = new ModelAndView("editar-carro");
        mv.addObject("carro", service.findById(id).get());
        return mv;
    }

    @DeleteMapping("/{id}")
    public ModelAndView excluir(@PathVariable("id") long id) {
        service.deleteById(id);
        return new ModelAndView("redirect:/");
    }
}
