package br.edu.univille.crud.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate dataAluguel;
    private double valor;

    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Getters e Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public LocalDate getDataAluguel() { return dataAluguel; }
    public void setDataAluguel(LocalDate dataAluguel) { this.dataAluguel = dataAluguel; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public Carro getCarro() { return carro; }
    public void setCarro(Carro carro) { this.carro = carro; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}