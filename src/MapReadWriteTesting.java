import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class MapReadWriteTesting {
    private Map<Integer, Integer> map;
    private String mapType;

    public MapReadWriteTesting(String mapType, Map<Integer, Integer> map) {
        this.mapType = mapType;
        this.map = map;
    }


    public void startReadingWriting(int size) {
        System.out.println(mapType + " " + size + " элементов");

        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try {
            Future<?> put = executorService.submit(() -> {
                for (int a = 0; a < size; a++) {
                    map.put(a, a);
                }
            });


            put.get();

            long end = System.currentTimeMillis();
            System.out.println("Вставка " + (end - start));

            start = System.currentTimeMillis();
            Future<?> read = executorService.submit(() -> {
                for (Map.Entry<Integer, Integer> pairs : map.entrySet()) {
                    pairs.getKey();
                    pairs.getValue();
                }
            });
            read.get();
            end = System.currentTimeMillis();

            System.out.println("Чтение: " + (end - start));

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        executorService.shutdown();
        System.out.println("----------------------");
    }
}

