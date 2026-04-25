package br.com.fiap.fintech.finseven.model;

import java.time.LocalDate;

public class Receita {
    private long idTransacao;
    private String origem;

    public Receita() {
    }

    public Receita(long idTransacao, String origem) {
        this.idTransacao = idTransacao;
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

    public long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(long idTransacao) {
        this.idTransacao = idTransacao;
    }
}
