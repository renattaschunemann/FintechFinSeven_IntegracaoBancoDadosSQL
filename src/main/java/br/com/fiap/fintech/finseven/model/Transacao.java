package br.com.fiap.fintech.finseven.model;

import java.sql.Date;
import java.time.LocalDate;

public class Transacao {


        private Long id;
        private Long idBanco;
        private Long idCategoria;
        private Double valor;
        private LocalDate data;
        private String descricao;
        private String tipo;


    public Transacao() {
    }

    public Transacao(Long id, Long idBanco, Long idCategoria, Double valor, LocalDate data, String descricao, String tipo) {
        this.id = id;
        this.idBanco = idBanco;
        this.idCategoria = idCategoria;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Transacao(Long idBanco, Long idCategoria, Double valor, LocalDate data, String descricao, String tipo) {
        this.idBanco = idBanco;
        this.idCategoria = idCategoria;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return Date.valueOf(data);
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Long idBanco) {
        this.idBanco = idBanco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected void exibirMovimentacao() {
    }
}



