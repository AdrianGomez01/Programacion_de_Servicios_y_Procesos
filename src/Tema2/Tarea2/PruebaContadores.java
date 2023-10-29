package Tema2.Tarea2;

class Contadores {
    private long cont1 = 0;
    private long cont2 = 0;
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void incrementar1() {
        synchronized (lock1) {
            cont1++;
        }
    }

    public long getContador1() {
        synchronized (lock1) {
            return cont1;
        }
    }

    public void incrementar2() {
        synchronized (lock2) {
            cont2++;
        }
    }

    public long getContador2() {
        synchronized (lock2) {
            return cont2;
        }
    }
}

class Incrementador extends Thread {
    private Contadores contadores;
    private int numIncrementos;

    public Incrementador(Contadores contadores, int numIncrementos) {
        this.contadores = contadores;
        this.numIncrementos = numIncrementos;
    }

    @Override
    public void run() {
        for (int i = 0; i < numIncrementos; i++) {
            contadores.incrementar1();
        }

        for (int i = 0; i < numIncrementos; i++) {
            contadores.incrementar2();
        }
    }
}

public class PruebaContadores {
    public static void main(String[] args) {
        Contadores contadores = new Contadores();
        int numThreads = 10;
        // Cada hilo se incrementa 10,000,000 veces
        int numIncrementosPorThread = 10000000;

        Incrementador[] incrementadores = new Incrementador[numThreads];

        for (int i = 0; i < numThreads; i++) {
            incrementadores[i] = new Incrementador(contadores, numIncrementosPorThread);
            incrementadores[i].start();
        }

        // Esperamos a que todos los hilos terminen
        for (int i = 0; i < numThreads; i++) {
            try {
                incrementadores[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Comprobamos que ambos contadores alcancen el valor esperado
        System.out.println("Contador 1: " + contadores.getContador1());
        System.out.println("Contador 2: " + contadores.getContador2());

        if (contadores.getContador1() == 100000000 && contadores.getContador2() == 100000000) {
            System.out.println("Ambos contadores alcanzaron el valor esperado.");
        } else {
            System.out.println("Los contadores no alcanzaron el valor esperado.");
        }
    }
}

