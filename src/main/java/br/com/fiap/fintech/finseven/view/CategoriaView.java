package br.com.fiap.fintech.finseven.view;

import br.com.fiap.fintech.finseven.dao.CategoriaDao;
import br.com.fiap.fintech.finseven.model.Categoria;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CategoriaView {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            CategoriaDao dao = new CategoriaDao();
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n--- MENU CATEGORIAS FINSEVEN ---");
                System.out.println("1 - Cadastrar Categoria");
                System.out.println("2 - Atualizar Categoria");
                System.out.println("3 - Exibir todas as categorias");
                System.out.println("4 - Pesquisar categoria por ID");
                System.out.println("5 - Remover Categoria");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scan.nextInt();
                scan.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("\n-- Nova Categoria --");
                        System.out.print("Descrição da Categoria (ex: Alimentação): ");
                        String desc = scan.nextLine();
                        System.out.print("Tipo de Movimento (RECEITA/DESPESA): ");
                        String tipoMov = scan.nextLine().toUpperCase();

                        Categoria novaCategoria = new Categoria(desc, tipoMov);
                        dao.insert(novaCategoria);
                        System.out.println("Categoria cadastrada com sucesso!");
                        break;

                    case 2:
                        System.out.println("\n-- Atualizar Categoria --");
                        System.out.print("ID da categoria que deseja alterar: ");
                        long idAlt = scan.nextLong();
                        scan.nextLine();
                        System.out.print("Nova Descrição: ");
                        String nDesc = scan.nextLine();
                        System.out.print("Novo Tipo de Movimento (RECEITA/DESPESA): ");
                        String nTipo = scan.nextLine().toUpperCase();

                        Categoria categoriaAtual = new Categoria(idAlt, nDesc, nTipo);
                        dao.update(categoriaAtual);
                        System.out.println("Categoria atualizada com sucesso!");
                        break;

                    case 3:
                        System.out.println("\n-- Categorias Cadastradas --");
                        List<Categoria> lista = dao.getAll();
                        if (lista.isEmpty()) {
                            System.out.println("Nenhuma categoria encontrada.");
                        } else {
                            for (Categoria item : lista) {
                                System.out.println("ID: " + item.getId() + " | Descrição: " + item.getDescricao() + " | Tipo: " + item.getTiposTransacao());
                            }
                        }
                        break;

                    case 4:
                        System.out.println("\n-- Pesquisar Categoria por ID --");
                        System.out.print("Digite o ID: ");
                        long idPesquisa = scan.nextLong();
                        scan.nextLine(); // Buffer

                        Categoria categoriaEncontrada = dao.searchById(idPesquisa);

                        if (categoriaEncontrada != null) {
                            System.out.println("\n--- Registro Localizado ---");
                            System.out.println("ID: " + categoriaEncontrada.getId());
                            System.out.println("Descrição: " + categoriaEncontrada.getDescricao());
                            System.out.println("Tipo: " + categoriaEncontrada.getTiposTransacao());
                        } else {
                            System.out.println("\n[!] Categoria com ID " + idPesquisa + " não encontrada.");
                        }
                        break;

                    case 5:
                        System.out.print("\nID da categoria para remover: ");
                        long idRem = scan.nextLong();
                        dao.remove(idRem);
                        System.out.println("Removido com sucesso!");
                        break;

                    case 0:
                        System.out.println("Encerrando módulo de Categorias...");
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