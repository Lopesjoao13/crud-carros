package br.edu.univille.crud.repository;

import br.edu.univille.crud.entity.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    // Pronto para salvar, deletar e listar os aluguéis associados aos carros e clientes
}