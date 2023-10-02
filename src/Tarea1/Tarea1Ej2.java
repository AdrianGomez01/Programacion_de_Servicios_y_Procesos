package Tarea1;

import java.io.IOException;

public class Tarea1Ej2 {

    public static void main(String[] args) {

        if (args.length <= 0) {
            System.out.println("Debe indicarse comando a ejecutar.");
            System.exit(1);
        }

        ProcessBuilder pb = new ProcessBuilder(args);
        pb.inheritIO();

        /**
         * Dentro de este bloque Try lanzamos el proceso y comprobamos si sigue en
         * ejecución, si es así esperaremos 3 segundos hasta volver a comprobarlo,
         * si ya ha terminado cerraremos la operación.
         */
        try {
            Process p = pb.start();
            while (true) {
                if (!p.isAlive()) {
                    System.out.println("El proceso ha finalizado");
                    break;
                } else {
                    System.out.println("El proceso está en ejecucion...");
                    Thread.sleep(3000);
                }
            }

        } catch (IOException e) {
            System.err.println("Error durante ejecución del proceso");
            System.err.println("Información detallada");
            System.err.println("---------------------");
            e.printStackTrace();
            System.err.println("---------------------");
            System.exit(2);
        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido");
            System.exit(3);
        }

    }

}
