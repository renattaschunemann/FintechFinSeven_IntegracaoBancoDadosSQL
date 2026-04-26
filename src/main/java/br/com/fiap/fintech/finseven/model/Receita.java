package br.com.fiap.fintech.finseven.model;

import java.time.LocalDate;

public class Receita extends Transacao {

    private String origem;

    public Receita() {
        super();
    }

    public Receita(Transacao transacao) {
        super(transacao.getId(),
                transacao.getIdBanco(),
                transacao.getIdCategoria(),
                transacao.getValor(),
                transacao.getData().toLocalDate(),
                transacao.getDescricao(),
                transacao.getTipo());
    }

    public Receita(Transacao transacao, String origem) {
        this(transacao);
        this.origem = origem;
    }

    public Receita(long idTransacao, String origem) {
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
