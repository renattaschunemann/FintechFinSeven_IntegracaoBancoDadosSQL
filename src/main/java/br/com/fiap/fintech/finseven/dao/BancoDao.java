package br.com.fiap.fintech.finseven.dao;

import br.com.fiap.fintech.finseven.factory.ConnectionFactory;
import br.com.fiap.fintech.finseven.model.Banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BancoDao {

    private Connection conexao;

    public BancoDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }

    public void insert(Banco banco) throws SQLException {
        String sql = "INSERT INTO T_FINSEVEN_BANCO (ID_BANCO, NUM_CONTA, SD_CONTA, TP_CONTA, NUM_AGENCIA, NM_BANCO) " +
                "VALUES (SQ_FINSEVEN_BANCO.nextval, ?, ?, ?, ?, ?)";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, banco.getConta());
            stm.setDouble(2, banco.getSaldo());
            stm.setString(3, banco.getTipo());
            stm.setString(4, banco.getAgencia());
            stm.setString(5, banco.getNome());
            stm.executeUpdate();
        }
    }

    public List<Banco> getAll() throws SQLException {
        List<Banco> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_FINSEVEN_BANCO ORDER BY NM_BANCO";

        try (PreparedStatement stm = conexao.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Banco banco = new Banco();
                banco.setIdBanco(rs.getLong("ID_BANCO"));
                banco.setConta(rs.getString("NUM_CONTA"));
                banco.setSaldo(rs.getDouble("SD_CONTA"));
                banco.setTipo(rs.getString("TP_CONTA"));
                banco.setAgencia(rs.getString("NUM_AGENCIA"));
                banco.setNome(rs.getString("NM_BANCO"));
                lista.add(banco);
            }
        }
        return lista;
    }

    public void update(Banco banco) throws SQLException {
        String sql = "UPDATE T_FINSEVEN_BANCO SET NUM_CONTA = ?, SD_CONTA = ?, TP_CONTA = ?, " +
                "NUM_AGENCIA = ?, NM_BANCO = ? WHERE ID_BANCO = ?";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, banco.getConta());
            stm.setDouble(2, banco.getSaldo());
            stm.setString(3, banco.getTipo());
            stm.setString(4, banco.getAgencia());
            stm.setString(5, banco.getNome());
            stm.setLong(6, banco.getIdBanco());
            stm.executeUpdate();
        }
    }

    public void remove(long id) throws SQLException {
        String sql = "DELETE FROM T_FINSEVEN_BANCO WHERE ID_BANCO = ?";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        }
    }
}