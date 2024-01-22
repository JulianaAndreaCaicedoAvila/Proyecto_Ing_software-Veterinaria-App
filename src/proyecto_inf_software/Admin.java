/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_inf_software;

import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static proyecto_inf_software.Proyecto_ing_sotfware.connection;

public class Admin {

    public static void Eliminar_usuario(int idUsuario) throws SQLException {
        List<Integer> idPerros = obtenerIdPerrosPorUsuario(idUsuario);
        eliminarProcesosPorPerros(idPerros);
        eliminarPerrosPorUsuario(idUsuario);
        eliminarUsuario(idUsuario);
    }

    private static List<Integer> obtenerIdPerrosPorUsuario(int idUsuario) throws SQLException {
        List<Integer> idPerros = new ArrayList<>();

        String query = "SELECT id_perro FROM perro WHERE propietario_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    idPerros.add(resultSet.getInt("id_perro"));
                }
            }
        }

        return idPerros;
    }

    private static void eliminarProcesosPorPerros(List<Integer> idPerros) throws SQLException {
        String eliminarProceso = "DELETE FROM progreso_mascota WHERE id_perro = ?";
        try (PreparedStatement statementProceso = connection.prepareStatement(eliminarProceso)) {
            for (int idPerro : idPerros) {
                statementProceso.setInt(1, idPerro);
                statementProceso.executeUpdate();
            }
        }
    }

    private static void eliminarPerrosPorUsuario(int idUsuario) throws SQLException {
        String eliminarPerro = "DELETE FROM perro WHERE propietario_id = ?";
        try (PreparedStatement statementPerro = connection.prepareStatement(eliminarPerro)) {
            statementPerro.setInt(1, idUsuario);
            statementPerro.executeUpdate();
        }
    }

    private static void eliminarUsuario(int idUsuario) throws SQLException {
        String eliminarUsuario = "DELETE FROM usuario WHERE id_usuario = ?";
        try (PreparedStatement statementUsuario = connection.prepareStatement(eliminarUsuario)) {
            statementUsuario.setInt(1, idUsuario);
            int filasEliminada = statementUsuario.executeUpdate();

            if (filasEliminada > 0) {
                MetodosInterface.imprimir("El usuario se ha eliminado correctamente.");
                // Registrar acción en Historial_Clientes
                String historial = "INSERT INTO Historial_Clientes (id_cliente, accion) VALUES (?, ?)";
                try (PreparedStatement Historial = connection.prepareStatement(historial)) {
                    Historial.setInt(1, idUsuario);
                    Historial.setString(2, "Eliminación");
                    Historial.executeUpdate();
                }
            } else {
                MetodosInterface.imprimir("No se encontró el usuario con el ID especificado.");
            }
        }
    }
    
    public static void agregar_plan_de_entrenamiento(int id, String nombre, String descripcion, String tipo, String rutaImagen) throws SQLException, IOException {
        String query = "INSERT INTO plandeentrenamiento (id,nombre, descripcion, tipo, imagen_entrenamiento) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, nombre);
            statement.setString(3, descripcion);
            statement.setString(4, tipo);
            statement.setString(5, rutaImagen);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                MetodosInterface.imprimir("Plan de entrenamiento agregado con éxito.");
            } else {
                MetodosInterface.imprimir("No se pudo agregar el plan de entrenamiento.");
            }
        }
    }

    public static void actualizar_plan_entrenamiento(int idPlan, String nuevoNombre, String nuevaDescripcion, String nuevoTipo, String nuevaImagen) throws SQLException {
        String query = "UPDATE plandeentrenamiento SET nombre = ?, descripcion = ?, tipo = ?, imagen_entrenamiento = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nuevoNombre);
            statement.setString(2, nuevaDescripcion);
            statement.setString(3, nuevoTipo);
            statement.setString(4, nuevaImagen);
            statement.setInt(5, idPlan);
            
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                MetodosInterface.imprimir("Plan de entrenamiento actualizado con éxito.");
            } else {
                MetodosInterface.imprimir("No se pudo actualizar el plan de entrenamiento.");
            }
        }
    }

    public static void eliminar_plan_de_entrenamiento(int idPlan) throws SQLException {
        String query = "DELETE FROM plandeentrenamiento WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPlan);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                MetodosInterface.imprimir("Plan de entrenamiento eliminado con éxito.");
            } else {
                MetodosInterface.imprimir("No se encontró el plan de entrenamiento con el ID proporcionado.");
            }
        }
    }

    public static void consultar_historial_perro(int idPerro) throws SQLException {
        String query = "SELECT id_perro, accion, fecha_registro FROM historial_mascotas WHERE id_perro = ? ORDER BY fecha_registro DESC";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPerro);

            try (ResultSet resultSet = statement.executeQuery()) {
                MetodosInterface.imprimir("Historial del mascota:\n"
                        + "id_perro\tAccion\t\tFecha Registro");

                while (resultSet.next()) {
                    int idPerroConsulta = resultSet.getInt("id_perro");
                    String accion = resultSet.getString("accion");
                    String fechaRegistro = resultSet.getString("fecha_registro");

                    MetodosInterface.imprimir(idPerroConsulta + "\t\t\n" + accion + "\t\t\n" + fechaRegistro);
                }
            }
        }
    }

    public static void consultar_historial_usuario(int id_usuario) throws SQLException {
        String query = "SELECT id_Cliente, accion, fecha_registro FROM historial_clientes WHERE id_Cliente = ? ORDER BY fecha_registro DESC";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_usuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                MetodosInterface.imprimir("Historial del cliente:\n"
                        + "id_perro\tAccion\t\tFecha Registro");
                while (resultSet.next()) {
                    String idClienteConsulta = resultSet.getString("id_Cliente2");
                    String accion = resultSet.getString("accion");
                    String fechaRegistro = resultSet.getString("fecha_registro");

                    MetodosInterface.imprimir(idClienteConsulta + "\t\t\n" + accion + "\t\t\n" + fechaRegistro);
                }
            }
        }
    }

    public static void consultar_proceso_mascota(int id_perro) throws SQLException {
        String query = "SELECT * FROM progreso_mascota WHERE id_perro = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_perro);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int idProgreso = resultSet.getInt("id_progreso");
            String descripcion = resultSet.getString("descripcion");
            boolean logroAlcanzado = resultSet.getBoolean("logro_alcanzado");

            MetodosInterface.imprimir("ID de Progreso: " + idProgreso);
            MetodosInterface.imprimir("Descripción: " + descripcion);
            MetodosInterface.imprimir("Logro Alcanzado: " + logroAlcanzado);

        }
    }

    public static void generar_retroalimentacion(int idPerro) throws SQLException {
        String query = "SELECT COUNT(*) FROM progreso_mascota WHERE id_perro = ? AND logro_alcanzado = true";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPerro);

            try (ResultSet resultSet = statement.executeQuery()) {
                MetodosInterface.imprimir("Evaluación de rendimiento para el perro con ID " + idPerro + ":");

                if (resultSet.next()) {
                    int cantidadLogros = resultSet.getInt(1);

                    String rendimiento = evaluarRendimiento(cantidadLogros);

                    MetodosInterface.imprimir("Rendimiento: " + rendimiento);
                } else {
                    MetodosInterface.imprimir("No se encontraron logros para el perro con ID " + idPerro);
                }
            }
        }
    }

    private static String evaluarRendimiento(int cantidadLogros) {
        if (cantidadLogros >= 50) {
            Notificaciones.Notificacion_progreso();
            return "Excelente";
        } else if (cantidadLogros >= 40) {
            Notificaciones.Notificacion_progreso();
            return "Alto";
        } else if (cantidadLogros >= 30) {
            return "Regular";
        } else if (cantidadLogros >= 20) {
            return "Bajo";
        } else {
            return "Critico";
        }
    }
  
///////////MAIN//////
    public static boolean Logeo_Admin(int id_usuario, String contrasena, String nombre_usuario) {
        String query = "SELECT COUNT(*) AS count FROM admin WHERE usuario = ? AND contrasena = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombre_usuario);
            statement.setString(2, contrasena);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    if (count > 0) {
                        return true;
                    } else {
                        MetodosInterface.imprimir("Error al iniciar sesión, vuelve a intentarlo.");
                        Inicio_de_sesion_admin();
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            MetodosInterface.imprimir("Error al entrar");
            MetodosInterface.close();
        }

        return false;
    }

    public static void Inicio_de_sesion_admin() throws SQLException {
        int id_usuario = MetodosInterface.recibirEntero("Digita  id_usuario:");
        String nombre_usuario = MetodosInterface.recibirString("Digita Usuario:");
        String contrasena = MetodosInterface.recibirString("Digita la Contraseña:");

        if (Logeo_Admin(id_usuario, contrasena, nombre_usuario) == true) {
            MetodosInterface.imprimir("Inicio exitoso");
        } else if (Logeo_Admin(id_usuario, contrasena, nombre_usuario) == false) {
            MetodosInterface.imprimir("Error, el usuario no es correcto");
        }

    }

    public static void menu_logeo_admin() throws SQLException {
        int seleccion = MetodosInterface.recibirEntero("¿Tienes cuenta en la aplicación?\n"
                + "Digita 1 para iniciar sesión.\n"
                + "Digita 2 para registrarte");
        switch (seleccion) {
            case 1 ->
                Inicio_de_sesion_admin();
            case 2 -> {
                int id_admin = MetodosInterface.recibirEntero("Digita el id_admin");
                String usuario = MetodosInterface.recibirString("Digita el usuario:");
                String contrasena = MetodosInterface.recibirString("Digita el contrasena:");
                Registrar_admin(id_admin, usuario, contrasena);
            }
            default ->
menu_logeo_admin() ;        }
    }

    public static void Registrar_admin(int id_admin, String usuario, String contrasena) throws SQLException {
        String query = "INSERT INTO admin (id_admin,  usuario, contrasena) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_admin);
        statement.setString(2, usuario);
        statement.setString(3, contrasena);
        int filasInsertadas = statement.executeUpdate();

        if (filasInsertadas > 0) {
            MetodosInterface.imprimir("El cliente se ha registrado exitosamente.");

        } else {
            MetodosInterface.imprimir("No se pudo insertar el cliente.");
        }
        statement.close();
    }
    public static void asignar_plan_de_entrenamiento(int idPerro, int idPlan) throws SQLException {
        String query = "INSERT INTO progreso_mascota (id_perro, id_plan) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPerro);
            statement.setInt(2, idPlan);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                MetodosInterface.imprimir("Plan de entrenamiento asignado al perro con ID " + idPerro);
            } else {
                MetodosInterface.imprimir("No se pudo asignar el plan de entrenamiento al perro con ID " + idPerro);
            }
        }
    }
//*/*//Menú de las funcionalidades de los administradores.

    public static void menuFuncionesAdmin() throws SQLException, IOException {
        MetodosInterface.imprimir("Ingresaste al menú de los admin, acá puedes hacer varias acciones:");
        int opcion = MetodosInterface.recibirEntero(
                "1.Eliminar usuarios.\n"
                + "2.Agregar plan de entrenamiento.\n"
                + "3.Actulizar plan de entrenamiento.\n"
                + "4.Eliminar plan de entrenamiento.\n"
                + "5.Consultar historial de un perro.\n"
                + "6.Colsultar historial de un usuario.\n"
                + "7.Consultar progreso de un perro.\n"
                + "8.Generar retroalimentación de un perro.\n"
                + "9. Agregar plan de entrenamiento a una mascota.");
        switch (opcion) {
            case 1 -> {
                int id = MetodosInterface.recibirEntero("Digita el id del cliente que quieres eliminar:");
                Eliminar_usuario(id);
            }
            case 2 -> {
                int id = MetodosInterface.recibirEntero("Digita el id del plan de entrenamiento:");
                String nombre = MetodosInterface.recibirString("Digita el nombre del plan de entrenamiento:");
                String descripcion = MetodosInterface.recibirString("Digita la descripcion del plan de entrenamiento:");
                String tipo = MetodosInterface.recibirString("Digita el tipo:");
                String imagen = MetodosInterface.recibirString("Digita la ruta de la imagén:");
                agregar_plan_de_entrenamiento(id, nombre, descripcion, tipo, imagen);
            }
            case 3 -> {
                int id = MetodosInterface.recibirEntero("Digita el id plan de entrenamiento que quieres actualizar:");
                String nombre = MetodosInterface.recibirString("Digita el nombre del plan de entrenamiento que quieres actualizar:");
                String descripcion = MetodosInterface.recibirString("Digita la descripcion del plan de entrenamiento que quieres actualizar:");
                String tipo = MetodosInterface.recibirString("Digita el tipo del plan de entrenamiento que quieres actualizar:");
                String imagen = MetodosInterface.recibirString("Digita la ruta de la imagén:");
                actualizar_plan_entrenamiento(id, nombre, descripcion, tipo, imagen);
            }
            case 4 -> {
                int id = MetodosInterface.recibirEntero("Digita el id plan de entrenamiento que quieres eliminar:");
                eliminar_plan_de_entrenamiento(id);
            }
            case 5 -> {
                int id = MetodosInterface.recibirEntero("Digita el id del perro del que quieres consultar el historial:");
                consultar_historial_perro(id);
            }
            case 6 -> {
                int id = MetodosInterface.recibirEntero("Digita el id del usuario del que quieres consultar el historial:");
                consultar_historial_usuario(id);
            }
            case 7 -> {
                int id = MetodosInterface.recibirEntero("Digita el id del perro del que quieres consultar el progreso:");
                consultar_proceso_mascota(id);
            }
            case 8 -> {
                int id = MetodosInterface.recibirEntero("Digita el id del perro del que quieres generar la retroalimentación:");
                generar_retroalimentacion(id);
            }    case 9 -> {
                int id = MetodosInterface.recibirEntero("Digita el id del perro:");
                int id_plan = MetodosInterface.recibirEntero("Digita el id del plan:");

                asignar_plan_de_entrenamiento(id,id_plan);
            }    
                    
            default ->
menuFuncionesAdmin();      
        }
        
        menuFuncionesAdmin();        

    }

}
