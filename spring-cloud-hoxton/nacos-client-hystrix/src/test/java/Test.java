import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    static AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        for (int i = 0; i < 10; i++) {
            System.out.println(incrementAndGetModulo(list.size()) + "----" + nextServerCyclicCounter.get());
        }
    }

    private static int incrementAndGetModulo(int modulo) {
        for (; ; ) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next))
                return next;
        }
    }
}
