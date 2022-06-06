import java.util.Arrays;

public class Dependence {
    public static int addOne(int i) {
        return i + 1;
    }

    public int addAll(int first, Integer... numbers) {
        return first + Arrays.stream(numbers).reduce(0, Integer::sum);
    }
}
