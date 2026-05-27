package br.edu.univille.crud.service;

import br.edu.univille.crud.entity.Aluguel;
import br.edu.univille.crud.repository.AluguelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AluguelService {

    private final AluguelRepository repository;

    public AluguelService(AluguelRepository repository) {
        this.repository = repository;
    }

    public List<Aluguel> findAll() {
        return repository.findAll();
    }

    public Optional<Aluguel> findById(long id) {
        return repository.findById(id);
    }

    public Aluguel save(Aluguel aluguel) {
        return repository.save(aluguel);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    /**
     * Regra de negócio para o Gráfico:
     * Busca todos os aluguéis e agrupa a soma dos valores pela Marca do Carro.
     */
    public Map<String, Double> obterFaturamentoPorMarca() {
        return repository.findAll().stream().collect(
                Collectors.groupingBy(
                        aluguel -> aluguel.getCarro().getMarca(),
                        Collectors.summingDouble(Aluguel::getValor)
                )
        );
    }
}