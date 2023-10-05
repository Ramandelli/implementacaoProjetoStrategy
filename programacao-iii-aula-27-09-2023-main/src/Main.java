import br.edu.umfg.estrategia.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Carrinho carrinho = new Carrinho();
        boolean jaPagou = false;

        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Adicionar Produto ao Carrinho");
            System.out.println("2. Realizar Pagamento com Cartão de Crédito");
            System.out.println("3. Realizar Pagamento com Cartão de Débito");
            System.out.println("4. Realizar Pagamento em Dinheiro");
            System.out.println("5. Sair");
            System.out.print("Opção: ");


            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o código de barras do produto: ");
                    String codigoDeBarras = scanner.next();
                    System.out.print("Digite a descrição do produto: ");
                    String descricao = scanner.next();
                    System.out.print("Digite o valor do produto: ");
                    Double valor = scanner.nextDouble();

                    Produto produto = new Produto(codigoDeBarras, descricao, valor);
                    carrinho.adicionarProduto(produto);

                    System.out.println("------------------------------------------------------");
                    System.out.println("Produto Adicionado: " + descricao);
                    System.out.println("Valor Total do Carrinho: " + carrinho.getTotal());
                    System.out.println("------------------------------------------------------");

                    jaPagou = false;
                    break;

                case 2:
                    if (jaPagou || carrinho.estaVazio()) {
                        System.out.println("Carrinho vazio. Adicione produtos antes de pagar..");
                    } else {
                        System.out.print("Digite o número do cartão de crédito: ");
                        String numeroCartaoCredito = scanner.next();
                        System.out.print("Digite o CPF: ");
                        String cpfCredito = scanner.next();
                        System.out.print("Digite o codigo CVV: ");
                        String cvvCredito = scanner.next();
                        System.out.print("Digite a data de validade (MM/yyyy): ");
                        String dataValidadeCredito = scanner.next();

                        MeioPagamentoCieloCreditoEstrategia pagamentoCredito =
                                new MeioPagamentoCieloCreditoEstrategia(numeroCartaoCredito, cpfCredito, cvvCredito, dataValidadeCredito);
                        carrinho.pagar(pagamentoCredito);
                        carrinho.limparCarrinho();
                        jaPagou = true;
                    }
                    break;

                case 3:
                    if (jaPagou || carrinho.estaVazio()) {
                        System.out.println("Carrinho vazio. Adicione produtos antes de pagar..");
                    } else {
                        System.out.print("Digite o número do cartão de débito: ");
                        String numeroCartaoDebito = scanner.next();
                        System.out.print("Digite o CPF: ");
                        String cpfDebito = scanner.next();
                        System.out.print("Digite a data de validade (MM/yyyy): ");
                        String dataValidadeDebito = scanner.next();

                        MeioPagamentoCieloDebitoEstrategia pagamentoDebito =
                                new MeioPagamentoCieloDebitoEstrategia(numeroCartaoDebito, cpfDebito, dataValidadeDebito);
                        carrinho.pagar(pagamentoDebito);
                        carrinho.limparCarrinho();
                        jaPagou = true;
                    }
                    break;

                case 4:
                    if (jaPagou || carrinho.estaVazio()) {
                        System.out.println("Carrinho vazio. Adicione produtos antes de pagar.");
                    } else {
                        System.out.print("Digite o valor em dinheiro:");
                        Double valorEmDinheiro = scanner.nextDouble();

                        MeioPagamentoDinheiroEstrategia pagamentoDinheiro = new MeioPagamentoDinheiroEstrategia();
                        Double troco = pagamentoDinheiro.pagarEmDinheiro(valorEmDinheiro, carrinho.getTotal());
                        carrinho.limparCarrinho();
                        jaPagou = true;
                    }
                    break;

                case 5:
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        }
    }
}
