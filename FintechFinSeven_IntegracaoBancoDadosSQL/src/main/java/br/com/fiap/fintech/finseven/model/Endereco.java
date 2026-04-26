package br.com.fiap.fintech.finseven.model;

public class Endereco {
    private String logradouro;
    private int numero;
    private int cep;
    private String cidade;
    private String estado;


    public Endereco() {}

    public Endereco(String logradouro, int numero, int cep, String cidade, String estado) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;


    }


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", cep=" + cep +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    public void exibirEndereco(){

        System.out.println("Logradouro: " + logradouro
                +"\nNúmero: " + numero
                + "\nCep: "+ cep
                + "\nCidade: " + cidade
                + "\nEstado: " + estado
        );

    }

}

