package br.com.fiap.fintech.finseven.view;

import br.com.fiap.fintech.finseven.dao.DespesaDao;
import br.com.fiap.fintech.finseven.model.Despesa;
import br.com.fiap.fintech.finseven.model.Receita;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DespesaView {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            DespesaDao dao = new DespesaDao();
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n--- MENU DESPESAS FINSEVEN ---");
                System.out.println("1 - Cadastrar Despesa");
                System.out.println("2 - Atualizar Despesa");
                System.out.println("3 - Exibir todas as despesas");
                System.out.println("4 - Pesquisar despesas por ID");
                System.out.println("5 - Remover despesa");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scan.nextInt();
                scan.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.println("\n-- Novo Lançamento --");
                        System.out.print("ID do Banco: 3-Bradesco/4-Nubank/6-Mercado Pago");
                        long idBanco = scan.nextLong();
                        System.out.print("ID da Categoria do tipo Despesa:");
                        long idCat = scan.nextLong();
                        System.out.print("Valor: ");
                        double valor = scan.nextDouble();
                        scan.nextLine();
                        System.out.print("Descrição: ");
                        String desc = scan.nextLine();
                        System.out.print("Forma de pagamento da Despesa: ");
                        String formaPagamento = scan.nextLine();
                        String tipo = "DESPESA";

                        Despesa despesa = new Despesa();
                        despesa.setIdBanco(idBanco);
                        despesa.setIdCategoria(idCat);
                        despesa.setValor(valor);
                        despesa.setDescricao(desc);
                        despesa.setTipo(tipo);
                        despesa.setData(java.time.LocalDate.now());
                        despesa.setFormaPagamento(formaPagamento);

                        dao.insert(despesa);
                        break;

                    case 2:
                        System.out.println("\n-- Atualizar Despesa --");
                        System.out.print("ID da despesa que deseja alterar: ");
                        long idAlt = scan.nextLong();
                        System.out.print("Novo Valor: ");
                        double nValor = scan.nextDouble();
                        scan.nextLine();
                        System.out.print("Nova Descrição: ");
                        String nDesc = scan.nextLine();
                        System.out.print("Nova forma de pagamento: ");
                        String fPag = scan.nextLine();

                       Despesa despesaAtual = new Despesa();
                        despesaAtual.setId(idAlt);
                        despesaAtual.setValor(nValor);
                        despesaAtual.setDescricao(nDesc);
                        despesaAtual.setData(java.time.LocalDate.now());
                        despesaAtual.setFormaPagamento(fPag);

                        dao.update(despesaAtual);
                        break;

                    case 3:
                        System.out.println("\n-- Despesas Cadastradas--");

                        List<Despesa> lista = dao.getAll();
                        if (lista.isEmpty()) {
                            System.out.println("Nenhuma Despesa encontrada.");
                        } else {
                            System.out.println("\n---Total de Registros Localizado: " + lista.size() + " ---");
                            for (Despesa item : lista) {

                                System.out.println("\n----------------");
                                System.out.println("ID: " + item.getId());
                                System.out.println("Valor: R$ " + item.getValor());
                                System.out.println("Data: " + item.getData());
                                System.out.println("Descrição: " + item.getDescricao());
                                System.out.println("Tipo: " + item.getTipo());
                                System.out.println("Forma de pagamento: " + item.getFormaPagamento());
                            }
                        }
                        break;

                    case 4:
                        System.out.println("\n-- Pesquisar por ID --");
                        System.out.print("Digite o ID da despesa: ");
                        long idPesquisa = scan.nextLong();
                        scan.nextLine();

                       Despesa despesaEncontrada = dao.searchById(idPesquisa);

                        if (despesaEncontrada != null) {
                            System.out.println("\n--- Registro Localizado ---");
                            System.out.println("ID: " + despesaEncontrada.getId());
                            System.out.println("Valor: R$ " +despesaEncontrada.getValor());
                            System.out.println("Data: " + despesaEncontrada.getData());
                            System.out.println("Descrição: " +despesaEncontrada.getDescricao());
                            System.out.println("Tipo: " + despesaEncontrada.getTipo());
                            System.out.println("Forma de pagamento: " + despesaEncontrada.getFormaPagamento());
                        } else {
                            System.out.println("\n[!] Despesa com ID " + idPesquisa + " não encontrada.");
                        }
                        break;


                    case 5:
                        System.out.print("\nID da despesa para remover: ");
                        long idRem = scan.nextLong();
                        dao.remove(idRem);
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
