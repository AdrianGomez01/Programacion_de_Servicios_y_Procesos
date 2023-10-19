package Tema2.EmborrachandoSidra;

class MonitorSidra {
    private boolean sidraEscanciada = false;
    public synchronized void beboSidra() {
        while (!sidraEscanciada) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Que rica !");
        /*Una vez termine de beber, vuelvo a poner la sidra escanciada a false para que el camarero
        tenga que volver a escanciarla al servir al siguiente alumno.*/
        sidraEscanciada=false;
    }
    public synchronized void camareroEscancia() {
        this.sidraEscanciada = true;
        notifyAll();
        System.out.println("Sidra escanciada...");
    }
}
class BebiendoSidra {
    public static void main(String[] args) throws InterruptedException {
        MonitorSidra objetoMonitor = new MonitorSidra();
        Camarero hilocam = new Camarero( objetoMonitor);
        //Creo un hilo para cada alumno y los ejecuto
        Cliente hiloAdrian = new Cliente( objetoMonitor, "Adrian",27);
        Cliente hiloEnrique = new Cliente( objetoMonitor, "Enrique",22);
        Cliente hiloIsrael = new Cliente( objetoMonitor, "Israel",18);
        Cliente hiloMiguel = new Cliente( objetoMonitor, "Miguel",15);
        hiloAdrian.start();
        hiloEnrique.start();
        hiloIsrael.start();
        hiloMiguel.start();
        hilocam.start();
    }
}
class Camarero extends Thread{
    MonitorSidra objetoMonitor;
    public Camarero(MonitorSidra objetoMonitor) {
        this.objetoMonitor = objetoMonitor;
    }
    public void run() {
        objetoMonitor.camareroEscancia();
    }
}
class Cliente extends Thread{
    MonitorSidra objetoMonitor;
    Integer edad;
    String nombre;
    /*Le añado al constructor de cliente el parámetro de edad para comprobar que sea mayor de edad, y el parámetro
    nombre para identificar a cada alumno facialmente*/
    public Cliente(MonitorSidra objetoMonitor, String nombre, Integer edad) {
        this.objetoMonitor = objetoMonitor;
        this.edad = edad;
        this.nombre = nombre;
    }
    public void run() {
        /*Si el alumno es mayor de edad, comienza a beber la sidra, y cuando termina el camarero comienza a
        escanciar la siguiente sidra.*/
        if (edad >= 18) {
            System.out.println(nombre + " está bebiendo sidra...");
            objetoMonitor.beboSidra();
            objetoMonitor.camareroEscancia();

        } else {
            //Si es menor de edad muestro por pantalla quien es el que no puede beber y la edad que tiene
            System.out.println( nombre + " no puede beber! Solo tiene " + edad + " años!");
        }
    }
}