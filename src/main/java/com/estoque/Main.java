package com.estoque;

import com.estoque.exception.ProdutoNaoEncontradoException;
import com.estoque.model.Produto;
import com.estoque.repository.EstoqueRepository;
import com.estoque.service.EstoqueService;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        EstoqueRepository repo = new EstoqueRepository();
        EstoqueService service = new EstoqueService(repo);

        int opcao = 0;

        System.out.println("=== SISTEMA DE ESTOQUE NINJA ===");

        while (opcao != 5) {
            System.out.println("\n1. Cadastrar Produto | 2. Buscar por SKU | 3. Valor Total | 4. Estoque Baixo | 5. Sair");
            System.out.print("Escolha uma opção: ");

            // Tratamento simples para evitar que o programa quebre se não for digitado um número
            try {
                opcao = Integer.parseInt(leitor.nextLine());

                switch (opcao) {
                    case 1 -> {
                        System.out.print("SKU: "); String sku = leitor.nextLine();
                        System.out.print("Nome: "); String nome = leitor.nextLine();
                        System.out.print("Preço: "); BigDecimal preco = new BigDecimal(leitor.nextLine());
                        System.out.print("Quantidade: "); int qtd = Integer.parseInt(leitor.nextLine());
                        System.out.print("Detalhes: "); String detalhes = leitor.nextLine();

                        repo.salvar(new Produto<>(sku, nome, preco, qtd, detalhes));
                        System.out.println("Produto registrado com sucesso!");
                    }
                    case 2 -> {
                        System.out.print("Digite o SKU para busca: ");
                        String skuBusca = leitor.nextLine();
                        // Aqui capturamos o erro caso o SKU não exista
                        try {
                            Produto<?> p = repo.buscarPorSku(skuBusca);
                            System.out.println("Localizado: " + p.getNome() + " | Qtd: " + p.getQuantidade());
                        } catch (ProdutoNaoEncontradoException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    case 3 -> System.out.println("Patrimônio total: R$ " + service.calcularValorTotalEmEstoque());
                    case 4 -> {
                        System.out.print("Defina o limite de alerta: ");
                        int limite = Integer.parseInt(leitor.nextLine());
                        System.out.println("Itens abaixo do limite:");
                        service.filtrarPorEstoqueBaixo(limite).forEach(System.out::println);
                    }
                    case 5 -> System.out.println("Encerrando... Até a próxima missão!");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro na entrada. Certifique-se de digitar os valores corretamente.");
            }
        }
        leitor.close();
    }
}