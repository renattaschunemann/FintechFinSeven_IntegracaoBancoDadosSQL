package br.com.fiap.fintech.finseven.view;

import br.com.fiap.fintech.finseven.dao.TransacaoDao;
import br.com.fiap.fintech.finseven.model.Transacao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TransacaoView {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            TransacaoDao dao = new TransacaoDao();
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n--- MENU TRANSAÇÕES FINSEVEN ---");
                System.out.println("1 - Cadastrar Transação");
                System.out.println("2 - Atualizar Transação");
                System.out.println("3 - Exibir todas as transações");
                System.out.println("4 - Pesquisar transação por ID");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scan.nextInt();
                scan.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        System.out.println("\n-- Novo Lançamento --");
                        System.out.print("ID do Banco: 3-Bradesco/4-Nubank/6-Mercado Pago");
                        long idBanco = scan.nextLong();
                        System.out.print("ID da Categoria: 1-Receita/2-Despesa/3-Investimento ");
                        long idCat = scan.nextLong();
                        System.out.print("Valor: ");
                        double valor = scan.nextDouble();
                        scan.nextLine();
                        System.out.print("Descrição: ");
                        String desc = scan.nextLine();
                        System.out.print("Tipo (RECEITA/DESPESA/INVESTIMENTO): ");
                        String tipo = scan.nextLine();

                        Transacao transacao = new Transacao();
                        transacao.setIdBanco(idBanco);
                        transacao.setIdCategoria(idCat);
                        transacao.setValor(valor);
                        transacao.setDescricao(desc);
                        transacao.setTipo(tipo);
                        transacao.setData(java.time.LocalDate.now());

                        dao.cadastrar(transacao);
                        break;

                    case 2:
                        System.out.println("\n-- Atualizar Transação --");
                        System.out.print("ID da transação que deseja alterar: ");
                        long idAlt = scan.nextLong();
                        System.out.print("Novo Valor: ");
                        double nValor = scan.nextDouble();
                        scan.nextLine(); // Buffer
                        System.out.print("Nova Descrição: ");
                        String nDesc = scan.nextLine();
                        System.out.print("Novo Tipo: ");
                        String nTipo = scan.nextLine();

                        Transacao tAtu = new Transacao();
                        tAtu.setId(idAlt);
                        tAtu.setValor(nValor);
                        tAtu.setDescricao(nDesc);
                        tAtu.setTipo(nTipo);
                        tAtu.setData(java.time.LocalDate.now());

                        dao.atualizar(tAtu);
                        break;

                    case 3:
                        System.out.println("\n-- Listagem Geral --");
                        // Aqui assume-se que você tem o método listar() no seu DAO
                        List<Transacao> lista = dao.pesquisarTodos();
                        if (lista.isEmpty()) {
                            System.out.println("Nenhuma transação encontrada.");
                        } else {
                            for (Transacao item : lista) {
                                System.out.println("ID: " + item.getId() + " | " + item.getDescricao() + " | R$ " + item.getValor());
                            }
                        }
                        break;

                    case 4:
                        System.out.println("\n-- Pesquisar por ID --");
                        System.out.print("Digite o ID da transação: ");
                        long idPesquisa = scan.nextLong();
                        scan.nextLine(); // Buffer

                        // Utiliza o método pesquisar que você criou no DAO
                        Transacao tEncontrada = dao.pesquisar(idPesquisa);

                        if (tEncontrada != null) {
                            System.out.println("\n--- Registro Localizado ---");
                            System.out.println("ID: " + tEncontrada.getId());
                            System.out.println("Valor: R$ " + tEncontrada.getValor());
                            System.out.println("Data: " + tEncontrada.getData());
                            System.out.println("Descrição: " + tEncontrada.getDescricao());
                            System.out.println("Tipo: " + tEncontrada.getTipo());
                        } else {
                            System.out.println("\n[!] Transação com ID " + idPesquisa + " não encontrada.");
                        }
                        break;

                    case 0:
                        System.out.println("Encerrando módulo...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro de Banco de Dados: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            scan.close();
        }
    }
}