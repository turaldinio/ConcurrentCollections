import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TechnicalSupport {
    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

    public String serveClient() {
        try {
            while (!queue.isEmpty()) {
                Thread.sleep((int) (Math.random() * 3000));
                System.out.println("Клиент " + queue.take() + " обслужен");
            }
        } catch (InterruptedException e) {
            return null;
        }
        return "все клиенты обслужены";

    }

    public void getInLine(String userNikName) {
        try {
            queue.put(userNikName);
            System.out.println("Клиент " + userNikName + " встал в очередь");
        } catch (InterruptedException e) {
            return;
        }
    }
}
