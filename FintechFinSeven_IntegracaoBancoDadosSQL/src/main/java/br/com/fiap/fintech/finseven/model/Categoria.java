package br.com.fiap.fintech.finseven.model;

public class Categoria {
    private Long id;
    private String descricao;
    private String tiposTransacao;

    public Categoria() {
    }

    public Categoria(Long id, String descricao, String tiposTransacao) {
        this.id = id;
        this.descricao = descricao;
        this.tiposTransacao = tiposTransacao;
    }

    public Categoria(String descricao, String tiposTransacao) {
        this.descricao = descricao;
        this.tiposTransacao = tiposTransacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTiposTransacao() {
        return tiposTransacao;
    }

    public void setTiposTransacao(String tiposTransacao) {
        this.tiposTransacao = tiposTransacao;
    }



}









