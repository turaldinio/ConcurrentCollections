import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        TechnicalSupport support = new TechnicalSupport();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Callable<String>> list = new ArrayList<>();

        new Thread(() -> {
            for (int a = 0; a < 60; a++) {
                support.getInLine(getRandomNick("asdfghjklqwertyuiopzxcvbnm"));
            }
        }).start();


        Thread.sleep(200);
        for (int a = 0; a < 5; a++) {
            list.add(support::serveClient);
        }

        String result = executorService.invokeAny(list);
        System.out.println(result);

        executorService.shutdown();

    }

    public static String getRandomNick(String line) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int a = 0; a < 5; a++) {
            stringBuilder.append(line.charAt(random.nextInt(line.length())));
        }
        return stringBuilder.toString();
    }
}
