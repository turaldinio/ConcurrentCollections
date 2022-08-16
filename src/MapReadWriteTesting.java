import java.util.Iterator;
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


        try {
            Thread put = new Thread(() -> {
                long start = System.currentTimeMillis();

                for (int a = 0; a < size; a++) {
                    map.put(a, a);
                }
                long end = System.currentTimeMillis();
                System.out.println("Вставка " + (end - start));

            });


            Thread read = new Thread(() -> {
                long start = System.currentTimeMillis();
                Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    iterator.next();
                }
                long end = System.currentTimeMillis();
                System.out.println("Чтение " + (end - start));

            });
            put.start();
            Thread.sleep(200);
            read.start();
            put.join();
            read.join();

            System.out.println("----------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
