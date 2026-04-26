package br.com.fiap.fintech.finseven.model;

import java.time.LocalDate;

public class Investimento extends Transacao {

    private double taxa;


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

    public Investimento(Transacao transacao, double taxa) {
        this(transacao);
        this.taxa = taxa;
    }

    public Investimento(long idTransacao, double taxa) {
        this.setId(idTransacao);
        this.taxa = taxa;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
}
