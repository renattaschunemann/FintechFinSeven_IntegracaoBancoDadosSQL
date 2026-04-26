package br.com.fiap.fintech.finseven.view;

import br.com.fiap.fintech.finseven.dao.BancoDao;
import br.com.fiap.fintech.finseven.model.Banco;

import java.util.List;
import java.util.Scanner;

public class BancoView {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            BancoDao dao = new BancoDao();
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n--- GERENCIAMENTO DE CONTAS BANCÁRIAS ---");
                System.out.println("1 - Cadastrar Banco/Conta");
                System.out.println("2 - Listar Todas as Contas");
                System.out.println("3 - Alterar Dados da Conta");
                System.out.println("4 - Remover Conta");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scan.nextInt();
                scan.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("\n-- Novo Cadastro --");
                        System.out.print("Nome do Banco: "); String nome = scan.nextLine();
                        System.out.print("Agência: "); String agencia = scan.nextLine();
                        System.out.print("Número da Conta: "); String conta = scan.nextLine();
                        System.out.print("Tipo (Corrente/Poupança): "); String tipo = scan.nextLine();
                        System.out.print("Saldo Inicial: "); double saldo = scan.nextDouble();

                        dao.insert(new Banco(nome, agencia, conta, tipo, saldo));
                        System.out.println("Conta cadastrada com sucesso!");
                        break;

                    case 2:
                        System.out.println("\n-- Contas Cadastradas --");
                        List<Banco> lista = dao.getAll();
                        for (Banco b : lista) {
                            System.out.println("ID: " + b.getIdBanco() + " | Banco: " + b.getNome() +
                                    " | Ag: " + b.getAgencia() + " | Cc: " + b.getConta() +
                                    " | Saldo: R$ " + b.getSaldo());
                        }
                        break;

                    case 3:
                        System.out.print("\nID do Banco para alterar: ");
                        long idAlt = scan.nextLong(); scan.nextLine();

                        System.out.println("Informe os novos dados:");
                        System.out.print("Nome do Banco: "); String nNome = scan.nextLine();
                        System.out.print("Agência: "); String nAgencia = scan.nextLine();
                        System.out.print("Conta: "); String nConta = scan.nextLine();
                        System.out.print("Tipo: "); String nTipo = scan.nextLine();
                        System.out.print("Saldo Atual: "); double nSaldo = scan.nextDouble();

                        Banco bancoAlt = new Banco(idAlt, nNome, nAgencia, nConta, nTipo, nSaldo);
                        dao.update(bancoAlt);
                        System.out.println("Dados atualizados com sucesso!");
                        break;

                    case 4:
                        System.out.print("\nID do Banco para remover: ");
                        long idRem = scan.nextLong();
                        dao.remove(idRem);
                        System.out.println("Conta removida com sucesso!");
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro na operação: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }
}