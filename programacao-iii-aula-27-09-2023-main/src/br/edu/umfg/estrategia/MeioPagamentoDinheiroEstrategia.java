package br.edu.umfg.estrategia;
import java.text.DecimalFormat;

public class MeioPagamentoDinheiroEstrategia implements MeioPagamentoEstrategia {
    @Override
    public void pagar(Double valor) {
        System.out.println("Não é possível pagar em dinheiro com esta estratégia.");
    }

    DecimalFormat df = new DecimalFormat("0.00");


    public Double pagarEmDinheiro(Double valorEmDinheiro, Double valorTotal) {
        if (valorEmDinheiro >= valorTotal) {
            System.out.println("Pagamento em dinheiro no valor de R$" + valorEmDinheiro + " realizado com sucesso.");


            Double troco = valorEmDinheiro - valorTotal;
            String valorFormatado = df.format(troco);
            if (troco > 0) {
                System.out.println("Troco: R$" + valorFormatado);
            } else {
                System.out.println("Troco: R$0.00");
            }

            return troco;
        } else {
            System.out.println("Valor em dinheiro insuficiente para o pagamento.");
            return null;
        }
    }
}
