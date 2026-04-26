package br.com.fiap.fintech.finseven.dao;

import br.com.fiap.fintech.finseven.factory.ConnectionFactory;
import br.com.fiap.fintech.finseven.model.Transacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TransacaoDao {
    private Connection conexao;


    public TransacaoDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }

    public Transacao cadastrar(Transacao transacao) throws SQLException {

        String[] idMapeado = {"ID_TRANSACAO"};
        PreparedStatement stmt = conexao.prepareStatement("INSERT INTO T_FINSEVEN_TRANSACAO (ID_TRANSACAO, ID_BANCO, ID_CATEGORIA, VLR_TRANSACAO, DT_TRANSACAO, DS_TRANSACAO, TP_TRANSACAO) " +
                "VALUES (SQ_FINSEVEN_TRANSACAO.NEXTVAL, ?, ?, ?, ?, ?, ?)", idMapeado);

        stmt.setLong(1, transacao.getIdBanco());
        stmt.setLong(2, transacao.getIdCategoria());
        stmt.setDouble(3, transacao.getValor());
        stmt.setDate(4, Date.valueOf(String.valueOf(transacao.getData())));
        stmt.setString(5, transacao.getDescricao());
        stmt.setString(6, transacao.getTipo());


        stmt.executeUpdate();
        System.out.println("Transação cadastrada com sucesso!");

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            transacao.setId(generatedKeys.getLong(1));
        }

        stmt.close();

        return transacao;

    }

    public void atualizar(Transacao transacao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("UPDATE T_FINSEVEN_TRANSACAO SET VLR_TRANSACAO = ?, DT_TRANSACAO = ?, DS_TRANSACAO = ? " +
                "WHERE ID_TRANSACAO = ?");
        stmt.setDouble(1, transacao.getValor());
        stmt.setDate(2, new java.sql.Date(transacao.getData().getTime()));
        stmt.setString(3, transacao.getDescricao());
        stmt.setLong(4, transacao.getId());

        int linhas = stmt.executeUpdate();
        if (linhas > 0) {
            System.out.println("Transação atualizada com sucesso!");
        } else {
            System.out.println("Nenhuma transação encontrada com o ID: " + transacao.getId());
        }
    }

    public Transacao pesquisar(long id) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM T_FINSEVEN_TRANSACAO WHERE ID_TRANSACAO = ?");
        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        Transacao transacao = null;

        if (rs.next()) {
            transacao = new Transacao();
            transacao.setId(rs.getLong("ID_TRANSACAO"));
            transacao.setIdBanco(rs.getLong("ID_BANCO"));
            transacao.setIdCategoria(rs.getLong("ID_CATEGORIA"));
            transacao.setValor(rs.getDouble("VLR_TRANSACAO"));
            transacao.setData(rs.getDate("DT_TRANSACAO").toLocalDate());
            transacao.setDescricao(rs.getString("DS_TRANSACAO"));
            transacao.setTipo(rs.getString("TP_TRANSACAO"));
        }

        return transacao;
    }

    public List<Transacao> pesquisarTodos() throws SQLException {
        List<Transacao> lista = new ArrayList<>();
        // SQL para selecionar todos os registros da tabela
        String sql = "SELECT * FROM T_FINSEVEN_TRANSACAO ORDER BY DT_TRANSACAO DESC";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // O while percorre todas as linhas retornadas pelo banco
            while (rs.next()) {
                Transacao transacao = new Transacao();
                transacao.setId(rs.getLong("ID_TRANSACAO"));
                transacao.setIdBanco(rs.getLong("ID_BANCO"));
                transacao.setIdCategoria(rs.getLong("ID_CATEGORIA"));
                transacao.setValor(rs.getDouble("VLR_TRANSACAO"));

                // Converte a data do banco para LocalDate conforme seu modelo [cite: 126]
                if (rs.getDate("DT_TRANSACAO") != null) {
                    transacao.setData(rs.getDate("DT_TRANSACAO").toLocalDate());
                }

                transacao.setDescricao(rs.getString("DS_TRANSACAO"));
                transacao.setTipo(rs.getString("TP_TRANSACAO"));

                // Adiciona o objeto preenchido na lista
                lista.add(transacao);
            }
        }
        return lista;
    }
}