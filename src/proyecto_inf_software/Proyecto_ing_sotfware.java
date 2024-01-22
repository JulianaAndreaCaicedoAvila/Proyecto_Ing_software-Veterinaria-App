/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_inf_software;

import proyecto_inf_software.Notificaciones;
import proyecto_inf_software.MetodosInterface;
import proyecto_inf_software.Cliente;
import proyecto_inf_software.Admin;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class Proyecto_ing_sotfware extends JFrame {

    static Connection connection = null;

    public static void main(String[] args) throws SQLException, IOException {
        Notificaciones.Notificacion_Tiempo();
        Generar_conexion();
        menu_usuarios();

    }
    private static void Generar_conexion() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/Ingenieria de software 1 proyecto", "postgres", "123");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void menu_usuarios() throws SQLException, IOException {
        int opcion = MetodosInterface.recibirEntero("¿Qué tipo de usuario eres?\n"
                + "1. Dueño mascota.\n"
                + "2. Admin");
        if (opcion == 1) {
            Cliente.menu_logeo_Usurios();
            Cliente.Menu_principal_usuario();

            
        } else if(opcion ==2) {
            Admin.menu_logeo_admin();
            Admin.menuFuncionesAdmin();
  
        }else {
            menu_usuarios();
        }

    }
}
