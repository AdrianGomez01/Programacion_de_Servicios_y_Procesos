package Tema2.Tarea2;

import java.util.Random;

class NumeroOculto {
    private int numeroOculto;
    private boolean juegoTerminado;

    public NumeroOculto() {
        Random rand = new Random();
        // Generamos un número aleatorio entre 0 y 100
        numeroOculto = rand.nextInt(101);
        juegoTerminado = false;
    }

    public synchronized int propuestaNumero(int num) {
        if (juegoTerminado) {
            // El juego ya terminó porque un hilo adivinó el número
            return -1;
        }
        if (num == numeroOculto) {
            // Un hilo ha adivinado el número
            juegoTerminado = true;
            // El número fue adivinado
            return 1;
        }
        // El número no fue adivinado
        return 0;
    }
}

class Adivinador extends Thread {
    private NumeroOculto numeroOculto;

    public Adivinador(NumeroOculto numeroOculto) {
        this.numeroOculto = numeroOculto;
    }

    @Override
    public void run() {
        while (true) {
            // Generamos un número aleatorio
            int propuesta = new Random().nextInt(101);
            int resultado = numeroOculto.propuestaNumero(propuesta);

            if (resultado == 1) {
                System.out.println("Hilo adivinador ha acertado el número: " + propuesta);
                break;
            } else if (resultado == -1) {
                System.out.println("Hilo adivinador se detiene porque el juego ha terminado.");
                break;
            }
        }
    }
}

public class JuegoAdivinaNumero {
    public static void main(String[] args) {
        NumeroOculto numeroOculto = new NumeroOculto();
        Adivinador[] adivinadores = new Adivinador[10];

        for (int i = 0; i < 10; i++) {
            adivinadores[i] = new Adivinador(numeroOculto);
            adivinadores[i].start();
        }
    }
}