package Tema2.HilosNoSincronizados;

import javax.management.monitor.Monitor;
import java.util.Random;

public class MiHilo extends Thread{

        String nombre;
        Monitor b;
        public MiHilo(String n, Monitor b) {
            nombre = n;
            this.b = b;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
            try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) { // nada
                }


                b.sumarValor();
                System.out.println("Soy " + nombre + ":" + b.dato);
            }
        }


}
