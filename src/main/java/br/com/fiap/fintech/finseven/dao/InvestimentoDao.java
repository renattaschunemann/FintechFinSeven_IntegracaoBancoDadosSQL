package br.com.fiap.fintech.finseven.dao;

import br.com.fiap.fintech.finseven.factory.ConnectionFactory;
import br.com.fiap.fintech.finseven.model.Investimento;
import br.com.fiap.fintech.finseven.model.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoDao {

    private Connection conexao;
    private TransacaoDao transacaoDao;

    public InvestimentoDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
        transacaoDao = new TransacaoDao();

    }

    public void insert(Investimento investimento) throws SQLException {
        transacaoDao.insert(investimento);

        PreparedStatement stmt = conexao.prepareStatement("INSERT INTO T_FINSEVEN_INVESTIMENTO (ID_TRANSACAO, TX_INVESTIMENTO) VALUES (?, ?)");

        stmt.setLong(1, investimento.getId());
        stmt.setDouble(2, investimento.getTaxa());

        stmt.executeUpdate();
        System.out.println("Investimento cadastrado com sucesso!");
        stmt.close();
    }

    public void update(Investimento investimento) throws SQLException {
        transacaoDao.update(investimento);

        PreparedStatement stmt = conexao.prepareStatement("UPDATE T_FINSEVEN_INVESTIMENTO SET TX_INVESTIMENTO = ? " +
                "WHERE ID_TRANSACAO = ?");
        stmt.setDouble(1, investimento.getTaxa());
        stmt.setLong(2, investimento.getId());

        int linhas = stmt.executeUpdate();
        if (linhas > 0) {
            System.out.println("Investimento atualizado com sucesso!");
        } else {
            System.out.println("Nenhum investimento encontrado com o ID: " + investimento.getId());
        }
    }

    public Investimento searchById(long id) throws SQLException {
        Transacao transacao = transacaoDao.searchById(id);

        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM T_FINSEVEN_INVESTIMENTO WHERE ID_TRANSACAO = ?");
        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        Investimento investimento = null;

        if (rs.next()) {
            investimento = new Investimento(transacao);
            investimento.setTaxa(rs.getDouble("TX_INVESTIMENTO"));
        }

        return investimento;
    }

    public List<Investimento> getAll() throws SQLException {
        List<Investimento> lista = new ArrayList<>();
        // SQL para selecionar todos os registros das tabelas do join entre transacao e investimento
        String sql = "SELECT * FROM T_FINSEVEN_INVESTIMENTO i INNER JOIN T_FINSEVEN_TRANSACAO t ON i.ID_TRANSACAO = t.ID_TRANSACAO ORDER BY t.DT_TRANSACAO DESC";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // O while percorre todas as linhas retornadas pelo banco
            while (rs.next()) {
                Investimento investimento = new Investimento();
                investimento.setId(rs.getLong("ID_TRANSACAO"));
                investimento.setIdBanco(rs.getLong("ID_BANCO"));
                investimento.setIdCategoria(rs.getLong("ID_CATEGORIA"));
                investimento.setValor(rs.getDouble("VLR_TRANSACAO"));
                investimento.setTaxa(rs.getDouble("TX_INVESTIMENTO"));

                // Converte a data do banco para LocalDate conforme seu modelo [cite: 126]
                if (rs.getDate("DT_TRANSACAO") != null) {
                    investimento.setData(rs.getDate("DT_TRANSACAO").toLocalDate());
                }

                investimento.setDescricao(rs.getString("DS_TRANSACAO"));
                investimento.setTipo(rs.getString("TP_TRANSACAO"));

                // Adiciona o objeto preenchido na lista
                lista.add(investimento);
            }
        }
        return lista;
    }

    public void remove(long id) throws SQLException {
        String sqlInvest = "DELETE FROM T_FINSEVEN_INVESTIMENTO WHERE ID_TRANSACAO = ?";
        String sqlTrans = "DELETE FROM T_FINSEVEN_TRANSACAO WHERE ID_TRANSACAO = ?";
        try {
            conexao.setAutoCommit(false);
            try (PreparedStatement stmt = conexao.prepareStatement(sqlInvest)) {
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
