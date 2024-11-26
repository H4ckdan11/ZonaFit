package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.conexion.Conexion.getConexion;

public class ClienteDAO implements IClienteDAO{

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes  = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM cliente ORDER BY id";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e){
            System.out.println("Error al listar clientes: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        // Definimos las variables con sus respectivas funciones.
        PreparedStatement ps;
        ResultSet rs;
        var con = getConexion();
        var sql = "SELECT * FROM cliente WHERE id = ?";
        // Probamos y arrojamos una exepcion.
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erro al recuperar el cliente por Id: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerra la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO cliente(nombre, apellido, membresia) "
                + " VALUES(?, ?, ?)";
        // Probamos y arrojamos una exepcion.
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Erro al agregar el cliente: " + e.getMessage());
        } finally {
            try {
                con.close();
            }catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "UPDATE cliente SET nombre=?, apellido=?, membresia=? " +
                " WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar el cliente: " + e.getMessage());
        } finally {
            try {
                con.close();
            }catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM cliente WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("Error al elimar el cliente: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }
        // Prueba local
    public static void main(String[] args) {
        // Listar clientes
//        System.out.println("*** Lsitar Clientes ***");
        //IClienteDAO clienteDao = new ClienteDAO();
//        var clientes = clienteDao.listarClientes();
//        clientes.forEach(System.out::println);

        // Buscar por ID
//        var cliente1 = new Cliente(2);
//        System.out.println("Cliente antes de la busqueda: " + cliente1);
//        var encontrado = clienteDao.buscarClientePorId(cliente1);
//        if (encontrado){
//            System.out.println("Cliente encontrado: " + cliente1);
//        } else {
//            System.out.println("No se encontro el cliente: " + cliente1.getId());
//        }

        // Agregar Cliente
        //var nuevoCliente  = new Cliente("Miguel", "Ortiz", 300);
        //var agregado = clienteDao.agregarCliente(nuevoCliente);
        //if (agregado) {
        //    System.out.println("Cliente agregado: " + nuevoCliente);
        //} else {
        //    System.out.println("No se agrego el cliente: " + nuevoCliente);
        //}

        // Modificar o Actualizar el cliente.
        //var modificarCliente = new Cliente(5, "Miguel Angel", "Ortiz", 300);
        //var modificado = clienteDao.modificarCliente(modificarCliente);
        //if (modificado) {
        //    System.out.println("Cliente modificado: " + modificarCliente);
        //} else {
        //    System.out.println("No se modifico el cliente: " + modificarCliente);
        //}

        // Eliminar un cliente por ID.
        //var clienteEliminar = new Cliente(5);
        //var eliminado = clienteDao.eliminarCliente(clienteEliminar);
        //if (eliminado) {
        //    System.out.println("Cliente eliminado: " + clienteEliminar);
        //} else {
        //    System.out.println("No se elimino el cliente: " + clienteEliminar);
        //}

        // Listar clientes
        //System.out.println("*** Lsitar Clientes ***");
        //var clientes = clienteDao.listarClientes();
        //clientes.forEach(System.out::println);
    }
}
