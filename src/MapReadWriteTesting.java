import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;

public abstract class MapReadWriteTesting {
    private Map<Integer, Integer> map;
    private final String mapType;

    public MapReadWriteTesting(String mapType, Map<Integer, Integer> map) {
        this.mapType = mapType;
        this.map = map;
    }

    public MapReadWriteTesting(Map<Integer, Integer> map) {
        this.map = map;
        this.mapType = this.getClass().getSimpleName();
    }


    public void multiThreadingPut(int size) {
        long start = System.currentTimeMillis();


        Thread first = new Thread(() -> {
            for (int a = 0; a <= size; a++) {
                map.put(a, a);
            }

        });

        Thread second = new Thread(() -> {
            for (int a = size; a >= 0; a--) {
                map.put(a, a);
            }

        });

        first.start();
        second.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.printf("%s: Вставка %d элементов %d мс%n", mapType, size, (end - start));
        multiThreadingRead(size);


    }

    public void multiThreadingRead(int size) {
        long start = System.currentTimeMillis();


        Thread first = new Thread(() -> {
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {

                iterator.next();

            }
        });
        Thread second = new Thread(() -> {
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                iterator.next();
            }
        });
        first.start();
        second.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.printf("%s: Чтение %d элементов %d мс%n", mapType, size, (end - start));
    }

}
