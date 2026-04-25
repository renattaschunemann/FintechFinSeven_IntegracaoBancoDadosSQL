package br.com.fiap.fintech.finseven.model;

public class Banco {

    private long id;
    private long idUsuario;
    private long numeroConta;
    private Double saldo;
    private String tipoConta;
    private Integer numeroAgencia;
    private String nome;

    public Banco() {
    }

    public Banco(Integer idBanco, Integer idUsuario, Integer numeroConta, Double saldo, String tipoConta, Integer numeroAgencia, String nome) {
        this.id = idBanco;
        this.idUsuario = idUsuario;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
        this.numeroAgencia = numeroAgencia;
        this.nome = nome;
    }

    public Banco(Integer idUsuario, Integer numeroConta, Double saldo, String tipoConta, Integer numeroAgencia, String nome) {
        this.idUsuario = idUsuario;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
        this.numeroAgencia = numeroAgencia;
        this.nome = nome;
    }

    public long getIdBanco() {
        return id;
    }

    public void setIdBanco(Integer idBanco) {
        this.id = idBanco;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Integer getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(Integer numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


     public void exibirDadosBanco (){
    System.out.println("Dados do Banco escolhido: " + nome + " id Usuário: " + idUsuario + "Número do banco: "+ id+ "Número da conta: " + numeroConta + "Tipo de conta: " + tipoConta + "Número da agência: "+ numeroAgencia +  "Saldo da conta: " + saldo ) ; }



}

