package br.com.fiap.fintech.finseven.model;

public class Investimento extends Transacao {

    private String origem;

    public Investimento() {
            super();
    }

    public Investimento(Transacao transacao) {
        super(transacao.getId(),
            transacao.getIdBanco(),
            transacao.getIdCategoria(),
            transacao.getValor(),
            transacao.getData().toLocalDate(),
            transacao.getDescricao(),
            transacao.getTipo());
    }

    public Investimento(Transacao transacao, String origem) {
        this(transacao);
        this.origem = origem;
    }

    public Investimento(long idTransacao, String origem) {
        this.setId(idTransacao);
        this.origem = origem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

}
