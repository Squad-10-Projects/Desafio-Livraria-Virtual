package br.com.squad10solutis.model;

import jakarta.persistence.*;

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static int numVendas = 0;
    private int numero;
    private String cliente;
    private float valor;

    public Venda() {
    }

    public Venda(Long id, int numero, String cliente, float valor) {
        this.id = id;
        this.numero = numero;
        this.cliente = cliente;
        this.valor = valor;
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


    @Override
    public String toString() {
        return String.format(
                "Venda [ID: %d, Número: %d, Cliente: '%s', Valor: %.2f]",
                id, numero, cliente, valor
        );
    }
}