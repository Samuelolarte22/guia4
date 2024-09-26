/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.venta_vehiculos;

/**
 *
 * @author samue
 */
import java.text.DecimalFormat;


public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int año;
    private int ejes;
    private double cilindrada;
    private double valor;
    
    // Constructor sin argumentos
    public Vehiculo() {
    }

    public Vehiculo(String placa, String marca, String modelo, int año, int ejes, double cilindrada, double valor) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.ejes = ejes;
        this.cilindrada = cilindrada;
        this.valor = valor;
    }

    // Getters y Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getEjes() {
        return ejes;
    }

    public void setEjes(int ejes) {
        this.ejes = ejes;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00"); // Formato para el valor
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", año=" + año +
                ", ejes=" + ejes +
                ", cilindrada=" + cilindrada +
                ", valor=" + df.format(valor) + 
                '}';
    }
}



