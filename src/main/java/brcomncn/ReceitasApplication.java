package brcomncn;

import brcomncn.dao.ReceitaDAO;
import brcomncn.model.Receita;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ReceitasApplication {
    private static Scanner scanner;
    private static ReceitaDAO receitaDAO;

    public static void main(String[] args) {
        inicializarRecursos();

        while (true) {
            try {
                exibirMenu();
                int opcao = lerOpcao();

                switch (opcao) {
                    case 1:
                        cadastrarReceita();
                        break;
                    case 2:
                        listarReceitas();
                        break;
                    case 3:
                        System.out.println("Saindo do sistema...");
                        fecharRecursos();
                        return;
                    default:
                        System.out.println("Opção inválida! Digite um número entre 1 e 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números!");
                scanner.nextLine(); // Limpa o buffer
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void inicializarRecursos() {
        scanner = new Scanner(System.in);
        receitaDAO = new ReceitaDAO();
    }

    private static void fecharRecursos() {
        if (scanner != null) {
            scanner.close();
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Receitas da Vovó ===");
        System.out.println("1. Cadastrar nova receita");
        System.out.println("2. Listar todas as receitas");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        return scanner.nextInt();
    }

    private static void cadastrarReceita() throws SQLException {
        scanner.nextLine(); // Limpar o buffer após ler a opção

        System.out.println("\n=== Cadastro de Receita ===");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Tempo de Preparo: ");
        String tempoPreparo = scanner.nextLine();

        System.out.print("Porções: ");
        int porcoes;
        try {
            porcoes = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Número de porções inválido!");
        }

        System.out.println("Ingredientes (digite 'fim' em uma nova linha para terminar):");
        StringBuilder ingredientes = new StringBuilder();
        String linha;
        while (!(linha = scanner.nextLine()).equalsIgnoreCase("fim")) {
            ingredientes.append(linha).append("\n");
        }

        System.out.println("Modo de Preparo (digite 'fim' em uma nova linha para terminar):");
        StringBuilder modoPreparo = new StringBuilder();
        while (!(linha = scanner.nextLine()).equalsIgnoreCase("fim")) {
            modoPreparo.append(linha).append("\n");
        }

        if (titulo.isEmpty() || ingredientes.toString().trim().isEmpty() ||
                modoPreparo.toString().trim().isEmpty()) {
            throw new IllegalArgumentException("Título, ingredientes e modo de preparo são obrigatórios!");
        }

        Receita receita = new Receita(
                titulo,
                categoria,
                tempoPreparo,
                porcoes,
                ingredientes.toString().trim(),
                modoPreparo.toString().trim(),
                "Manual"
        );

        receitaDAO.salvarReceita(receita);
        System.out.println("Receita cadastrada com sucesso!");
    }

    private static void listarReceitas() throws SQLException {
        List<Receita> receitas = receitaDAO.buscarTodasReceitas();

        if (receitas.isEmpty()) {
            System.out.println("\nNenhuma receita cadastrada!");
            return;
        }

        System.out.println("\n=== Receitas Cadastradas ===");
        for (Receita receita : receitas) {
            exibirReceita(receita);
        }
    }

    private static void exibirReceita(Receita receita) {
        System.out.println("\nTítulo: " + receita.getTitulo());
        System.out.println("Categoria: " + receita.getCategoria());
        System.out.println("Tempo de Preparo: " + receita.getTempoPreparo());
        System.out.println("Porções: " + receita.getPorcoes());
        System.out.println("Fonte: " + receita.getFonte());
        System.out.println("-----------------");
    }
}