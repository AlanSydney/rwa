package util;

import com.google.common.collect.ImmutableMultiset;

import java.util.List;

public class TestHelper {
    public static boolean isEqualIgnoringOrder(List<String> x, List<String> y) {
        if (x == null) {
            return y == null;
        }

        if (x.size() != y.size()) {
            return false;
        }

        return ImmutableMultiset.copyOf(x).equals(ImmutableMultiset.copyOf(y));
    }
}
