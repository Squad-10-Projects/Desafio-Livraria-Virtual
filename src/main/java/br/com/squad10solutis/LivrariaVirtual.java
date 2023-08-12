package br.com.squad10solutis;

import jakarta.persistence.EntityManager;
import util.EntityManagerUtil;

import java.util.List;
import java.util.Scanner;

public class LivrariaVirtual {
    private static final int MAX_IMPRESSOS = 10;
    private static final int MAX_ELETRONICOS = 20;
    private static final int MAX_VENDAS = 50;

    private EntityManager em = EntityManagerUtil.getEntityManager();

    private Impresso[] impressos = new Impresso[MAX_IMPRESSOS];
    private Eletronico[] eletronicos = new Eletronico[MAX_ELETRONICOS];
    private Venda[] vendas = new Venda[MAX_VENDAS];

    private int numImpressos = 0;
    private int numEletronicos = 0;
    private int numVendas = 0;

    public void cadastrarLivro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tipo de livro a ser cadastrado:");
        System.out.println("1 - Impresso");
        System.out.println("2 - Eletrônico");
        System.out.println("3 - Ambos");
        int tipo = scanner.nextInt();

        if (tipo == 1 || tipo == 3) {
            if (numImpressos < MAX_IMPRESSOS) {
                System.out.println("Informe os dados do livro impresso:");
                System.out.print("Título: ");
                String titulo = scanner.next();
                System.out.print("Autores: ");
                String autores = scanner.next();
                System.out.print("Editora: ");
                String editora = scanner.next();
                System.out.print("Preço: ");
                float preco = scanner.nextFloat();
                System.out.print("Frete: ");
                float frete = scanner.nextFloat();
                System.out.print("Estoque: ");
                int estoque = scanner.nextInt();

                Impresso impresso = new Impresso(titulo, autores, editora, preco, frete, estoque);
                em.getTransaction().begin();
                em.persist(impresso);
                em.getTransaction().commit();
                impressos[numImpressos] = impresso;
                numImpressos++;
                System.out.println("Livro impresso cadastrado com sucesso!");
            } else {
                System.out.println("Não é possível cadastrar mais livros impressos.");
            }
        }

        if (tipo == 2 || tipo == 3) {
            if (numEletronicos < MAX_ELETRONICOS) {
                System.out.println("Informe os dados do livro eletrônico:");
                System.out.print("Título: ");
                String titulo = scanner.next();
                System.out.print("Autores: ");
                String autores = scanner.next();
                System.out.print("Editora: ");
                String editora = scanner.next();
                System.out.print("Preço: ");
                float preco = scanner.nextFloat();
                System.out.print("Tamanho: ");
                int tamanho = scanner.nextInt();

                Eletronico eletronico = new Eletronico(titulo, autores, editora, preco, tamanho);
                em.getTransaction().begin();
                em.persist(eletronico);
                em.getTransaction().commit();
                eletronicos[numEletronicos] = eletronico;
                numEletronicos++;
                System.out.println("Livro eletrônico cadastrado com sucesso!");
            } else {
                System.out.println("Não é possível cadastrar mais livros eletrônicos.");
            }
        }
    }

    public void realizarVenda() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome do cliente: ");
        String cliente = scanner.nextLine();

        System.out.println("Quantidade de livros a comprar: ");
        int quantidadeLivros = scanner.nextInt();

        Venda venda = new Venda();
        venda.setNumero(numVendas + 1);
        venda.setCliente(cliente);

        for (int i = 0; i < quantidadeLivros; i++) {
            System.out.println("Livro " + (i + 1) + ":");
            System.out.println("Tipo de livro (1 - Impresso, 2 - Eletrônico): ");
            int tipoLivro = scanner.nextInt();

            if (tipoLivro == 1) {
                listarLivrosImpressos();
            } else if (tipoLivro == 2) {
                listarLivrosEletronicos();
            }

            System.out.println("Escolha o índice do livro: ");
            int indiceLivro = scanner.nextInt();

            Livro livroEscolhido = (tipoLivro == 1) ? impressos[indiceLivro] : eletronicos[indiceLivro];

            venda.getLivros().add(livroEscolhido);
            venda.setValor(venda.getValor() + livroEscolhido.getPreco());
        }

        vendas[numVendas] = venda;
        numVendas++;
        em.getTransaction().begin();
        em.persist(venda);
        em.getTransaction().commit();
        System.out.println("Venda realizada com sucesso!");
    }

    public void listarLivrosImpressos() {
        List<Impresso> impressosList = em.createQuery("SELECT i FROM Impresso i", Impresso.class).getResultList();
        for (Impresso impresso : impressosList) {
            System.out.println(impresso);
        }
    }



    public void listarLivrosEletronicos() {
        List<Eletronico> eletronicosList = em.createQuery("SELECT e FROM Eletronico e", Eletronico.class).getResultList();
        for (Eletronico eletronico : eletronicosList) {
            System.out.println(eletronico);
        }
    }

    public void listarLivros() {
        listarLivrosImpressos();
        listarLivrosEletronicos();
    }

    public void listarVendas() {
        List<Venda> vendasList = em.createQuery("SELECT v FROM Venda v", Venda.class).getResultList();
        for (Venda venda : vendasList) {
            System.out.println(venda);
        }
    }

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Realizar Venda");
            System.out.println("3. Listar Livros");
            System.out.println("4. Listar Vendas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    realizarVenda();
                    break;
                case 3:
                    listarLivros();
                    break;
                case 4:
                    listarVendas();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    public static void main(String[] args) {
        LivrariaVirtual livraria = new LivrariaVirtual();
        livraria.exibirMenu();
    }
}