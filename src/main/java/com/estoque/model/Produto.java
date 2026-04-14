package com.estoque.model;

import java.math.BigDecimal;

// O uso de <T> serve para que o produto aceite qualquer tipo de detalhe extra
// dependendo da categoria (ex: Voltagem para eletrônicos ou Validade para comida)
public class Produto<T> {
    private String sku;
    private String nome;
    private BigDecimal preco;
    private int quantidade;
    private T detalhes;

    public Produto(String sku, String nome, BigDecimal preco, int quantidade, T detalhes) {
        this.sku = sku;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.detalhes = detalhes;
    }

    // Getters necessários para as operações de filtro e cálculo no Service
    public String getSku() { return sku; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }
    public T getDetalhes() { return detalhes; }

    @Override
    public String toString() {
        return "Produto: " + nome + " | SKU: " + sku + " | Qtd: " + quantidade;
    }
}