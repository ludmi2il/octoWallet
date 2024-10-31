package Octo.Vista;

import Octo.Modelo.Entidad.Activo;
import Octo.Modelo.Entidad.Moneda;
import Octo.Controlador.ControladorAIO;
import Octo.Modelo.Entidad.Stock;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ControladorAIO controlador = new ControladorAIO();
        boolean exit = false;
        while (!exit) {
            System.out.println("Bienvenido al sistema de criptomonedas");
            System.out.println("Seleccione una opcion:");
            System.out.println("1. Crear Moneda");
            System.out.println("2. Listar Monedas");
            System.out.println("3. Crear Stock");
            System.out.println("4. Listar Stocks");
            System.out.println("5. Crear Activo");
            System.out.println("6. Listar Activos");
            System.out.println("7. Swap");
            System.out.println("8. Comprar CriptoMonedas");
            System.out.println("9. Salir");
            int opcion = in.nextInt();
            in.nextLine(); // consume newline

            switch (opcion) {
                case 1:
                    crearMoneda(in, controlador);
                    break;
                case 2:
                    listarMoneda(in, controlador);
                    break;
                case 3:
                    crearStock(in, controlador);
                    break;
                case 4:
                    listarStock(in, controlador);
                    break;
                case 5:
                    crearActivo(in, controlador);
                    break;
                case 6:
                    listarActivos(controlador);
                    break;
                case 7:
                    swap(in, controlador);
                    break;
                case 8:
                    comprarCriptoMonedas(in, controlador);
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    public static void crearMoneda(Scanner in, ControladorAIO controlador) {
        System.out.println("Ingrese tipo de moneda:");
        String tipo = in.nextLine();
        System.out.println("Ingrese nombre de moneda:");
        String nombre = in.nextLine();
        System.out.println("Ingrese nomenclatura de moneda:");
        String nomenclatura = in.nextLine();
        System.out.println("Ingrese cotizacion de moneda:");
        double cotizacion = in.nextDouble();
        System.out.println("Ingrese volatilidad de moneda:");
        double volatilidad = in.nextDouble();
        System.out.println("Ingrese stock de moneda:");
        double stock = in.nextDouble();
        boolean exitoMoneda = controlador.crearMoneda(tipo, nombre, nomenclatura, cotizacion, volatilidad, stock);
        System.out.println("Moneda creada: " + exitoMoneda);
    }

    public static void listarMoneda(Scanner in, ControladorAIO controlador) {
        System.out.println("Seleccione criterio de ordenamiento: 1. Valor Dolar 2. Nomenclatura");
        int criterioMoneda = in.nextInt();
        List<Moneda> monedas = controlador.listarMoneda(criterioMoneda);
        System.out.println("Listado de Monedas:");
        for (Moneda moneda : monedas) {
            System.out.println(moneda);
        }
    }

    public static void crearStock(Scanner in, ControladorAIO controlador) {
        System.out.println("Ingrese nomenclatura de stock:");
        String nomenclaturaStock = in.nextLine();
        boolean exitoStock = controlador.crearStock(nomenclaturaStock);
        System.out.println("Stock creado: " + exitoStock);
    }

    public static void listarStock(Scanner in, ControladorAIO controlador) {
        System.out.println("Seleccione criterio de ordenamiento: 1. Nomenclatura");
        int criterioStock = in.nextInt();
        List<Stock> stocks = controlador.ListarStock(criterioStock);
        System.out.println("Listado de Stocks:");
        for (Stock stockItem : stocks) {
            System.out.println(stockItem);
        }
    }

    public static void crearActivo(Scanner in, ControladorAIO controlador) {
        System.out.println("Ingrese tipo de activo (CRYPTO/FIAT):");
        String tipoActivo = in.nextLine();
        System.out.println("Ingrese nomenclatura de activo:");
        String nomenclaturaActivo = in.nextLine();
        System.out.println("Ingrese saldo de activo:");
        double saldo = in.nextDouble();
        boolean exitoActivo = controlador.crearActivo(tipoActivo, nomenclaturaActivo, saldo);
        System.out.println("Activo creado: " + exitoActivo);
    }

    public static void listarActivos(ControladorAIO controlador) {
        List<Activo> activos = controlador.ListarActivos();
        System.out.println("Listado de Activos:");
        for (Activo activo : activos) {
            System.out.println(activo);
        }
    }

    public static void swap(Scanner in, ControladorAIO controlador) {
        System.out.println("Ingrese cripto original:");
        String criptoOriginal = in.nextLine();
        System.out.println("Ingrese cantidad:");
        double cantidad = in.nextDouble();
        in.nextLine(); // consume newline
        System.out.println("Ingrese cripto esperada:");
        String criptoEsperada = in.nextLine();
        boolean exitoSwap = controlador.swap(criptoOriginal, cantidad, criptoEsperada);
        System.out.println("Swap realizado: " + exitoSwap);
    }

    public static void comprarCriptoMonedas(Scanner in, ControladorAIO controlador) {
        System.out.println("Ingrese cripto:");
        String cripto = in.nextLine();
        System.out.println("Ingrese fiat:");
        String fiat = in.nextLine();
        System.out.println("Ingrese cantidad:");
        double cantidad = in.nextDouble();
        boolean exitoCompra = controlador.comprarCripto(cripto, fiat, cantidad);
        System.out.println("Compra realizada: " + exitoCompra);
    }
}