package br.com.fiap.fintech.finseven.view;

import br.com.fiap.fintech.finseven.dao.UsuarioDao;
import br.com.fiap.fintech.finseven.model.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioView
{
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);

        try {

            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n--- MENU USUÁRIO FINSEVEN ---");
                System.out.println("1 - Cadastrar Novo Usuário");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scan.nextInt();
                scan.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        System.out.println("\n-- Cadastro de Usuário --");
                        System.out.print("Nome completo: ");
                        String nome = scan.nextLine();

                        System.out.print("CPF (apenas números): ");
                        long cpf = scan.nextLong();
                        scan.nextLine(); // Limpar buffer

                        System.out.print("E-mail: ");
                        String email = scan.nextLine();

                        UsuarioDao dao = new UsuarioDao();
                        Usuario usuario = new Usuario(nome,cpf,email);
                        dao.cadastrar(usuario);
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scan.close(); // Fechando o scan
        }
    }
}