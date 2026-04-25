package br.com.fiap.fintech.finseven.dao;

import br.com.fiap.fintech.finseven.factory.ConnectionFactory;
import br.com.fiap.fintech.finseven.model.Categoria;
import br.com.fiap.fintech.finseven.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioDao {

    private Connection conexao;


    public UsuarioDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }

    public void cadastrar(Usuario usuario) throws SQLException {

        java.sql.PreparedStatement stmt = conexao.prepareStatement("INSERT INTO T_FINSEVEN_USUARIO (ID_USUARIO, NM_USUARIO,CPF_USUARIO,EMAIL_USUARIO) " +
                "VALUES (SQ_FINSEVEN_USUARIO.NEXTVAL, ?, ?,?)");


        stmt.setString(1, usuario.getNome());
        stmt.setLong(2, usuario.getCpf());
        stmt.setString(3,usuario.getEmail());
        stmt.executeUpdate();
        stmt.close();

        System.out.println("Categoria cadastrada com sucesso!");
    }
}
