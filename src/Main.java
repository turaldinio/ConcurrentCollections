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

        for (int a = 1; a < 1_000_000; a *= 10) {
            synchronizedMap.multiThreadingPut(a);

            concurrentMap.multiThreadingPut(a);
            System.out.println("---------------");
        }
    }

}




