package br.com.fiap.fintech.finseven.model;

public class Suporte {


    private String assunto;
    private String mensagem;
    private Usuario usuario;

    public Suporte() {
    }

    public Suporte(String assunto, String mensagem, Usuario usuario) {
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.usuario = usuario;
    }


    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void enviarMensagem() {
        if (usuario != null && assunto != null && !assunto.isEmpty()) {
            System.out.println("\n--- Chamado de Suporte Enviado ---");
            System.out.println("Usuário: " + usuario.getNome());
            System.out.println("Assunto: " + assunto);
            System.out.println("Mensagem: " + mensagem);
        } else {
            System.out.println("Erro: informações de suporte incompletas.");
        }
    }
}

