package br.com.fiap.fintech.finseven.dao;

import br.com.fiap.fintech.finseven.factory.ConnectionFactory;
import br.com.fiap.fintech.finseven.model.Suporte;
import br.com.fiap.fintech.finseven.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuporteDao {

    private Connection conexao;

    public SuporteDao() throws SQLException {
        this.conexao = ConnectionFactory.getConnection();
    }

    public void cadastrar(Suporte suporte) throws SQLException {
        String sql = "INSERT INTO T_FINSEVEN_SUPORTE (ID_SUPORTE, DS_ASSUNTO, DS_MENSAGEM, ID_USUARIO) " +
                "VALUES (SQ_FINSEVEN_SUPORTE.NEXTVAL, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, suporte.getAssunto());
            stmt.setString(2, suporte.getMensagem());
            stmt.setLong(3, suporte.getUsuario().getId());
            stmt.executeUpdate();
        }
    }

    public List<Suporte> listar() throws SQLException {
        List<Suporte> lista = new ArrayList<>();
        String sql = "SELECT S.*, U.NM_USUARIO FROM T_FINSEVEN_SUPORTE S " +
                "INNER JOIN T_FINSEVEN_USUARIO U ON S.ID_USUARIO = U.ID_USUARIO";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getLong("ID_USUARIO"));
                user.setNome(rs.getString("NM_USUARIO"));

                Suporte sup = new Suporte();
                sup.setAssunto(rs.getString("DS_ASSUNTO"));
                sup.setMensagem(rs.getString("DS_MENSAGEM"));
                sup.setUsuario(user);

                lista.add(sup);
            }
        }
        return lista;
    }

    public void atualizar(long id, String novoAssunto, String novaMsg) throws SQLException {
        String sql = "UPDATE T_FINSEVEN_SUPORTE SET DS_ASSUNTO = ?, DS_MENSAGEM = ? WHERE ID_SUPORTE = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, novoAssunto);
            stmt.setString(2, novaMsg);
            stmt.setLong(3, id);
            stmt.executeUpdate();
        }
    }

    public void remover(long id) throws SQLException {
        String sql = "DELETE FROM T_FINSEVEN_SUPORTE WHERE ID_SUPORTE = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}