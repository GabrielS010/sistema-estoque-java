package com.estoque.repository;

import com.estoque.exception.ProdutoNaoEncontradoException;
import com.estoque.model.Produto;
import java.util.HashMap;
import java.util.Map;

public class EstoqueRepository {

    // Escolhi o HashMap porque a busca pelo SKU (chave) é instantânea.
    // O "?" no Produto permite que o mapa aceite produtos com qualquer tipo de detalhe.
    private final Map<String, Produto<?>> produtos = new HashMap<>();

    public void salvar(Produto<?> produto) {
        produtos.put(produto.getSku(), produto);
    }

    public Produto<?> buscarPorSku(String sku) throws ProdutoNaoEncontradoException {
        // Antes de tentar pegar o objeto, valido se ele realmente existe no mapa
        if (!produtos.containsKey(sku)) {
            throw new ProdutoNaoEncontradoException(sku);
        }
        return produtos.get(sku);
    }

    public Map<String, Produto<?>> listarTodos() {
        return produtos;
    }
}