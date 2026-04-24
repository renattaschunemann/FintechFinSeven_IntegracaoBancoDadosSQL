package br.com.fiap.fintech.finseven.model;

import java.time.LocalDate;

public class Receita extends Movimentacao {
    private String fonte;

    public Receita(String descricao, double valor, LocalDate data, Categoria categoria, String fonte) {
        super(descricao, valor, data, TipoMovimentacao.RECEITA, categoria);
        this.fonte = fonte;
    }
    public Receita(){
        super();
    }
    public String getFonte() { return fonte; }

    public void setFonte(String fonte) { this.fonte = fonte; }

    @Override
    public void exibirMovimentacao() {
        super.exibirMovimentacao();
        System.out.println("Fonte da Receita: " + fonte);
    }

}
