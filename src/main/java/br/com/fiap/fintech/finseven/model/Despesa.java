package br.com.fiap.fintech.finseven.model;

import java.time.LocalDate;

public class Despesa extends  Movimentacao{
    private String metodoPagamento;


    public Despesa(){
        super();
    }


    public Despesa(String descricao, double valor, LocalDate data, Categoria categoria, String metodoPagamento) {

        super(descricao, valor, data, TipoMovimentacao.DESPESA, categoria);
        this.metodoPagamento = metodoPagamento;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    // Polimorfismo: Sobrescrevendo o método para exibir o dado específico de Despesa
    @Override
    public void exibirMovimentacao() {
        super.exibirMovimentacao();
        System.out.println("Forma de Pagamento: " + metodoPagamento);

    }
}
