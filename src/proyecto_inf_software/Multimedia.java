/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_inf_software;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
public class Multimedia extends JFrame{
    
    public Multimedia(String ruta) {
        setTitle("Plan de entrenamiento");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon imageIcon = new ImageIcon(ruta);
        JLabel imageLabel = new JLabel(imageIcon);

        JScrollPane scrollPane = new JScrollPane(imageLabel);

        add(scrollPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }
 public static void VerImagen(String rutaImagen){
        Multimedia imageViewer = new Multimedia(rutaImagen);
    }
}

