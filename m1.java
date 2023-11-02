class SharedResources {
    private int V;
    private boolean isP = true; // Initialize isP to true to allow the producer to start first

    public synchronized void P(int sR) {
        while (!isP) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        V = sR;
        System.out.println("Produced: " + V);
        isP = false;
        notify();
    }

    public synchronized void C(int sR) {
        while (isP) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        V = sR;
        System.out.println("Consumed: " + V);
        isP = true;
        notify();
    }
}

class P implements Runnable {
    private SharedResources sR;

    public P(SharedResources sR) {
        this.sR = sR;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            sR.P(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class C implements Runnable {
    private SharedResources sR;

    public C(SharedResources sR) {
        this.sR = sR;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            sR.C(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class m1 {
    public static void main(String[] args) {
        SharedResources sR = new SharedResources();

        Thread producer = new Thread(new P(sR));
        Thread consumer = new Thread(new C(sR));

        producer.start();
        consumer.start();
    }
}
