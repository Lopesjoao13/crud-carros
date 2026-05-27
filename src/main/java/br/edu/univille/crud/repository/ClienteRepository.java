package br.edu.univille.crud.repository;

import br.edu.univille.crud.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // O Spring Data JPA gera automaticamente todos os métodos de CRUD aqui
}