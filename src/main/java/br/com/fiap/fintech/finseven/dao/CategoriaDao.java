package br.com.fiap.fintech.finseven.dao;

import br.com.fiap.fintech.finseven.factory.ConnectionFactory;
import br.com.fiap.fintech.finseven.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoriaDao {

    private Connection conexao;


    public CategoriaDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }

    public void cadastrar(Categoria categoria) throws SQLException {

        java.sql.PreparedStatement stmt = conexao.prepareStatement("INSERT INTO T_FINSEVEN_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, TP_MOVIMENTO) " +
                "VALUES (SQ_FINSEVEN_CATEGORIA.NEXTVAL, ?, ?)");


        stmt.setLong(1, categoria.getId());
        stmt.setString(2, categoria.getDescricao());
        stmt.setString(3, categoria.getTiposTransacao());
        stmt.executeUpdate();
        stmt.close();

        System.out.println("Categoria cadastrada com sucesso!");

    }
    }


