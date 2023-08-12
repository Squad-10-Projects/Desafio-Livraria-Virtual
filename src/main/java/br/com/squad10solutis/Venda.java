package br.com.squad10solutis;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static int numVendas = 0;
    private int numero;
    private String cliente;
    private float valor;

   @OneToMany
    private List<Livro> livros = new ArrayList<>();

    public Venda() {
    }

    public Venda(Long id, int numero, String cliente, float valor, List<Livro> livros) {
        this.id = id;
        this.numero = numero;
        this.cliente = cliente;
        this.valor = valor;
        this.livros = livros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static int getNumVendas() {
        return numVendas;
    }

    public static void setNumVendas(int numVendas) {
        Venda.numVendas = numVendas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public void addLivro(Livro livro, int index) {
        livros.add(index, livro);
    }

    public void listarLivros() {
        for (Livro livro : livros) {
            System.out.println(livro.toString());
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Venda [ID: %d, Número: %d, Cliente: '%s', Valor: %.2f, Livros: %s]",
                id, numero, cliente, valor, livros
        );
    }
}