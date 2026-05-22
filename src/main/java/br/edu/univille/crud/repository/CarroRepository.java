package br.edu.univille.crud.repository;

import br.edu.univille.crud.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
