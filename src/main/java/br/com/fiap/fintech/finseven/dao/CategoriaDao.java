package br.com.fiap.fintech.finseven.dao;

import br.com.fiap.fintech.finseven.factory.ConnectionFactory;
import br.com.fiap.fintech.finseven.model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    private Connection conexao;

    public CategoriaDao() throws SQLException {
        this.conexao = ConnectionFactory.getConnection();
    }

    public void insert(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO T_FINSEVEN_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, TP_MOVIMENTO) VALUES (SQ_FINSEVEN_CATEGORIA.NEXTVAL, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, categoria.getDescricao());
            stmt.setString(2, categoria.getTiposTransacao());
            stmt.execute();
        }
    }

    public List<Categoria> getAll() throws SQLException {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_FINSEVEN_CATEGORIA ORDER BY DS_CATEGORIA";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Categoria(rs.getLong("ID_CATEGORIA"), rs.getString("DS_CATEGORIA"), rs.getString("TP_MOVIMENTO")));
            }
        }
        return lista;
    }

    public Categoria searchById(long id) throws SQLException {
        Categoria categoria = null;
        String sql = "SELECT * FROM T_FINSEVEN_CATEGORIA WHERE ID_CATEGORIA = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = new Categoria(rs.getLong("ID_CATEGORIA"), rs.getString("DS_CATEGORIA"), rs.getString("TP_MOVIMENTO"));
                }
            }
        }
        return categoria;
    }

    public void update(Categoria categoria) throws SQLException {
        String sql = "UPDATE T_FINSEVEN_CATEGORIA SET DS_CATEGORIA = ?, TP_MOVIMENTO = ? WHERE ID_CATEGORIA = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, categoria.getDescricao());
            stmt.setString(2, categoria.getTiposTransacao());
            stmt.setLong(3, categoria.getId());
            stmt.executeUpdate();
        }
    }

    public void remove(long id) throws SQLException {
        String sql = "DELETE FROM T_FINSEVEN_CATEGORIA WHERE ID_CATEGORIA = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public void closeConnection() throws SQLException {
        if (conexao != null && !conexao.isClosed()) conexao.close();
    }
}