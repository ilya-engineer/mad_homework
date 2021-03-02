package homework;

import java.util.Comparator;
import java.util.stream.Stream;

public interface AdvancedList<T> extends SimpleList<T> {
    AdvancedList<T> shuffle() throws Exception;
    AdvancedList<T> sort(Comparator<T> comparator);
}
