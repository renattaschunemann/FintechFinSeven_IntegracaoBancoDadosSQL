package br.com.fiap.fintech.finseven.view;

import br.com.fiap.fintech.finseven.dao.ReceitaDao;
import br.com.fiap.fintech.finseven.model.Receita;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReceitaView {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ReceitaDao dao;

        try {
            dao = new ReceitaDao();
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n--- CRUD RECEITAS FINSEVEN ---");
                System.out.println("1 - Cadastrar Receita");
                System.out.println("2 - Listar Todas");
                System.out.println("3 - Alterar Receita");
                System.out.println("4 - Remover Receita");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                opcao = scan.nextInt();
                scan.nextLine(); // Limpar buffer

                switch (opcao) {
                    case 1:
                        System.out.println("\n-- Novo Cadastro --");
                        System.out.print("Valor: "); double vlr = scan.nextDouble(); scan.nextLine();
                        System.out.print("Descrição: "); String desc = scan.nextLine();
                        System.out.print("Origem: "); String orig = scan.nextLine();
                        System.out.print("ID Banco: "); long banco = scan.nextLong();
                        System.out.print("ID Categoria: "); long cat = scan.nextLong();

                        Receita nova = new Receita(banco, cat, vlr, LocalDate.now(), desc, "RECEITA", orig);
                        dao.cadastrar(nova);
                        System.out.println("Cadastrado com sucesso!");
                        break;

                    case 2:
                        System.out.println("\n-- Listagem de Receitas --");
                        List<Receita> lista = dao.listar();
                        for (Receita r : lista) {
                            System.out.println("ID: " + r.getId() + " | " + r.getDescricao() + " | R$ " + r.getValor() + " | Origem: " + r.getOrigem());
                        }
                        break;

                    case 3:
                        System.out.print("\nID da receita para alterar: ");
                        long idAlt = scan.nextLong(); scan.nextLine();
                        System.out.print("Novo Valor: "); double nVlr = scan.nextDouble(); scan.nextLine();
                        System.out.print("Nova Descrição: "); String nDesc = scan.nextLine();
                        System.out.print("Nova Origem: "); String nOrig = scan.nextLine();

                        Receita alt = new Receita();
                        alt.setId(idAlt);
                        alt.setValor(nVlr);
                        alt.setDescricao(nDesc);
                        alt.setOrigem(nOrig);
                        dao.atualizar(alt);
                        System.out.println("Alterado com sucesso!");
                        break;

                    case 4:
                        System.out.print("\nID da receita para remover: ");
                        long idRem = scan.nextLong();
                        dao.remover(idRem);
                        System.out.println("Removido com sucesso!");
                        break;

                    case 0:
                        System.out.println("Encerrando...");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }
}