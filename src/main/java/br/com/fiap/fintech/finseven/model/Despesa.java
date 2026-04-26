package br.com.fiap.fintech.finseven.model;


public class Despesa extends Transacao{

    private String formaPagamento;

    public Despesa() {
        super();
    }

    public Despesa(Transacao transacao) {
        super(transacao.getId(),
                transacao.getIdBanco(),
                transacao.getIdCategoria(),
                transacao.getValor(),
                transacao.getData().toLocalDate(),
                transacao.getDescricao(),
                transacao.getTipo());
    }

    public Despesa(Transacao transacao, String formaPagamento) {
        this(transacao);
        this.formaPagamento = formaPagamento;
    }

    public Despesa(long idTransacao, String formaPagamento) {
        this.setId(idTransacao);
        this.formaPagamento = formaPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
