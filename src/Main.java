import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        MapReadWriteTesting synchronizedMap = new WorkWithSynchronizedMap("Синхронизированная мапа",
                Collections.synchronizedMap(new HashMap<>()));

        MapReadWriteTesting concurrentMap = new WorkWithConcurrentMap("Потокобезопасная мапа",
                new ConcurrentHashMap<>());
        Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());

        for (Map.Entry<Integer, Integer> pairs : map.entrySet()) {
            pairs.getKey();
            pairs.getValue();
        }

        for (int a = 1; a <= 1_000_000; a *= 10) {
            synchronizedMap.startReadingWriting(a);
        }
        for (int a = 1; a <= 1_000_000; a *= 10) {
            concurrentMap.startReadingWriting(a);
        }
    }
}




