import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        // Número de filósofos (y tenedores)
        int numeroFilosofos = 5;

        // Arreglo de semáforos para representar los tenedores
        Semaphore[] tenedores = new Semaphore[numeroFilosofos];
        for (int i = 0; i < numeroFilosofos; i++) {
            tenedores[i] = new Semaphore(1); // Inicialmente, cada tenedor está disponible
        }

        // Crear e iniciar los hilos para los filósofos
        Filosofo[] filosofos = new Filosofo[numeroFilosofos];
        for (int i = 0; i < numeroFilosofos; i++) {
            filosofos[i] = new Filosofo(i, tenedores);
            filosofos[i].start();
        }
    }
}