package br.edu.univille.crud.service;

import br.edu.univille.crud.entity.Carro;
import br.edu.univille.crud.repository.CarroRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private final CarroRepository repository;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public List<Carro> findAll() {
        return repository.findAll(Sort.by("marca"));
    }

    public Optional<Carro> findById(long id) {
        return repository.findById(id);
    }

    public Carro save(Carro carro) {
        return repository.save(carro);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public List<Carro> findAll(Sort sort) {return repository.findAll(sort);}
}
