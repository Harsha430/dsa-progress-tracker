public class Threads {
    public static void main(String[] args) {
        Runnable task = () -> handleRequest();
        Thread.startVirtualThread(task);

    }

    static void handleRequest() {
        System.out.println("Handling request on OS thread");
        try {
            Thread.sleep(1000); // simulate blocking I/O
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
