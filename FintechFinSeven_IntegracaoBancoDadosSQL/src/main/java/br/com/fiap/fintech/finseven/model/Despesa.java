package br.com.fiap.fintech.finseven.model;


public class Despesa{

    private long idTransacao;
    private String formaPagamento;

    public Despesa() {
    }

    public Despesa(long idTransacao, String formaPagamento) {
        this.idTransacao = idTransacao;
        this.formaPagamento = formaPagamento;
    }

    public Despesa(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
