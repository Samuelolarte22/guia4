/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.venta_vehiculos;

/**
 *
 * @author samue
 */

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Venta_vehiculos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection con = ConexionBD.conectar();
        VehiculoDAO vehiculoDAO = new VehiculoDAO(con);

        while (true) {
            System.out.println("Menú de opciones:");
            System.out.println("1. Placas de todos los vehiculos");
            System.out.println("2. Informacion por placa");
            System.out.println("3. Agregar un nuevo vehiculo");
            System.out.println("4. Ordenar por modelo, marca o año");
            System.out.println("5. Encuentra placa usando modelo y año");
            System.out.println("6. Comprar un vehiculo");
            System.out.println("7. Disminuir en un 10% el precio de los vehiculos mayores a $200.000.000");
            System.out.println("8. Localizar el vehiculo mas antiguo");
            System.out.println("9. Localizar el vehiculo mas potente");
            System.out.println("10. Localizar el vehiculo mas barato");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    List<String> placas = vehiculoDAO.obtenerPlacas();
                    System.out.println("Placas disponibles:");
                    for (String placa : placas) {
                        System.out.println(placa);
                    }
                    break;
                case 2:
                    System.out.print("Ingrese la placa del vehiculo: ");
                    String placaInfo = scanner.nextLine();
                    Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoPorPlaca(placaInfo);
                    if (vehiculo != null) {
                        System.out.println("Informacion del vehiculo:");
                        System.out.println(vehiculo);
                    } else {
                        System.out.println("No se encontro el vehiculo con la placa: " + placaInfo);
                    }
                    break;
                case 3:
                    System.out.print("Ingrese la placa: ");
                    String placaNuevo = scanner.nextLine();
                    System.out.print("Ingrese la marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Ingrese el modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Ingrese el año: ");
                    int año = scanner.nextInt();
                    System.out.print("Ingrese el numero de ejes: ");
                    int numEjes = scanner.nextInt();
                    System.out.print("Ingrese la cilindrada: ");
                    double cilindrada = scanner.nextDouble();
                    System.out.print("Ingrese el valor: ");
                    double valor = scanner.nextDouble();

                    Vehiculo nuevoVehiculo = new Vehiculo(placaNuevo, marca, modelo, año, numEjes, cilindrada, valor);
                    vehiculoDAO.agregarVehiculo(nuevoVehiculo);
                    System.out.println("Vehiculo agregado con exito.");
                    break;
                case 4:
                    System.out.print("Ingrese el criterio de ordenacion (modelo/marca/año): ");
                    String criterio = scanner.nextLine();
                    List<Vehiculo> vehiculosOrdenados = vehiculoDAO.obtenerVehiculosOrdenados(criterio);
                    System.out.println("Vehiculos ordenados:");
                    for (Vehiculo v : vehiculosOrdenados) {
                        System.out.println(v);
                    }
                    break;
                case 5:
                    System.out.print("Ingrese el modelo: ");
                    String modeloBuscar = scanner.nextLine();
                    System.out.print("Ingrese el año: ");
                    int añoBuscar = scanner.nextInt();
                    List<String> placasBuscadas = vehiculoDAO.buscarPlacasPorModeloYAño(modeloBuscar, añoBuscar);
                    System.out.println("Placas encontradas:");
                    for (String p : placasBuscadas) {
                        System.out.println(p);
                    }
                    break;
                case 6:
                    System.out.print("Ingrese la placa del vehiculo a comprar: ");
                    String placaCompra = scanner.nextLine();
                    vehiculoDAO.comprarVehiculo(placaCompra);
                    System.out.println("Vehiculo comprado con exito.");
                    break;
                case 7:
                    System.out.print("Ingrese el valor minimo para aplicar descuento: ");
                    double cantidad = scanner.nextDouble();
                    vehiculoDAO.disminuirPrecio(cantidad);
                    System.out.println("Precios actualizados.");
                    break;
                case 8:
                    Vehiculo vehiculoAntiguo = vehiculoDAO.obtenerVehiculoMasAntiguo();
                    System.out.println("Vehiculo mas antiguo: " + vehiculoAntiguo);
                    break;
                case 9:
                    Vehiculo vehiculoPotente = vehiculoDAO.obtenerVehiculoMasPotente();
                    System.out.println("Vehiculo mas potente: " + vehiculoPotente);
                    break;
                case 10:
                    Vehiculo vehiculoBarato = vehiculoDAO.obtenerVehiculoMasBarato();
                    System.out.println("Vehiculo mas barato: " + vehiculoBarato);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida, intente nuevamente.");
            }
        }
    }
}





