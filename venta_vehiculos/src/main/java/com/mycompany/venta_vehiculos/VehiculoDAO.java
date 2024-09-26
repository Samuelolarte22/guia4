/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.venta_vehiculos;

/**
 *
 * @author samue
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {
    
    private Connection conexion;
    // Constructor que acepta una conexión
    public VehiculoDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    //agregar vehiculo
    public void agregarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (placa, marca, modelo, año, num_ejes, cilindrada, valor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, vehiculo.getPlaca());
            pst.setString(2, vehiculo.getMarca());
            pst.setString(3, vehiculo.getModelo());
            pst.setInt(4, vehiculo.getAño());
            pst.setInt(5, vehiculo.getEjes());
            pst.setDouble(6, vehiculo.getCilindrada());
            pst.setDouble(7, vehiculo.getValor());
            pst.executeUpdate();
            System.out.println("Vehiculo agregado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar el vehículo: " + e.getMessage());
        }
    }

    //obtener placas
    public List<String> obtenerPlacas() {
        List<String> placas = new ArrayList<>();
        String sql = "SELECT placa FROM vehiculos";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                placas.add(rs.getString("placa"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las placas: " + e.getMessage());
        }
        return placas;
    }

    //obtener vehiculo por placa
    public Vehiculo obtenerVehiculoPorPlaca(String placa) {
    Vehiculo vehiculo = null;
    String sql = "SELECT * FROM vehiculos WHERE placa = ?";

    try (Connection con = ConexionBD.conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, placa);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            vehiculo = new Vehiculo();
            vehiculo.setPlaca(rs.getString("placa"));
            vehiculo.setMarca(rs.getString("marca"));
            vehiculo.setModelo(rs.getString("modelo"));
            vehiculo.setAño(rs.getInt("año"));
            vehiculo.setEjes(rs.getInt("num_ejes"));
            vehiculo.setCilindrada(rs.getDouble("cilindrada"));
            vehiculo.setValor(rs.getDouble("valor"));
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener el vehículo: " + e.getMessage());
    }

    return vehiculo;
}
    //obtener vehiculos ordenados
    public List<Vehiculo> obtenerVehiculosOrdenados(String orden) {
    List<Vehiculo> vehiculos = new ArrayList<>();
    String sql = "SELECT * FROM vehiculos ORDER BY " + orden;

    try (Connection con = ConexionBD.conectar();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        while (rs.next()) {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca(rs.getString("placa"));
            vehiculo.setMarca(rs.getString("marca"));
            vehiculo.setModelo(rs.getString("modelo"));
            vehiculo.setAño(rs.getInt("año"));
            vehiculo.setEjes(rs.getInt("num_ejes"));
            vehiculo.setCilindrada(rs.getDouble("cilindrada"));
            vehiculo.setValor(rs.getDouble("valor"));
            vehiculos.add(vehiculo);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener vehículos ordenados: " + e.getMessage());
    }

    return vehiculos;
}
    // usando modelo y año, obtener placa
    public List<String> buscarPlacasPorModeloYAño(String modelo, int año) {
    List<String> placas = new ArrayList<>();
    String sql = "SELECT placa FROM vehiculos WHERE modelo = ? AND año = ?";

    try (Connection con = ConexionBD.conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, modelo);
        pst.setInt(2, año);
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            placas.add(rs.getString("placa"));
        }
    } catch (SQLException e) {
        System.out.println("Error al buscar placas: " + e.getMessage());
    }

    return placas;
}

    //Comprar vehiculo
   public void comprarVehiculo(String placa) {
    String sql = "DELETE FROM vehiculos WHERE placa = ?";

    try (Connection con = ConexionBD.conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, placa);
        int filasAfectadas = pst.executeUpdate();
        
        if (filasAfectadas > 0) {
            System.out.println("Vehículo comprado exitosamente.");
        } else {
            System.out.println("No se encontró el vehículo.");
        }
    } catch (SQLException e) {
        System.out.println("Error al comprar el vehículo: " + e.getMessage());
    }
}
 
   //descuento ESTE ES EL UNICO QUE AUBN FALTA POR ARREGLAR
   public void disminuirPrecio(double cantidad) {
    String sql = "UPDATE vehiculos SET valor = valor * 0.9 WHERE valor >= ?";

    try (Connection con = ConexionBD.conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setDouble(1, cantidad);
        int filasAfectadas = pst.executeUpdate();
        
        System.out.println("Precio actualizado para " + filasAfectadas + " vehículos.");
    } catch (SQLException e) {
        System.out.println("Error al disminuir el precio: " + e.getMessage());
    }
}

   //vehiculo mas antiguo
   public Vehiculo obtenerVehiculoMasAntiguo() {
    Vehiculo vehiculo = null;
    String sql = "SELECT * FROM vehiculos ORDER BY año ASC LIMIT 1";

    try (Connection con = ConexionBD.conectar();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
            vehiculo = new Vehiculo();
            vehiculo.setPlaca(rs.getString("placa"));
            vehiculo.setMarca(rs.getString("marca"));
            vehiculo.setModelo(rs.getString("modelo"));
            vehiculo.setAño(rs.getInt("año"));
            vehiculo.setEjes(rs.getInt("num_ejes"));
            vehiculo.setCilindrada(rs.getDouble("cilindrada"));
            vehiculo.setValor(rs.getDouble("valor"));
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener el vehículo más antiguo: " + e.getMessage());
    }

    return vehiculo;
}

   //mayor CC potencia
    public Vehiculo obtenerVehiculoMasPotente() {
    Vehiculo vehiculo = null;
    String sql = "SELECT * FROM vehiculos ORDER BY cilindrada DESC LIMIT 1";

    try (Connection con = ConexionBD.conectar();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
            vehiculo = new Vehiculo();
            vehiculo.setPlaca(rs.getString("placa"));
            vehiculo.setMarca(rs.getString("marca"));
            vehiculo.setModelo(rs.getString("modelo"));
            vehiculo.setAño(rs.getInt("año"));
            vehiculo.setEjes(rs.getInt("num_ejes"));
            vehiculo.setCilindrada(rs.getDouble("cilindrada"));
            vehiculo.setValor(rs.getDouble("valor"));
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener el vehiculo mas potente: " + e.getMessage());
    }

    return vehiculo;
}
    
    //menor precio
    public Vehiculo obtenerVehiculoMasBarato() {
    Vehiculo vehiculo = null;
    String sql = "SELECT * FROM vehiculos ORDER BY valor ASC LIMIT 1";

    try (Connection con = ConexionBD.conectar();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
            vehiculo = new Vehiculo();
            vehiculo.setPlaca(rs.getString("placa"));
            vehiculo.setMarca(rs.getString("marca"));
            vehiculo.setModelo(rs.getString("modelo"));
            vehiculo.setAño(rs.getInt("año"));
            vehiculo.setEjes(rs.getInt("num_ejes"));
            vehiculo.setCilindrada(rs.getDouble("cilindrada"));
            vehiculo.setValor(rs.getDouble("valor"));
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener el vehiculo mas barato: " + e.getMessage());
    }

    return vehiculo;
}


    
}

