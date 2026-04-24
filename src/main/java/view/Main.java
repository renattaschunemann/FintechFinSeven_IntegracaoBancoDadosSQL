package view;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
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