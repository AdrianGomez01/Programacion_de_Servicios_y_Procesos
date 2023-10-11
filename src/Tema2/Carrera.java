package Tema2;

public class Carrera {
    public static void main(String[] args) {
        Corredor[] corredores = new Corredor[5];
        corredores[0] = new Corredor("Corredor 1");
        corredores[1] = new Corredor("Corredor 2");
        corredores[2] = new Corredor("Corredor 3");
        corredores[3] = new Corredor("Corredor 4");
        corredores[4] = new Corredor("Corredor 5");

        System.out.println("Comienza la carrera...");

        //Hago que todos los corredores empiecen a correr
        for (Corredor corredor : corredores) {
            corredor.start();
        }

        for (Corredor corredor : corredores) {
            try {
                corredor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("La carrera ha terminado.");
    }
}