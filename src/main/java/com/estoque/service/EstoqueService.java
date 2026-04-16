package com.estoque.service;

import com.estoque.model.Produto;
import com.estoque.repository.EstoqueRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class EstoqueService {
    private final EstoqueRepository repository;

    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    // Uso o stream para filtrar a lista e encontrar quem está com pouca unidade
    public List<Produto<?>> filtrarPorEstoqueBaixo(int quantidadeLimite) {
        return repository.listarTodos().values().stream()
                .filter(p -> p.getQuantidade() < quantidadeLimite)
                .collect(Collectors.toList());
    }

    // O map transforma o produto no valor (preço * qtd) e o reduce soma tudo
    public BigDecimal calcularValorTotalEmEstoque() {
        return repository.listarTodos().values().stream()
                .map(p -> p.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}