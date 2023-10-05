package br.edu.umfg.estrategia;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Carrinho {
    private ArrayList<Produto> lista = new ArrayList<Produto>();

    public void adicionarProduto(Produto produto){
        this.lista.add(produto);
    }

    public void removerProduto(Produto produto){
        this.lista.remove(produto);
    }

    public void pagar(MeioPagamentoEstrategia meioPagamento){
            meioPagamento.pagar(obterTotal());
        }


    public void limparCarrinho() {
        lista.clear();
    }
    private Double obterTotal (){
        Double total = 0.0;

        for(Produto produto : lista){
            total += produto.getValor();
        }
        return total;
    }

    public Double getTotal() {
        return obterTotal();
    }

    public boolean estaVazio() {
        return lista.isEmpty();
    }
}
