import java.util.concurrent.Semaphore;

public class ReverseHello extends Thread {
    final Value n;
    final Semaphore thread;
    static class Value {
        int i = 1;
    }

    public static void main(String[] args) {
        Value n = new Value();
        Semaphore semaphore = new Semaphore(1);
        ReverseHello a = new ReverseHello(n, semaphore);
        ReverseHello b = new ReverseHello(n, semaphore);
        a.start();
        b.start();
    }
    public ReverseHello(Value n, Semaphore thread) {
        this.n =n;
        this.thread = thread;
    }
    public void run() {
        try {
            thread.acquire();
            while (n.i <= 50) {
                System.out.println("Hello from Thread " + n.i);
                n.i++;
                thread.release();
                sleep(100);
                thread.acquire();
            }
            thread.release();
        } catch (Exception e) {
            System.out.println("Exception!");
        }
    }
}