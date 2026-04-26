package br.com.fiap.fintech.finseven.model;

import java.time.LocalDate;

public class Receita extends Transacao {

    private String origem;

    public Receita() {
        super();
    }

    public Receita(long idTransacao, String origem) {
        this.setId(idTransacao);
        this.origem = origem;
    }

    public Receita(String origem) {
        this.origem = origem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
}
