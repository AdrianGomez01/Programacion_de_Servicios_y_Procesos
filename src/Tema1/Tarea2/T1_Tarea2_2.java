package Tema1.Tarea2;

import java.util.Map;
import java.util.Iterator;

public class T1_Tarea2_2 {
    public static void main(String[] args) {

        try {
            ProcessBuilder pb = new ProcessBuilder("ls");

            Process proceso = pb.start();

            // Obtenemos el entorno de ejecución del proceso
            Map<String, String> entorno = pb.environment();

            // Iteramos sobre el Map y mostramos cada entrada
            Iterator<Map.Entry<String, String>> iterator = entorno.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String clave = entry.getKey();
                String valor = entry.getValue();
                System.out.println(clave + "=" + valor);
            }
            int resultado = proceso.waitFor();
            System.out.println("El proceso ha finalizado, código de salida: " + resultado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
