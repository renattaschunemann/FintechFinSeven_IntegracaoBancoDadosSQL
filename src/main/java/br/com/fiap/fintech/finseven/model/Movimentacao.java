package br.com.fiap.fintech.finseven.model;

import java.time.LocalDate;

public class Movimentacao {

    private String descricao;
    private double valor;
    private LocalDate data;
    private TipoMovimentacao tipo;
    private Categoria categoria;

    public Movimentacao(){}

    public Movimentacao(String descricao, double valor, LocalDate data, TipoMovimentacao tipo, Categoria categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void registrarReceita(double valorReceita) {
        this.tipo = TipoMovimentacao.RECEITA;
        if (this.valor > 0) {
            this.valor = valorReceita;
        }
        System.out.println("Receita confirmada: " + descricao);
    }

    public void registrarDespesa(double valorDespesa) {
        this.tipo = TipoMovimentacao.DESPESA;

        if (this.valor > 0) {
            this.valor = valorDespesa;
        }
        System.out.println("Despesa registrada: " + descricao);
    }

    public void registrarInvestimento() {
        this.tipo = TipoMovimentacao.INVESTIMENTO;
        System.out.println("Investimento aplicado: " + descricao);
    }

    public void exibirMovimentacao() {
        System.out.println("\n--- Extrato ---");
        System.out.println("Data: " + data);
        System.out.println("Descrição: " + descricao);
        System.out.println("Tipo: " + tipo);
        System.out.println("Categoria: " + (categoria != null ? categoria : "Nenhuma"));
        System.out.printf("Valor: R$ %.2f%n", valor);
    }


    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }
}

