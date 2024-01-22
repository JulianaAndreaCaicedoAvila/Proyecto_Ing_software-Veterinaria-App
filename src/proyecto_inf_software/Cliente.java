/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_inf_software;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import proyecto_inf_software.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;

import static proyecto_inf_software.Proyecto_ing_sotfware.connection;

public class Cliente extends JFrame {

    public static void Registrar_Usuario(int id_usuario, String nombre, String correo, String usuario, String contrasena, int telefono) throws SQLException {
        String query = "INSERT INTO usuario (id_usuario, nombre,correo, usuario, contrasena,telefono) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_usuario);
        statement.setString(2, nombre);
        statement.setString(3, correo);
        statement.setString(4, usuario);
        statement.setString(5, contrasena);
        statement.setInt(6, telefono);

        int filasInsertadas = statement.executeUpdate();

        if (filasInsertadas > 0) {
            MetodosInterface.imprimir("El cliente se ha registrado exitosamente.");

            String historial = "INSERT INTO Historial_Clientes (id_cliente, accion) VALUES (?, ?)";
            PreparedStatement Historial = connection.prepareStatement(historial);
            Historial.setInt(1, id_usuario);
            Historial.setString(2, "Registro");
            Historial.executeUpdate();
            System.out.println("El cliente se ha registrado exitosamente.");
            Historial.close();

        } else {
            MetodosInterface.imprimir("No se pudo insertar el cliente.");
        }
        statement.close();

    }

    public static void Menu_principal_usuario() throws SQLException {
        Notificaciones.Notificacion_recordatorio();
        MetodosInterface.imprimir("Bienvenido a tu app para entrenar a tu mascota");
        int opcion = MetodosInterface.recibirEntero("¿Qué deseas hacer en la aplicación?\n"
                + "1.Sección mascotas.\n"
                + "2.Sección de entrenamiento.");
        switch (opcion) {
            case 1 -> {
                int seleccion = MetodosInterface.recibirEntero("Entraste a la sección mascotas, selecciona la acción que deseas realizar:\n"
                        + "1.Registrar nueva mascota.\n"
                        + "2.Consultar información macotas.\n"
                        + "3.Consultar proceso mascotas.\n"
                        + "4.Registrar proceso mascota.\n"
                        + "5.Actualizar datos de la mascota.\n"
                        + "6.Consultar información usuario.\n"
                        + "7.Actualizar datos del usuario.\n"
                        + "8.Eliminar mascota. \n");
                        
                switch (seleccion) {
                    case 1 -> {
                        int id_perro = MetodosInterface.recibirEntero("Digita el id perro:");
                        String nombre = MetodosInterface.recibirString("Digita el nombre:");
                        int edad = MetodosInterface.recibirEntero("Digita la edad del perro:");
                        String raza = MetodosInterface.recibirString("Digita la raza:");
                        int nivel_comportamiento = MetodosInterface.recibirEntero("Digita el nivel de comportamiento:");
                        int id_usuario = MetodosInterface.recibirEntero("Digita el id usuario::");
                        Registrar_mascota(id_perro, nombre, edad, raza, nivel_comportamiento, id_usuario);
                    }
                    case 2 -> {
                        int id_perro = MetodosInterface.recibirEntero("Digita el id perro:");
                        consultar_informacion_mascota(id_perro);
                    }
                    case 3 -> {
                        int id_perro = MetodosInterface.recibirEntero("Digita el id perro:");
                        consultar_proceso_mascota(id_perro);
                    }
                    case 4 -> {
                        int id = MetodosInterface.recibirEntero("id perro");
                        int id2 = MetodosInterface.recibirEntero("id plan");
                        boolean a = MetodosInterface.recibirBoolean("Logro");
                        registrar_proceso_mascota(id, id2, a);

                    }
                    case 5 -> {
                        int id_perro = MetodosInterface.recibirEntero("Digita el id perro:");
                        String nombre = MetodosInterface.recibirString("Digita el nombre:");
                        int edad = MetodosInterface.recibirEntero("Digita la edad del perro:");
                        String raza = MetodosInterface.recibirString("Digita la raza:");
                        int nivel_comportamiento = MetodosInterface.recibirEntero("Digita el nivel de comportamiento:");
                        int id_propietario = MetodosInterface.recibirEntero("Digita el id usuario:");
                        actualizar_datos_perro(id_perro, nombre, edad, raza, nivel_comportamiento, id_propietario);
                    }
                    case 6 -> {
                        int id_usuario = MetodosInterface.recibirEntero("Digita el id_usuario:");
                        consultar_informacion_usuario(id_usuario);
                    }
                    case 7 -> {
                        int id_usuario = MetodosInterface.recibirEntero("Digita el id usuario:");
                        String nombre = MetodosInterface.recibirString("Digita el nombre:");
                        String correo = MetodosInterface.recibirString("Digita el correo:");
                        String usuario = MetodosInterface.recibirString("Digita el usuario:");
                        String contrasena = MetodosInterface.recibirString("Digita el contraseña:");
                        int telefono = MetodosInterface.recibirEntero("Digita el telefono:");

                        actualizar_datos_usuario(id_usuario, nombre, correo, usuario, contrasena, telefono);
                    }
                    case 8 -> {
                        int id_perro = MetodosInterface.recibirEntero("Digita el id perro:");
                        eliminar_mascota(id_perro);
                    } default ->
 Menu_principal_usuario();  
                }
            }
            case 2 -> {
                int seleccion = MetodosInterface.recibirEntero("Entraste a la sección plan de entrenamiento, selecciona la acción que deseas realizar:\n"
                        + "1.Ver todos los logros de mi mascota.\n"
                        + "2.Ver lista de estado de tareas logradas .\n"
                        + "3.Ver lista de estado de tareas pendientes.\n"
                        + "4.Ver plan de entremamiento:");

                switch (seleccion) {
                    case 1 -> {
                        int id = MetodosInterface.recibirEntero("id perro");
                        consultarTodosLogros(id);
                    }
                    case 2 -> {
                        int id = MetodosInterface.recibirEntero("id perro");
                        consultarLogrosAlcanzados(id);
                    }
                    case 3 -> {
                        int id = MetodosInterface.recibirEntero("id perro");
                        consultarLogrosNoAlcanzados(id);
                    }
                    case 4 -> {
                        int id = MetodosInterface.recibirEntero("id perro");
                        VerImagenEntrenamiento(id);
                    }
                }
            }
            default ->
                    Menu_principal_usuario();    
        }
       
 Menu_principal_usuario(); 
    }
///// 1 case

    public static void Registrar_mascota(int id_perro, String nombre, int edad, String raza, int nivel_comportamiento, int id_usuario) throws SQLException {
        String query = "INSERT INTO perro (id_perro, nombre,edad, raza, nivel_comportamiento,propietario_id) VALUES ( ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_perro);
        statement.setString(2, nombre);
        statement.setInt(3, edad);
        statement.setString(4, raza);
        statement.setInt(5, nivel_comportamiento);
        statement.setInt(6, id_usuario);

        int filasInsertadas = statement.executeUpdate();

        if (filasInsertadas > 0) {
            MetodosInterface.imprimir("El cliente se ha registrado exitosamente.");

            String historial = "INSERT INTO Historial_Mascotas(id_usuario,id_perro, accion) VALUES (?, ?, ?)";
            PreparedStatement Historial = connection.prepareStatement(historial);
            Historial.setInt(1, id_usuario);
            Historial.setInt(2, id_perro);
            Historial.setString(3, "Registro");
            Historial.executeUpdate();
            Historial.close();

        } else {
            MetodosInterface.imprimir("No se pudo insertar la mascota.");
        }
        statement.close();

    }

    public static boolean consultar_informacion_mascota(int id_perro) throws SQLException {
        boolean existePerro = false;
        String query = "SELECT * FROM perro WHERE id_perro = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_perro);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int idPerro = resultSet.getInt("id_perro");
            String nombre = resultSet.getString("nombre");
            int edad = resultSet.getInt("edad");
            String raza = resultSet.getString("raza");
            int nivelComportamiento = resultSet.getInt("nivel_comportamiento");
            int idPropietario = resultSet.getInt("propietario_id");

            MetodosInterface.imprimir("Información del perro:");
            MetodosInterface.imprimir("ID del Perro: " + idPerro);
            MetodosInterface.imprimir("Nombre: " + nombre);
            MetodosInterface.imprimir("Edad: " + edad);
            MetodosInterface.imprimir("Raza: " + raza);
            MetodosInterface.imprimir("Nivel de Comportamiento: " + nivelComportamiento);
            MetodosInterface.imprimir("ID del Propietario: " + idPropietario);
            existePerro = true;
            Admin.generar_retroalimentacion(id_perro);

        } else {
            MetodosInterface.imprimir("El perro \"" + id_perro + "\" no existe");
        }
        return existePerro;
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
            Admin.generar_retroalimentacion(id_perro);
        }
    }

    /*//**///
    public static void registrar_proceso_mascota(int idPerro, int idPlan, boolean logroAlcanzado) throws SQLException {
        String query = "UPDATE progreso_mascota SET logro_alcanzado = ? WHERE id_perro = ? AND id_plan = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, logroAlcanzado);
            statement.setInt(2, idPerro);
            statement.setInt(3, idPlan);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                MetodosInterface.imprimir("Progreso actualizado para el perro con ID " + idPerro + " y el plan con ID " + idPlan);
            } else {
                MetodosInterface.imprimir("No se pudo actualizar el progreso para el perro con ID " + idPerro + " y el plan con ID " + idPlan);
            }
        }
    }

    public static boolean consultar_informacion_usuario(int id_usuario) throws SQLException {
        boolean existeUsuario = false;
        String query = "SELECT * FROM usuario WHERE id_usuario = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_usuario);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id_Usuario = resultSet.getInt("id_usuario");
            String nombre = resultSet.getString("nombre");
            String correo = resultSet.getString("correo");
            String usuario = resultSet.getString("usuario");
            int telefono = resultSet.getInt("telefono");

            MetodosInterface.imprimir("Información del usuario:");
            MetodosInterface.imprimir("ID del usuario: " + id_usuario);
            MetodosInterface.imprimir("Nombre: " + nombre);
            MetodosInterface.imprimir("correo: " + correo);
            MetodosInterface.imprimir("usuario: " + usuario);
            MetodosInterface.imprimir("telefono: " + telefono);
            existeUsuario = true;
        } else {
            MetodosInterface.imprimir("El usuario \"" + id_usuario + "\" no existe");
        }
        return existeUsuario;
    }

    public static void actualizar_datos_perro(int id_Perro, String nombre, int edad, String raza, int nivel_comportamiento, int id_propetario) throws SQLException {
        String query = "UPDATE perro SET nombre = ?, edad = ?, raza = ?, nivel_comportamiento = ?,propietario_id = ? WHERE id_perro = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nombre);
        statement.setInt(2, edad);
        statement.setString(3, raza);
        statement.setInt(4, nivel_comportamiento);
        statement.setInt(5, id_propetario);
        statement.setInt(6, id_Perro);

        int filasAfectadas = statement.executeUpdate();

        if (filasAfectadas > 0) {
            MetodosInterface.imprimir("Datos de la mascota actualizados con éxito.");
        } else {
            MetodosInterface.imprimir("No se pudo actualizar los datos de la mascota.");
        }

    }

    public static void actualizar_datos_usuario(int id_usuario, String nombre, String correo, String usuario, String contrasena, int telefono) throws SQLException {
        String query = "UPDATE usuario SET nombre = ?, correo = ?, usuario = ?, contrasena = ? , telefono= ? WHERE id_usuario = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nombre);
        statement.setString(2, correo);
        statement.setString(3, usuario);
        statement.setString(4, contrasena);
        statement.setInt(5, telefono);
        statement.setInt(6, id_usuario);

        int filasInsertadas = statement.executeUpdate();

        if (filasInsertadas > 0) {
            MetodosInterface.imprimir("El cliente se ha actualizado exitosamente.");

            String historial = "INSERT INTO Historial_Clientes (id_cliente, accion) VALUES (?, ?)";
            try (PreparedStatement Historial = connection.prepareStatement(historial)) {
                Historial.setInt(1, id_usuario);
                Historial.setString(2, "Actualizo datos");
                Historial.executeUpdate();
                MetodosInterface.imprimir("El cliente se ha actualizado exitosamente.");
            }
        } else {
            MetodosInterface.imprimir("No se pudo actualizar el cliente.");
        }

        statement.close();
    }

    public static void eliminar_mascota(int id_perro) throws SQLException {
        String query = "DELETE FROM perro WHERE id_perro = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_perro);

        int filasEliminadas = statement.executeUpdate();
        if (filasEliminadas > 0) {
            MetodosInterface.imprimir("La mascota se ha eliminado correctamente.");

            String historial = "INSERT INTO Historial_mascotas (id_perro, accion) VALUES (?, ?)";
            PreparedStatement Historial = connection.prepareStatement(historial);
            Historial.setInt(1, id_perro);
            Historial.setString(2, "Eliminación");
            Historial.executeUpdate();
            Historial.close();
        } else {
            MetodosInterface.imprimir("No se encontró el perro con el ID especificado.");
        }
    }
//// 2 case

    public static void consultarTodosLogros(int idPerro) throws SQLException {
        String query = "SELECT id_perro, id_plan, logro_alcanzado FROM progreso_mascota WHERE id_perro = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPerro);

            try (ResultSet resultSet = statement.executeQuery()) {
                MetodosInterface.imprimir("Todos los logros para el perro con ID " + idPerro + ":");

                while (resultSet.next()) {
                    int idPlan = resultSet.getInt("id_plan");
                    boolean logroAlcanzado = resultSet.getBoolean("logro_alcanzado");

                    MetodosInterface.imprimir("Id del perro:" + idPerro + "\n"
                            + "Id del plan: " + idPlan + "\n"
                            + "Logro:" + logroAlcanzado);
                }
            }
        }
    }

    public static void consultarLogrosAlcanzados(int idPerro) throws SQLException {
        String query = "SELECT id_perro, id_plan, logro_alcanzado FROM progreso_mascota WHERE id_perro = ? AND logro_alcanzado = true";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPerro);

            try (ResultSet resultSet = statement.executeQuery()) {
                MetodosInterface.imprimir("Logros alcanzados para el perro con ID " + idPerro + ":");

                while (resultSet.next()) {
                    int idPlan = resultSet.getInt("id_plan");
                    boolean logroAlcanzado = resultSet.getBoolean("logro_alcanzado");
                    MetodosInterface.imprimir("Id del perro:" + idPerro + "\n"
                            + "Id del plan: " + idPlan + "\n"
                            + "Logro:" + logroAlcanzado);

                }
            }
        }
    }

    public static void consultarLogrosNoAlcanzados(int idPerro) throws SQLException {
        String query = "SELECT id_perro, id_plan, logro_alcanzado FROM progreso_mascota WHERE id_perro = ? AND logro_alcanzado = false";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPerro);

            try (ResultSet resultSet = statement.executeQuery()) {
                MetodosInterface.imprimir("Logros No alcanzados para el perro con ID " + idPerro + ":");

                while (resultSet.next()) {
                    int idPlan = resultSet.getInt("id_plan");
                    boolean logroAlcanzado = resultSet.getBoolean("logro_alcanzado");
                    MetodosInterface.imprimir("Id del perro:" + idPerro + "\n"
                            + "Id del plan: " + idPlan + "\n"
                            + "Logro:" + logroAlcanzado);

                }
            }
        }
    }

    public static void VerImagenEntrenamiento(int idPerro) throws SQLException {
        String query = "SELECT id_perro, id_plan FROM progreso_mascota WHERE id_perro = ? ";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPerro);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idPlan = resultSet.getInt("id_plan");
                    Plan(idPlan);
                } else {
                    MetodosInterface.imprimir("No se encontró información para el perro con ID " + idPerro);
                }
            }
        }
    }

    public static void Ruta_imagen(int id_plan) throws SQLException {
        String ruta;
        String query = "SELECT imagen_entrenamiento FROM plandeentrenamiento WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_plan);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ruta = resultSet.getString("imagen_entrenamiento");
                    Multimedia imageViewer = new Multimedia(ruta);
                } else {
                    System.out.println("No se encontraron resultados para el id del plan.");
                }
            }
        }
    }

    public static void Plan(int id) throws SQLException {
        String query = "SELECT id, nombre, descripcion, tipo FROM plandeentrenamiento WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idPlan = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String descripcion = resultSet.getString("descripcion");
                    String tipo = resultSet.getString("tipo");

                    MetodosInterface.imprimir("Id del plan: " + idPlan + "\n" + "Nombre: " + nombre);
                    MetodosInterface.imprimir("Tipo: " + tipo);
                    MetodosInterface.imprimir("Descripcion: " + descripcion);
                    Ruta_imagen(idPlan);
                } else {
                    MetodosInterface.imprimir("No hay plan de entrenamiento para el perro con ID " + id);
                }
            }
        }
    }

////////*//////////////////////// MAIN/////////////////////////
    public static boolean Logeo_Usuario(int id_usuario, String contrasena, String nombre_usuario) {
        String query = "SELECT COUNT(*) AS count FROM usuario WHERE usuario = ? AND contrasena = ? ";

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
                        Inicio_de_sesion();
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

    public static void Inicio_de_sesion() throws SQLException {
        int id_usuario = MetodosInterface.recibirEntero("Digita  id_usuario:");
        String nombre_usuario = MetodosInterface.recibirString("Digita Usuario:");
        String contrasena = MetodosInterface.recibirString("Digita la Contraseña:");

        if (Logeo_Usuario(id_usuario, contrasena, nombre_usuario) == true) {
            MetodosInterface.imprimir("Inicio exitoso");
        } else if (Logeo_Usuario(id_usuario, contrasena, nombre_usuario) == false) {
            MetodosInterface.imprimir("Error, el usuario no es correcto");
        }
    }

    public static void menu_logeo_Usurios() throws SQLException {
        int seleccion = MetodosInterface.recibirEntero("¿Tienes cuenta en la aplicación?\n"
                + "Digita 1 para iniciar sesión.\n"
                + "Digita 2 para registrarte");
        switch (seleccion) {
            case 1 ->
                Inicio_de_sesion();
            case 2 -> {
                int id_usuario = MetodosInterface.recibirEntero("Digita el id usuario");
                String nombre = MetodosInterface.recibirString("Digita el nombre:");
                String correo = MetodosInterface.recibirString("Digita el correo:");
                String usuario = MetodosInterface.recibirString("Digita el usuario:");
                String contrasena = MetodosInterface.recibirString("Digita el contrasena:");
                int telefono = MetodosInterface.recibirEntero("Digita el telefono");
                Cliente.Registrar_Usuario(id_usuario, nombre, correo, usuario, contrasena, telefono);
            }
            default ->
                menu_logeo_Usurios();
        }
    }
}
