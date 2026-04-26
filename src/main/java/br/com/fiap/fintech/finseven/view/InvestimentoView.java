package br.com.fiap.fintech.finseven.view;

import br.com.fiap.fintech.finseven.dao.InvestimentoDao;
import br.com.fiap.fintech.finseven.dao.ReceitaDao;
import br.com.fiap.fintech.finseven.model.Investimento;
import br.com.fiap.fintech.finseven.model.Receita;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class InvestimentoView {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            InvestimentoDao dao = new InvestimentoDao();
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n--- MENU INVESTIMENTOS FINSEVEN ---");
                System.out.println("1 - Cadastrar Investimento");
                System.out.println("2 - Atualizar Investimento");
                System.out.println("3 - Exibir todos os Investimentos");
                System.out.println("4 - Pesquisar Investimentos por ID");
                System.out.println("5 - Remover Investimento");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scan.nextInt();
                scan.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        System.out.println("\n-- Novo Lançamento --");
                        System.out.print("ID do Banco: 3-Bradesco/4-Nubank/6-Mercado Pago/7-Santander/8-Safra ");
                        long idBanco = scan.nextLong();
                        System.out.print("ID da Categoria do tipo Investimento: ");
                        long idCat = scan.nextLong();
                        System.out.print("Valor: ");
                        double valor = scan.nextDouble();
                        scan.nextLine();
                        System.out.print("Descrição: ");
                        String desc = scan.nextLine();
                        System.out.print("Taxa do Investimento: ");
                        double taxa = scan.nextDouble();
                        String tipo = "INVESTIMENTO";

                        Investimento investimento = new Investimento();
                        investimento.setIdBanco(idBanco);
                        investimento.setIdCategoria(idCat);
                        investimento.setValor(valor);
                        investimento.setDescricao(desc);
                        investimento.setTipo(tipo);
                        investimento.setData(java.time.LocalDate.now());
                        investimento.setTaxa(taxa);

                        dao.insert(investimento);
                        break;

                    case 2:
                        System.out.println("\n-- Atualizar Investimento --");
                        System.out.print("ID do Investimento que deseja alterar: ");
                        long idAlt = scan.nextLong();
                        System.out.print("Novo Valor: ");
                        double nValor = scan.nextDouble();
                        scan.nextLine(); // Buffer
                        System.out.print("Nova Descrição: ");
                        String nDesc = scan.nextLine();
                        System.out.print("Nova Taxa: ");
                        double nTaxa = scan.nextDouble();

                        Investimento investimentoAtual = new Investimento();
                        investimentoAtual.setId(idAlt);
                        investimentoAtual.setValor(nValor);
                        investimentoAtual.setDescricao(nDesc);
                        investimentoAtual.setData(java.time.LocalDate.now());
                        investimentoAtual.setTaxa(nTaxa);

                        dao.update(investimentoAtual);
                        break;

                    case 3:
                        System.out.println("\n-- Investimentos Cadastrados--");
                        // Aqui assume-se que você tem o método listar() no seu DAO
                        List<Investimento> lista = dao.getAll();
                        if (lista.isEmpty()) {
                            System.out.println("Nenhum Investimento encontrado.");
                        } else {
                            System.out.println("\n---Total de Registros Localizados: " + lista.size() + " ---");
                            for (Investimento item : lista) {
                                //System.out.println("ID: " + item.getId() + " | " + item.getDescricao() + " | R$ " + item.getValor());
                                System.out.println("\n----------------");
                                System.out.println("ID: " + item.getId());
                                System.out.println("Valor: R$ " + item.getValor());
                                System.out.println("Data: " + item.getData());
                                System.out.println("Descrição: " + item.getDescricao());
                                System.out.println("Tipo: " + item.getTipo());
                                System.out.println("Taxa: " + item.getTaxa());
                            }
                        }
                        break;

                    case 4:
                        System.out.println("\n-- Pesquisar por ID --");
                        System.out.print("Digite o ID do Investimento: ");
                        long idPesquisa = scan.nextLong();
                        scan.nextLine(); // Buffer

                        // Utiliza o método pesquisar que você criou no DAO
                        Investimento investimentoEncontrado = dao.searchById(idPesquisa);

                        if (investimentoEncontrado != null) {
                            System.out.println("\n--- Registro Localizado ---");
                            System.out.println("ID: " + investimentoEncontrado.getId());
                            System.out.println("Valor: R$ " + investimentoEncontrado.getValor());
                            System.out.println("Data: " + investimentoEncontrado.getData());
                            System.out.println("Descrição: " + investimentoEncontrado.getDescricao());
                            System.out.println("Tipo: " + investimentoEncontrado.getTipo());
                            System.out.println("Taxa: " + investimentoEncontrado.getTaxa());
                        } else {
                            System.out.println("\n[!] Investimento com ID " + idPesquisa + " não encontrado.");
                        }
                        break;


                    case 5:
                        System.out.print("\nID do Investimento para remover: ");
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
