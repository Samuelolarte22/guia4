
package com.mycompany.venta_vehiculos;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.sql.Connection;

public class VentaVehiculosGUI extends JFrame {

    private VehiculoDAO vehiculoDAO;
    private JTextArea outputArea;

    public VentaVehiculosGUI() {
        // Conectar a la base de datos
        Connection con = ConexionBD.conectar();
        vehiculoDAO = new VehiculoDAO(con);

        // Configurar la ventana principal
        setTitle("Venta de Vehículos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear un área de texto para mostrar resultados
        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // Crear panel para el menú
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(11, 1));

        // Crear botones
        JButton btnPlacas = new JButton("Placas de todos los vehículos");
        JButton btnInfoPlaca = new JButton("Información por placa");
        JButton btnAgregarVehiculo = new JButton("Agregar nuevo vehículo");
        JButton btnOrdenarVehiculos = new JButton("Ordenar vehículos");
        JButton btnBuscarPlaca = new JButton("Buscar placas (modelo/año)");
        JButton btnComprarVehiculo = new JButton("Comprar vehículo");
        JButton btnAplicarDescuento = new JButton("Aplicar descuento");
        JButton btnVehiculoAntiguo = new JButton("Vehículo más antiguo");
        JButton btnVehiculoPotente = new JButton("Vehículo más potente");
        JButton btnVehiculoBarato = new JButton("Vehículo más barato");
        JButton btnSalir = new JButton("Salir");

        // Agregar botones al panel
        panelMenu.add(btnPlacas);
        panelMenu.add(btnInfoPlaca);
        panelMenu.add(btnAgregarVehiculo);
        panelMenu.add(btnOrdenarVehiculos);
        panelMenu.add(btnBuscarPlaca);
        panelMenu.add(btnComprarVehiculo);
        panelMenu.add(btnAplicarDescuento);
        panelMenu.add(btnVehiculoAntiguo);
        panelMenu.add(btnVehiculoPotente);
        panelMenu.add(btnVehiculoBarato);
        panelMenu.add(btnSalir);

        // Agregar panel de menú al contenedor principal
        add(panelMenu, BorderLayout.WEST);

        // Añadir acciones a los botones
        btnPlacas.addActionListener(e -> mostrarPlacas());
        btnInfoPlaca.addActionListener(e -> mostrarInformacionPorPlaca());
        btnAgregarVehiculo.addActionListener(e -> agregarNuevoVehiculo());
        btnOrdenarVehiculos.addActionListener(e -> ordenarVehiculos());
        btnBuscarPlaca.addActionListener(e -> buscarPlacas());
        btnComprarVehiculo.addActionListener(e -> comprarVehiculo());
        btnAplicarDescuento.addActionListener(e -> aplicarDescuento());
        btnVehiculoAntiguo.addActionListener(e -> mostrarVehiculoMasAntiguo());
        btnVehiculoPotente.addActionListener(e -> mostrarVehiculoMasPotente());
        btnVehiculoBarato.addActionListener(e -> mostrarVehiculoMasBarato());
        btnSalir.addActionListener(e -> salir());
    }

    private void mostrarPlacas() {
        List<String> placas = vehiculoDAO.obtenerPlacas();
        outputArea.setText("Placas disponibles:\n");
        for (String placa : placas) {
            outputArea.append(placa + "\n");
        }
    }

    private void mostrarInformacionPorPlaca() {
        String placa = JOptionPane.showInputDialog(this, "Ingrese la placa del vehículo:");
        Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoPorPlaca(placa);
        if (vehiculo != null) {
            outputArea.setText("Información del vehículo:\n" + vehiculo);
        } else {
            outputArea.setText("No se encontró el vehículo con la placa: " + placa);
        }
    }

    private void agregarNuevoVehiculo() {
        String placa = JOptionPane.showInputDialog(this, "Ingrese la placa:");
        String marca = JOptionPane.showInputDialog(this, "Ingrese la marca:");
        String modelo = JOptionPane.showInputDialog(this, "Ingrese el modelo:");
        int año = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el año:"));
        int numEjes = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el número de ejes:"));
        double cilindrada = Double.parseDouble(JOptionPane.showInputDialog(this, "Ingrese la cilindrada:"));
        double valor = Double.parseDouble(JOptionPane.showInputDialog(this, "Ingrese el valor:"));

        Vehiculo nuevoVehiculo = new Vehiculo(placa, marca, modelo, año, numEjes, cilindrada, valor);
        vehiculoDAO.agregarVehiculo(nuevoVehiculo);
        outputArea.setText("Vehículo agregado con éxito.");
    }

    private void ordenarVehiculos() {
        String criterio = JOptionPane.showInputDialog(this, "Ingrese el criterio de ordenación (modelo/marca/año):");
        List<Vehiculo> vehiculosOrdenados = vehiculoDAO.obtenerVehiculosOrdenados(criterio);
        outputArea.setText("Vehículos ordenados:\n");
        for (Vehiculo vehiculo : vehiculosOrdenados) {
            outputArea.append(vehiculo + "\n");
        }
    }

    private void buscarPlacas() {
        String modelo = JOptionPane.showInputDialog(this, "Ingrese el modelo:");
        int año = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el año:"));
        List<String> placas = vehiculoDAO.buscarPlacasPorModeloYAño(modelo, año);
        outputArea.setText("Placas encontradas:\n");
        for (String placa : placas) {
            outputArea.append(placa + "\n");
        }
    }

    private void comprarVehiculo() {
        String placa = JOptionPane.showInputDialog(this, "Ingrese la placa del vehículo a comprar:");
        vehiculoDAO.comprarVehiculo(placa);
        outputArea.setText("Vehículo comprado con éxito.");
    }

    private void aplicarDescuento() {
        double cantidad = Double.parseDouble(JOptionPane.showInputDialog(this, "Ingrese el valor mínimo para aplicar descuento:"));
        vehiculoDAO.disminuirPrecio(cantidad);
        outputArea.setText("Precios actualizados.");
    }

    private void mostrarVehiculoMasAntiguo() {
        Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoMasAntiguo();
        outputArea.setText("Vehículo más antiguo:\n" + vehiculo);
    }

    private void mostrarVehiculoMasPotente() {
        Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoMasPotente();
        outputArea.setText("Vehículo más potente:\n" + vehiculo);
    }

    private void mostrarVehiculoMasBarato() {
        Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoMasBarato();
        outputArea.setText("Vehículo más barato:\n" + vehiculo);
    }

    private void salir() {
        JOptionPane.showMessageDialog(this, "Saliendo del programa...");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentaVehiculosGUI ventana = new VentaVehiculosGUI();
            ventana.setVisible(true);});}
}
