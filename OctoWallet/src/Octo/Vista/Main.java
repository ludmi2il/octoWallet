package Octo.Vista;

import Octo.Modelo.DAO.DaoMonedaImpl;
import Octo.Modelo.Entidad.Activo;
import Octo.Modelo.Entidad.Moneda;
import Octo.Controlador.ControladorAIO;
import Octo.Modelo.Entidad.Stock;

import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DaoMonedaImpl mon = new DaoMonedaImpl();
        mon.crear(new Moneda("k","MIAU","Lmao", 4000.00,1,5000));
        System.out.println(mon.listar());
        //Creacion del menu

        ControladorAIO controlador = new ControladorAIO();
        System.out.println("Bienvenido al sistema de criptomonedas");
        System.out.println( "seleccione una opcion: 1. crearMoneda " +
                                                   "2. listarMoneda " +
                                                   "3. crearStock " +
                                                   "4. listarStock " +
                                                   "5. crearActivo " +
                                                   "6. listarActivos");
        int opcion;
        opcion = in.nextInt();
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
                listarActivos(in, controlador);
                break;
            case 7:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }
    private static void crearMoneda(Scanner in, ControladorAIO controlador) {
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

    private static void listarMoneda(Scanner in, ControladorAIO controlador) {
        System.out.println("Seleccione criterio de ordenamiento: 1. Valor Dolar 2. Nomenclatura");
        int criterioMoneda = in.nextInt();
        List<Moneda> monedas = controlador.listarMoneda(criterioMoneda);
        System.out.println("Listado de Monedas:");
        for (Moneda moneda : monedas) {
            System.out.println(moneda);
        }
    }

    private static void crearStock(Scanner in, ControladorAIO controlador) {
        System.out.println("Ingrese nomenclatura de stock:");
        String nomenclaturaStock = in.nextLine();
        boolean exitoStock = controlador.crearStock(nomenclaturaStock);
        System.out.println("Stock creado: " + exitoStock);
    }

    private static void listarStock(Scanner in, ControladorAIO controlador) {
        System.out.println("Seleccione criterio de ordenamiento: 1. Nomenclatura");
        int criterioStock = in.nextInt();
        List<Stock> stocks = controlador.ListarStock(criterioStock);
        System.out.println("Listado de Stocks:");
        for (Stock stockItem : stocks) {
            System.out.println(stockItem);
        }
    }

    private static void crearActivo(Scanner in, ControladorAIO controlador) {
        System.out.println("Ingrese tipo de activo (CRYPTO/FIAT):");
        String tipoActivo = in.nextLine();
        System.out.println("Ingrese nomenclatura de activo:");
        String nomenclaturaActivo = in.nextLine();
        System.out.println("Ingrese saldo de activo:");
        double saldo = in.nextDouble();
        boolean exitoActivo = controlador.crearActivo(tipoActivo, nomenclaturaActivo, saldo);
        System.out.println("Activo creado: " + exitoActivo);
    }

    private static void listarActivos(Scanner in, ControladorAIO controlador) {
        List<Activo> activos = controlador.ListarActivos();
        System.out.println("Listado de Activos:");
        for (Activo activo : activos) {
            System.out.println(activo);
        }
    }
}
