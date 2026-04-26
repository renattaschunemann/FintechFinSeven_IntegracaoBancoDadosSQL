package br.com.fiap.fintech.finseven.model;

import java.time.LocalDate;

public class Receita extends Transacao {

    private String origem;

    public Receita() {
        super();
    }

    public Receita(Long idBanco, Long idCategoria, Double valor, LocalDate data, String descricao, String tipo, String origem) {
        super(idBanco, idCategoria, valor, data, descricao, tipo);
        this.origem = origem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
}