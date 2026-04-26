package br.com.fiap.fintech.finseven.dao;

import br.com.fiap.fintech.finseven.factory.ConnectionFactory;
import br.com.fiap.fintech.finseven.model.Receita;
import br.com.fiap.fintech.finseven.model.Transacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReceitaDao {

    private Connection conexao;
    private TransacaoDao transacaoDao;

    public ReceitaDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
        transacaoDao = new TransacaoDao();

    }

    public void cadastrar(Receita receita) throws SQLException {

        transacaoDao.cadastrar(receita);

        PreparedStatement stmt = conexao.prepareStatement("INSERT INTO T_FINSEVEN_RECEITA (ID_TRANSACAO, ORIGEM_RECEITA) VALUES (?, ?)");

        stmt.setLong(1, receita.getId());
        stmt.setString(2, receita.getOrigem());

        stmt.executeUpdate();
        System.out.println("Receita cadastrada com sucesso!");
        stmt.close();

    }
}
