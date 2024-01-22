
package proyecto_inf_software;
import proyecto_inf_software.MetodosInterface;
import java.util.Timer;
import java.util.TimerTask;

public class Notificaciones {
 
    
    public static void Notificacion_Tiempo(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               MetodosInterface.imprimir("¡Hola! Han pasado 15 sg desde que entraste a la aplicación, ¿Qué tal te ha parecido la experiencia?");
            }
        }, 15000); 
    }
    
    
    public static void Notificacion_progreso(){
        boolean progressAchieved = true;

        if (progressAchieved) {
            enviarNotificacion("¡Felicidades! Has logrado un progreso significativo.");
        }
    }
   
    public static void Notificacion_recordatorio() {
        boolean reminderCondition = true;

        if (reminderCondition) {
            enviarNotificacion("Recuerda realizar tu tarea diaria.");
        }
    }

    private static void enviarNotificacion(String mensaje) {
        MetodosInterface.imprimir("Notificación: " + mensaje);
    }
}