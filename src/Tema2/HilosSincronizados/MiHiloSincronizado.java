package Tema2.HilosSincronizados;

import java.util.Random;

public class MiHiloSincronizado extends Thread{

        String nombre;
        ContenidoCompartido b;
        public MiHiloSincronizado(String n, ContenidoCompartido b) {
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
