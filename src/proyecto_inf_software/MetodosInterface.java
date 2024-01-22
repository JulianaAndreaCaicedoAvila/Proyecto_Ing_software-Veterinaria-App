
package proyecto_inf_software;


import java.util.Scanner;
import javax.swing.JOptionPane;// para imprimir en ventana 
/**
 * Esta clase nos tiene diversos metodos como recibir os datos que digite el usuario e imprimirlos.
 */
public class MetodosInterface {
	public static Scanner teclado=new Scanner(System.in);
	public static final boolean WINDOWS = true;// para imprimir en venta 

	
	public  static void imprimir(String msg) {
		if(WINDOWS){
			javax.swing.UIManager.put("OptionPane.messageFont", new java.awt.Font(java.awt.Font.MONOSPACED, java.awt.Font.PLAIN, 20));
			JOptionPane.showMessageDialog(null,msg);
		}
		else {
			System.out.println(msg);
		}
}
            public static boolean recibirBoolean(String info) {
        boolean dato = false;
        boolean entradaValida = false;
        do {
            String ax = recibirString(info);
            if (ax.equalsIgnoreCase("true") || ax.equalsIgnoreCase("false")) {
                dato = Boolean.parseBoolean(ax);
                entradaValida = true;
            } else {
                MetodosInterface.imprimir("Error, ingresa: 'true' o 'false'.");
            }
        } while (!entradaValida);
        return dato;
    }
	//*********************************************************
	/**
	 *  Recibe un entero por parte del usuario 
	 *  en caso de error de digitación vuelve a pedir el dato 
	 * @param info
	 * @return el entero digitado por el usuario
	 */
	public static int recibirEntero(String info){
		int dato=Integer.MAX_VALUE;
		do{
			String ax=recibirString(info);
			try{	
				dato=Integer.parseInt(ax);
			}
			catch( NumberFormatException e){
				imprimir("Error: digita un dato de tipo int: " + e.getMessage());

			}
		}
		while(dato==Integer.MAX_VALUE);
		return dato;
	}
	//*********************************************************
	/**
	 * 	 *  Recibe un double por parte del usuario 
	 *  en caso de error de digitación vuelve a pedir el dato 
	 * @param info
	 * @return el double digitado por el usuario
	 */

          public static byte[] recibirBytes(String info) {
        byte[] datos = null;
        do {
            String ax = recibirString(info);
            try {
                // Convierte la representación en String a un arreglo de bytes
                datos = ax.getBytes();
            } catch (NumberFormatException e) {
                imprimir("Error:  digita un dato de tipo Byte: " + e.getMessage());
            }
        } while (datos == null);
        return datos;
    }
        
	public static double recibirDouble(String info){

		double dato=Double.NaN;
		do{
			String ax=recibirString(info);
			try{	
				dato=Double.parseDouble(ax);
			}
			catch( NumberFormatException e){
				imprimir("Error: digita un dato de tipo Double: " + e.getMessage());

			}
		}
		while(dato==Double.NaN);
		return dato;
	}	//*********************************************************
	/**
	 * recibirString();
	 * Recibe los datos en String (letras).
	 * @param info
	 * @return el string digitado por el usuario
	 */
	public static String recibirString(String info) {
		String ax;
		if(WINDOWS)
			ax =JOptionPane.showInputDialog(info.toLowerCase());
		else {
			System.out.print(info);
			ax =teclado.nextLine();
		}
		return ax;
	}
	//*********************************************************
	/**
	 * Cerrar el objeto Scanner para liberar el recurso
	 */
	public static void close() {
		teclado.close();
	}
	//*********************************************************
	/**
	 * Saludar();
	 * Le da la bienvenida a el usuario.
	 */
	public static void Saludar(){
	MetodosInterface.imprimir("Holaaa, estás en el proyecto examén :3 ");
	}
	//*********************************************************
	/**
	 * Depedirse();
	 * Le da las gracias a el usuario por usar el programa.
	 */
	public static void Despedirse() {
		  MetodosInterface.imprimir("Gracias por usar el programa :3");		
	}

   
        
        
	
}


