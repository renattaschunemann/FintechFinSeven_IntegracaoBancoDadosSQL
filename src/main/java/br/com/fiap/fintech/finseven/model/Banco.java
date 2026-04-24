package br.com.fiap.fintech.finseven.model;

public class Banco {

    private String nome;
    private String agencia;
    private String conta;
    private String tipo;
    private double saldo;

    public Banco() {
    }

    public Banco(String nome, String agencia, String conta, String tipo, double saldo) {
        this.nome = nome;
        this.agencia = agencia;
        this.conta = conta;
        this.tipo = tipo;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public void exibirDadosBanco (){
        System.out.println("Dados do Banco escolhido: " + nome + "Agência: " + agencia + " Número da conta: " + conta +
                "Tipo de conta" + tipo + " Saldo da conta: " + saldo);
    }

}

