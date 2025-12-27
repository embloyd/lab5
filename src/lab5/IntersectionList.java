package lab5;

import java.util.*;

public class IntersectionList<T> {
    private List<T> result;

    public IntersectionList(List<T> list1, List<T> list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException("Списки не могут быть null.");
        }

        Set<T> set1 = new LinkedHashSet<>(list1);
        Set<T> set2 = new HashSet<>(list2);

        set1.retainAll(set2);
        this.result = new ArrayList<>(set1);
    }

    public List<T> getResult() {
        return new ArrayList<>(result);
    }
}