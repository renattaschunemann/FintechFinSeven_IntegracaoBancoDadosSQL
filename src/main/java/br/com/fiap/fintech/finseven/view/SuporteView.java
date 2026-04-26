package br.com.fiap.fintech.finseven.view;

import br.com.fiap.fintech.finseven.dao.SuporteDao;
import br.com.fiap.fintech.finseven.model.Suporte;
import br.com.fiap.fintech.finseven.model.Usuario;

import java.util.List;
import java.util.Scanner;

public class SuporteView {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            SuporteDao dao = new SuporteDao();
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n--- CENTRAL DE SUPORTE FINSEVEN ---");
                System.out.println("1 - Abrir Novo Chamado");
                System.out.println("2 - Listar Chamados");
                System.out.println("3 - Alterar Chamado");
                System.out.println("4 - Excluir Chamado");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scan.nextInt();
                scan.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("\n-- Novo Chamado --");
                        System.out.print("ID do Usuário: ");
                        long idUser = scan.nextLong(); scan.nextLine();
                        System.out.print("Assunto: ");
                        String assunto = scan.nextLine();
                        System.out.print("Mensagem: ");
                        String msg = scan.nextLine();

                        Usuario u = new Usuario();
                        u.setId(idUser);
                        dao.cadastrar(new Suporte(assunto, msg, u));
                        System.out.println("Chamado aberto com sucesso!");
                        break;

                    case 2:
                        System.out.println("\n-- Lista de Chamados --");
                        List<Suporte> lista = dao.listar();
                        for (Suporte s : lista) {
                            System.out.println("Usuário: " + s.getUsuario().getNome() +
                                    " | Assunto: " + s.getAssunto() +
                                    " | Msg: " + s.getMensagem());
                        }
                        break;

                    case 3:
                        System.out.print("\nID do Chamado para alterar: ");
                        long idAlt = scan.nextLong(); scan.nextLine();
                        System.out.print("Novo Assunto: ");
                        String nAssunto = scan.nextLine();
                        System.out.print("Nova Mensagem: ");
                        String nMsg = scan.nextLine();

                        dao.atualizar(idAlt, nAssunto, nMsg);
                        System.out.println("Chamado atualizado!");
                        break;

                    case 4:
                        System.out.print("\nID do Chamado para excluir: ");
                        long idRem = scan.nextLong();
                        dao.remover(idRem);
                        System.out.println("Chamado excluído!");
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro no Suporte: " + e.getMessage());
        } finally {
            scan.close();
        }
    }
}