package zona_fit.precentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }
    private static void zonaFitApp(){
        var salir = false;
        var consola = new Scanner(System.in);
        // Creamos un objeto de la clase clienteDao
        IClienteDAO clienteDao = new ClienteDAO();
        while (!salir){
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);
            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    // Este es un metodo.
    private static int mostrarMenu(Scanner cosonla) {
        System.out.print("""
                *** Zona Fit (GYM) ***
                1. Listar Clientes
                2. Buscar Clientes
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elije un opcion:\s""");
        return Integer.parseInt(cosonla.nextLine());
    }

    // Creamos la sitaxis de otro metodo para ejecutarOpciones.
    private static boolean ejecutarOpciones(Scanner consola, int opcion,
                                            IClienteDAO clienteDAO) {
        var salir = false;
        switch (opcion) {
            case 1 -> { // 1. Listar Clientes
                System.out.println("--- Listado de Clientes ---");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> { // 1. Buscar el cliente por ID
                System.out.print("Introduce el ID del Cliente a buscar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarClientePorId(cliente);
                if (encontrado) {
                    System.out.println("Cliente encontrado: " + cliente);
                } else {
                    System.out.println("Cliente NO encontrado: " + cliente);
                }
            }
            case 3 -> { // Agregar el Cliente.
                System.out.println("--- Agregar Cliente ---");

                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                // Creamos el objeto cliente (sin el ID)
                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado  = clienteDAO.agregarCliente(cliente);
                if (agregado) {
                    System.out.println("Cliente agregado: " + cliente);
                } else {
                    System.out.println("Cliente NO agregado: " + cliente);
                }
            }
            case 4 -> { //4. Modificar cliente
                System.out.println("--- Modificar Cliente ---");

                System.out.print("Id Cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                // Creamos el objeto a modificar.
                var cliente = new Cliente(idCliente, nombre, apellido, membresia);
                var modificado = clienteDAO.modificarCliente(cliente);
                if (modificado) {
                    System.out.println("Cliente Modificado: " + cliente);
                }else {
                    System.out.println("Cliente NO modificado: " + cliente);
                }
            }
            case 5 -> { // 5. Eliminar Cliente
                System.out.println("--- Eliminar el Cliente ---");
                System.out.print("Id Cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                // Creamos el objeto
                var cliente = new Cliente(idCliente);
                var elimando = clienteDAO.eliminarCliente(cliente);
                if (elimando) {
                    System.out.println("Cliente eliminado: " + cliente);
                } else {
                    System.out.println("Cliente NO eliminado: " + cliente);
                }
            }
            case 6 -> { // 6. Salir
                System.out.println("Hasta prontro!");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida: " + opcion);
        }
        return salir;
    }
}
