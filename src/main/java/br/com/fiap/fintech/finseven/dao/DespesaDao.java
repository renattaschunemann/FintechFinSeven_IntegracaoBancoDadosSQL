package br.com.fiap.fintech.finseven.dao;

import br.com.fiap.fintech.finseven.factory.ConnectionFactory;
import br.com.fiap.fintech.finseven.model.Despesa;
import br.com.fiap.fintech.finseven.model.Receita;
import br.com.fiap.fintech.finseven.model.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DespesaDao {

    private Connection conexao;
    private TransacaoDao transacaoDao;

    public DespesaDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
        transacaoDao = new TransacaoDao();

    }
    public void insert(Despesa despesa) throws SQLException {
        transacaoDao.insert(despesa);

        PreparedStatement stmt = conexao.prepareStatement("INSERT INTO T_FINSEVEN_DESPESA (ID_TRANSACAO,FORMA_PGTO_DESPESA) VALUES (?, ?)");

        stmt.setLong(1, despesa.getId());
        stmt.setString(2, despesa.getFormaPagamento());

        stmt.executeUpdate();
        System.out.println("Despesa cadastrada com sucesso!");
        stmt.close();
    }

    public void update(Despesa despesa) throws SQLException {
        transacaoDao.update(despesa);

        PreparedStatement stmt = conexao.prepareStatement("UPDATE T_FINSEVEN_DESPESA SET FORMA_PGTO_DESPESA = ? " +
                "WHERE ID_TRANSACAO = ?");
        stmt.setString(1, despesa.getFormaPagamento());
        stmt.setLong(2, despesa.getId());

        int linhas = stmt.executeUpdate();
        if (linhas > 0) {
            System.out.println("Despesa atualizada com sucesso!");
        } else {
            System.out.println("Nenhuma despesa encontrada com o ID: " + despesa.getId());
        }
    }
    public Despesa searchById(long id) throws SQLException {
        Transacao transacao = transacaoDao.searchById(id);

        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM T_FINSEVEN_DESPESA WHERE ID_TRANSACAO = ?");
        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
       Despesa despesa = null;

        if (rs.next()) {
            despesa = new Despesa(transacao);
            despesa.setFormaPagamento(rs.getString("FORMA_PGTO_DESPESA"));
        }

        return despesa;
    }

    public List<Despesa> getAll() throws SQLException {
        List<Despesa> lista = new ArrayList<>();

        String sql = "SELECT * FROM T_FINSEVEN_DESPESA d INNER JOIN T_FINSEVEN_TRANSACAO t ON d.ID_TRANSACAO = t.ID_TRANSACAO ORDER BY t.DT_TRANSACAO DESC";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {


            while (rs.next()) {
               Despesa despesa = new Despesa();
                despesa.setId(rs.getLong("ID_TRANSACAO"));
                despesa.setIdBanco(rs.getLong("ID_BANCO"));
                despesa.setIdCategoria(rs.getLong("ID_CATEGORIA"));
                despesa.setValor(rs.getDouble("VLR_TRANSACAO"));
                despesa.setFormaPagamento(rs.getString("FORMA_PGTO_DESPESA"));


                if (rs.getDate("DT_TRANSACAO") != null) {
                    despesa.setData(rs.getDate("DT_TRANSACAO").toLocalDate());
                }

                despesa.setDescricao(rs.getString("DS_TRANSACAO"));
                despesa.setTipo(rs.getString("TP_TRANSACAO"));


                lista.add(despesa);
            }
        }
        return lista;
    }

    public void remove(long id) throws SQLException {
        String sqlDesp = "DELETE FROM T_FINSEVEN_DESPESA WHERE ID_TRANSACAO = ?";
        String sqlTrans = "DELETE FROM T_FINSEVEN_TRANSACAO WHERE ID_TRANSACAO = ?";
        try {
            conexao.setAutoCommit(false);
            try (PreparedStatement stmt = conexao.prepareStatement(sqlDesp)) {
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
