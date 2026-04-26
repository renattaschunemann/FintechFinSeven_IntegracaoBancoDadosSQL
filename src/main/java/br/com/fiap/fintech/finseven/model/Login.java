package br.com.fiap.fintech.finseven.model;

public class Login {
    private int idLogin;
    private String email;
    private String senha;
    private int tentativasLogin;
    private boolean contaBloqueada;
    private  Usuario usuario;


    public Login() {
    }

    public Login(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Login(int idLogin) {
        System.out.println(idLogin);
    }

    public Login(int idLogin, String email, String senha, int tentativasLogin, boolean contaBloqueada) {
        this.idLogin = idLogin;
        this.email = email;
        this.senha = senha;
        this.tentativasLogin = tentativasLogin;
        this.contaBloqueada = contaBloqueada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTentativasLogin() {
        return tentativasLogin;
    }

    public void setTentativasLogin(int tentativasLogin) {
        this.tentativasLogin = tentativasLogin;
    }

    public boolean isContaBloqueada() {
        return contaBloqueada;
    }

    public void setContaBloqueada(boolean contaBloqueada) {
        this.contaBloqueada = contaBloqueada;
    }

    //Método validarCredenciais executado
    public boolean validarCredenciais(String email, String senha) {
        System.out.println("Verificando email e senha");
        return email.equals(email) && senha.equals(senha);
    }

    //Método realizarLogin
    public void realizarLogin(String emailInformado, String senhaInformada) {
        if (this.contaBloqueada) {
            System.out.println("ACESSO NEGADO: Conta bloqueada. Procure o suporte.");
            return;
        }

        // Validação: Verifica se a senha informada é IGUAL à senha do objeto
        if (this.senha != null && this.senha.equals(senhaInformada)) {
            System.out.println("Login realizado com sucesso! Bem-vindo, " + (usuario != null ? usuario.getNome() : emailInformado));
            this.tentativasLogin = 0;
        } else {
            this.tentativasLogin++;
            System.out.println("Erro: Senha incorreta! Tentativa " + tentativasLogin + " de 3.");

            if (this.tentativasLogin >= 3) {
                bloquearConta();
            }
        }
    }

    //Método bloquearConta executado
    public void bloquearConta() {
        contaBloqueada = true;
        System.out.println("Conta bloqueada por excesso tentativas inválidas");
    }

    //Método recuperarSenha executado
    public void recuperarSenha() {
        System.out.println("Processo de recuperação iniciado");
    }

    //Método contatarSuporte executado
    public void contatarSuporte() {
        System.out.println("Direcionando usuário ao suporte");
    }

}