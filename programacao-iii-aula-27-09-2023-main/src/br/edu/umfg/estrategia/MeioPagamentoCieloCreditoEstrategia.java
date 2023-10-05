package br.edu.umfg.estrategia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MeioPagamentoCieloCreditoEstrategia implements MeioPagamentoEstrategia {
    private String numeroCartao;
    private String cpf;
    private String cvv;
    private String dataValidade;

    public MeioPagamentoCieloCreditoEstrategia(String numeroCartao, String cpf, String cvv, String dataValidade) {
        this.numeroCartao = numeroCartao;
        this.cpf = cpf;
        this.cvv = cvv;
        this.dataValidade = dataValidade;
    }

    @Override
    public void pagar(Double valor) {
        if (validarNumeroCartao() && validarCPF() && validarCVV() && validarDataValidade()) {
            System.out.printf("Pagamento via Cielo com Cartão de Crédito no valor %.2f realizado com sucesso.\n", valor);
        } else {
            System.out.println("Falha no pagamento. Verifique os dados do cartão de crédito.");
        }
    }

    private boolean validarNumeroCartao() {
        // Verifica se o número do cartão tem exatamente 16 dígitos
        return numeroCartao.matches("\\d{16}");
    }

    private boolean validarCPF() {
        // Verifica se o CPF possui 11 dígitos e se está em um formato válido
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

        // Calcula os dígitos verificadores
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }

        int sum1 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += digits[i] * (10 - i);
        }

        int remainder1 = sum1 % 11;
        int digit1 = (remainder1 < 2) ? 0 : (11 - remainder1);

        int sum2 = 0;
        for (int i = 0; i < 10; i++) {
            sum2 += digits[i] * (11 - i);
        }

        int remainder2 = sum2 % 11;
        int digit2 = (remainder2 < 2) ? 0 : (11 - remainder2);

        return (digits[9] == digit1) && (digits[10] == digit2);
    }


    private boolean validarCVV() {
        // Verifica se o CVV tem exatamente 3 dígitos
        return cvv.matches("\\d{3}");
    }

    private boolean validarDataValidade() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
            Date dataValidadeDate = sdf.parse(dataValidade);
            Date dataAtual = new Date();

            // Verifica se a data de validade é maior que a data atual
            return dataValidadeDate.compareTo(dataAtual) > 0;
        } catch (ParseException e) {
            return false; // Formato de data inválido
        }
    }
}
