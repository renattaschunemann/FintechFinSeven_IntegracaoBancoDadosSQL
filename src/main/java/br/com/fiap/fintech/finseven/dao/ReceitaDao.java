package br.com.fiap.fintech.finseven.dao;

import br.com.fiap.fintech.finseven.factory.ConnectionFactory;
import br.com.fiap.fintech.finseven.model.Receita;
import br.com.fiap.fintech.finseven.model.Transacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void atualizar(Receita receita) throws SQLException {
        transacaoDao.atualizar(receita);

        PreparedStatement stmt = conexao.prepareStatement("UPDATE T_FINSEVEN_RECEITA SET ORIGEM_RECEITA = ? " +
                "WHERE ID_TRANSACAO = ?");
        stmt.setString(1, receita.getOrigem());
        stmt.setLong(2, receita.getId());

        int linhas = stmt.executeUpdate();
        if (linhas > 0) {
            System.out.println("Receita atualizada com sucesso!");
        } else {
            System.out.println("Nenhuma receita encontrada com o ID: " + receita.getId());
        }
    }

    public Receita pesquisar(long id) throws SQLException {
        Transacao transacao = transacaoDao.pesquisar(id);

        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM T_FINSEVEN_RECEITA WHERE ID_TRANSACAO = ?");
        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        Receita receita = null;

        if (rs.next()) {
            receita = new Receita(transacao);
            receita.setOrigem(rs.getString("ORIGEM_RECEITA"));
        }

        return receita;
    }

    public List<Receita> pesquisarTodos() throws SQLException {
        List<Receita> lista = new ArrayList<>();
        // SQL para selecionar todos os registros das tabelas do join entre transacao e receita
        String sql = "SELECT * FROM T_FINSEVEN_RECEITA r INNER JOIN T_FINSEVEN_TRANSACAO t ON r.ID_TRANSACAO = t.ID_TRANSACAO ORDER BY t.DT_TRANSACAO DESC";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // O while percorre todas as linhas retornadas pelo banco
            while (rs.next()) {
                Receita receita = new Receita();
                receita.setId(rs.getLong("ID_TRANSACAO"));
                receita.setIdBanco(rs.getLong("ID_BANCO"));
                receita.setIdCategoria(rs.getLong("ID_CATEGORIA"));
                receita.setValor(rs.getDouble("VLR_TRANSACAO"));
                receita.setOrigem(rs.getString("ORIGEM_RECEITA"));

                // Converte a data do banco para LocalDate conforme seu modelo [cite: 126]
                if (rs.getDate("DT_TRANSACAO") != null) {
                    receita.setData(rs.getDate("DT_TRANSACAO").toLocalDate());
                }

                receita.setDescricao(rs.getString("DS_TRANSACAO"));
                receita.setTipo(rs.getString("TP_TRANSACAO"));

                // Adiciona o objeto preenchido na lista
                lista.add(receita);
            }
        }
        return lista;
    }

    public void remover(long id) throws SQLException {
        String sqlRec = "DELETE FROM T_FINSEVEN_RECEITA WHERE ID_TRANSACAO = ?";
        String sqlTrans = "DELETE FROM T_FINSEVEN_TRANSACAO WHERE ID_TRANSACAO = ?";
        try {
            conexao.setAutoCommit(false);
            try (PreparedStatement stmt = conexao.prepareStatement(sqlRec)) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = conexao.prepareStatement(sqlTrans)) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
            }
            conexao.commit();
        } catch (SQLException e) {
            conexao.rollback();
            throw e;
        }
    }


}
