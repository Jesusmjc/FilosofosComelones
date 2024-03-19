import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
    private final int id;
    private final Semaphore[] tenedores;

    public Filosofo(int id, Semaphore[] tenedores) {
        this.id = id;
        this.tenedores = tenedores;
    }

    private void pensar() {
        System.out.println("Filósofo " + id + " está pensando...");
        try {
            // Simula un tiempo de pensamiento
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void comer() {
        System.out.println("Filósofo " + id + " está comiendo.");
        try {
            // Simula un tiempo de comer
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            
            try {
                // Intenta obtener el tenedor de la izquierda
                tenedores[id].acquire();
                System.out.println("Filósofo " + id + " tiene el tenedor izquierdo.");

                // Intenta obtener el tenedor de la derecha
                tenedores[(id + 1) % tenedores.length].acquire(); //(id + 1) % forks.length garantiza que no se sobrepasen los límites del arreglo.
                System.out.println("Filósofo " + id + " tiene el tenedor derecho y está listo para comer.");
                comer(); // Come
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Libera los tenedores
                tenedores[id].release();
                tenedores[(id + 1) % tenedores.length].release();
                System.out.println("Filósofo " + id + " ha liberado los tenedores.");
            }
        }
    }
}
