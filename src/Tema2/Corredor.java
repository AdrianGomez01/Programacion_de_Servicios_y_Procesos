package Tema2;

import java.util.Random;

class Corredor extends Thread {
    private String nombre;

    public Corredor(String nombre) {
        this.nombre = nombre;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(nombre + " ha completado el tramo " + i + " de la carrera.");
            try {
                // Simulo una pausa aleatoria
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nombre + " ha terminado la carrera.");
    }
}