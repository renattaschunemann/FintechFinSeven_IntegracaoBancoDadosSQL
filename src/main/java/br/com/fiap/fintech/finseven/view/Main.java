package br.com.fiap.fintech.finseven.view;
import br.com.fiap.fintech.finseven.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
public class Main
{
    public static void main( String[] args )
    {
        try {
            Connection conexao = ConnectionFactory.getConnection();
            System.out.println("Conexão realizada!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}