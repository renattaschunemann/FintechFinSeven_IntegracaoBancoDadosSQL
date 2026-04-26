package br.com.fiap.fintech.finseven.dao;

import br.com.fiap.fintech.finseven.factory.ConnectionFactory;
import br.com.fiap.fintech.finseven.model.Receita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDao {

    private Connection conexao;

    public ReceitaDao() throws SQLException {
        this.conexao = ConnectionFactory.getConnection();
    }

    public void cadastrar(Receita receita) throws SQLException {
        String sqlTrans = "INSERT INTO T_FINSEVEN_TRANSACAO (ID_TRANSACAO, ID_BANCO, ID_CATEGORIA, VLR_TRANSACAO, DT_TRANSACAO, DS_TRANSACAO, TP_TRANSACAO) VALUES (SQ_FINSEVEN_TRANSACAO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        String sqlRec = "INSERT INTO T_FINSEVEN_RECEITA (ID_TRANSACAO, ORIGEM_RECEITA) VALUES (SQ_FINSEVEN_TRANSACAO.CURRVAL, ?)";

        try {
            conexao.setAutoCommit(false);
            try (PreparedStatement stmt = conexao.prepareStatement(sqlTrans)) {
                stmt.setLong(1, receita.getIdBanco());
                stmt.setLong(2, receita.getIdCategoria());
                stmt.setDouble(3, receita.getValor());
                stmt.setDate(4, Date.valueOf(receita.getData()));
                stmt.setString(5, receita.getDescricao());
                stmt.setString(6, receita.getTipo());
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = conexao.prepareStatement(sqlRec)) {
                stmt.setString(1, receita.getOrigem());
                stmt.executeUpdate();
            }
            conexao.commit();
        } catch (SQLException e) {
            conexao.rollback();
            throw e;
        }
    }

    public List<Receita> listar() throws SQLException {
        List<Receita> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_FINSEVEN_TRANSACAO T INNER JOIN T_FINSEVEN_RECEITA R ON T.ID_TRANSACAO = R.ID_TRANSACAO";
        try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Receita r = new Receita();
                r.setId(rs.getLong("ID_TRANSACAO"));
                r.setValor(rs.getDouble("VLR_TRANSACAO"));
                r.setDescricao(rs.getString("DS_TRANSACAO"));
                r.setOrigem(rs.getString("ORIGEM_RECEITA"));
                r.setData(rs.getDate("DT_TRANSACAO").toLocalDate());
                lista.add(r);
            }
        }
        return lista;
    }


    public void atualizar(Receita receita) throws SQLException {
        String sqlTrans = "UPDATE T_FINSEVEN_TRANSACAO SET VLR_TRANSACAO = ?, DS_TRANSACAO = ? WHERE ID_TRANSACAO = ?";
        String sqlRec = "UPDATE T_FINSEVEN_RECEITA SET ORIGEM_RECEITA = ? WHERE ID_TRANSACAO = ?";
        try {
            conexao.setAutoCommit(false);
            try (PreparedStatement stmt = conexao.prepareStatement(sqlTrans)) {
                stmt.setDouble(1, receita.getValor());
                stmt.setString(2, receita.getDescricao());
                stmt.setLong(3, receita.getId());
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = conexao.prepareStatement(sqlRec)) {
                stmt.setString(1, receita.getOrigem());
                stmt.setLong(2, receita.getId());
                stmt.executeUpdate();
            }
            conexao.commit();
        } catch (SQLException e) {
            conexao.rollback();
            throw e;
        }
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