package br.com.fiap.fintech.finseven.model;

public class Usuario {
    private String nome;
    private int cpf;
    private Endereco endereco;
    private Login login;


    public Usuario() {
    }


    public Usuario(String nome, int cpf, Endereco endereco, Login login) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.login = login;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }


    public void cadastrarUsuario(String nome, int dtNasc, Endereco endereco, Login login) {
        this.nome = nome;
        this.cpf = dtNasc;
        this.endereco = endereco;
        this.login = login;
        System.out.println("Usuário cadastrado com sucesso!");

    }

    public void atualizarUsuario(String nome, int cpf) {
        this.nome = nome;
        this.cpf = cpf;


        System.out.println("Dados do usuário atualizados com sucesso.");
    }

    public void exibirDadosUsuario() {
        System.out.println("\n-----Dados do usuário-----");
        System.out.println("Nome: " + nome
                + "\n CPF: " + cpf
                // + "\n Endereço: " + endereco (exibição do endereço no método exibirEndereço)
                + "\n Id do usuário: " + login.getIdLogin()
                + "\nEmail do usuário:" + login.getEmail());


    }

    public void deletarEndereco() {
        this.endereco = null;
        System.out.println("Endereço removido do perfil do usuário.");
    }

}
