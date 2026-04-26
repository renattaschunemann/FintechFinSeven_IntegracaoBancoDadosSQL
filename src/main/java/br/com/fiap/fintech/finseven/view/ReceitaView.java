package br.com.fiap.fintech.finseven.view;

import br.com.fiap.fintech.finseven.dao.ReceitaDao;
import br.com.fiap.fintech.finseven.model.Receita;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ReceitaView {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            ReceitaDao dao = new ReceitaDao();
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n--- MENU RECEITAS FINSEVEN ---");
                System.out.println("1 - Cadastrar Receita");
                System.out.println("2 - Atualizar Receita");
                System.out.println("3 - Exibir todas as receitas");
                System.out.println("4 - Pesquisar receitas por ID");
                System.out.println("5 - Remover Receita");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scan.nextInt();
                scan.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        System.out.println("\n-- Novo Lançamento --");
                        System.out.print("ID do Banco: 3-Bradesco/4-Nubank/6-Mercado Pago");
                        long idBanco = scan.nextLong();
                        System.out.print("ID da Categoria do tipo Receita:");
                        long idCat = scan.nextLong();
                        System.out.print("Valor: ");
                        double valor = scan.nextDouble();
                        scan.nextLine();
                        System.out.print("Descrição: ");
                        String desc = scan.nextLine();
                        System.out.print("Origem da Receita: ");
                        String origem = scan.nextLine();
                        String tipo = "RECEITA";

                        Receita receita = new Receita();
                        receita.setIdBanco(idBanco);
                        receita.setIdCategoria(idCat);
                        receita.setValor(valor);
                        receita.setDescricao(desc);
                        receita.setTipo(tipo);
                        receita.setData(java.time.LocalDate.now());
                        receita.setOrigem(origem);

                        dao.insert(receita);
                        break;

                    case 2:
                        System.out.println("\n-- Atualizar Receita --");
                        System.out.print("ID da receita que deseja alterar: ");
                        long idAlt = scan.nextLong();
                        System.out.print("Novo Valor: ");
                        double nValor = scan.nextDouble();
                        scan.nextLine(); // Buffer
                        System.out.print("Nova Descrição: ");
                        String nDesc = scan.nextLine();
                        System.out.print("Nova Origem: ");
                        String nOrigem = scan.nextLine();

                        Receita receitaAtual = new Receita();
                        receitaAtual.setId(idAlt);
                        receitaAtual.setValor(nValor);
                        receitaAtual.setDescricao(nDesc);
                        receitaAtual.setData(java.time.LocalDate.now());
                        receitaAtual.setOrigem(nOrigem);

                        dao.update(receitaAtual);
                        break;

                    case 3:
                        System.out.println("\n-- Receitas Cadastradas--");
                        // Aqui assume-se que você tem o método listar() no seu DAO
                        List<Receita> lista = dao.getAll();
                        if (lista.isEmpty()) {
                            System.out.println("Nenhuma receita encontrada.");
                        } else {
                            System.out.println("\n---Total de Registros Localizado: " + lista.size() + " ---");
                            for (Receita item : lista) {
                                //System.out.println("ID: " + item.getId() + " | " + item.getDescricao() + " | R$ " + item.getValor());
                                System.out.println("\n----------------");
                                System.out.println("ID: " + item.getId());
                                System.out.println("Valor: R$ " + item.getValor());
                                System.out.println("Data: " + item.getData());
                                System.out.println("Descrição: " + item.getDescricao());
                                System.out.println("Tipo: " + item.getTipo());
                                System.out.println("Origem: " + item.getOrigem());
                            }
                        }
                        break;

                    case 4:
                        System.out.println("\n-- Pesquisar por ID --");
                        System.out.print("Digite o ID da receita: ");
                        long idPesquisa = scan.nextLong();
                        scan.nextLine(); // Buffer

                        // Utiliza o método pesquisar que você criou no DAO
                        Receita receitaEncontrada = dao.searchById(idPesquisa);

                        if (receitaEncontrada != null) {
                            System.out.println("\n--- Registro Localizado ---");
                            System.out.println("ID: " + receitaEncontrada.getId());
                            System.out.println("Valor: R$ " + receitaEncontrada.getValor());
                            System.out.println("Data: " + receitaEncontrada.getData());
                            System.out.println("Descrição: " + receitaEncontrada.getDescricao());
                            System.out.println("Tipo: " + receitaEncontrada.getTipo());
                            System.out.println("Origem: " + receitaEncontrada.getOrigem());
                        } else {
                            System.out.println("\n[!] Receita com ID " + idPesquisa + " não encontrada.");
                        }
                        break;


                    case 5:
                        System.out.print("\nID da receita para remover: ");
                        long idRem = scan.nextLong();
                        dao.remover(idRem);
                        System.out.println("Removido com sucesso!");
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
