package Tema2.HilosNoSincronizados;

import javax.management.monitor.Monitor;
import java.util.Random;
public class HilosNoSincronizados {
    public static void main(String[] args) {
        // creo un objeto de ContenidoCompartido,
        // que compartiran todos los objetos
        Monitor mont = new Monitor();
        MiHilo objetoMiH1 = new MiHilo("Eva", mont);
        MiHilo objetoMiH2 = new MiHilo("Paz", mont);
        MiHilo objetoMiH3 = new MiHilo("Maria", mont);
        objetoMiH1.start();
        objetoMiH2.start();
        objetoMiH3.start();
        try {
            objetoMiH1.join();
            objetoMiH2.join();
            objetoMiH3.join();
        } catch (InterruptedException e) {
            // no hace nada

        }
            System.out.println("FINAL: " + mont.dato);
        }
    }











