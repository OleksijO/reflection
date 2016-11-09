package training.proxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents EXAMPLE of the implementation of mock proxy factory.
 *
 * Methods returns default values
 *
 * Added method to get method statistics of method call counts
 *
 *
 * @version 1.0 09 NOV 2016
 * @author oleksij.onysymchuk@gmail
 */
public class MockWithMethodCallCountFactory extends MockFactory {

    /**
     * Example of using method call history to build a map of method call counters
     *
     * @param mockProxy the reference to MockFactory instance
     * @return Map of method call counters
     */
    public Map<String, Integer> getMethodCallCounters(Object mockProxy) {
        List<Method> methodList = getMethodCallHistory(mockProxy);
        Map<String, Integer> methodCountMap = new HashMap<>();
        methodList.forEach(method -> {
            if (!methodCountMap.containsKey(method.getName())) {
                methodCountMap.put(method.getName(), 0);
            }
            methodCountMap.put(method.getName(), methodCountMap.get(method.getName()) + 1);
        });
        return methodCountMap;
    }
}
