package training.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public class MockProxy {

    public static <T> T getMockProxy(Class clazz) {
        Class[] interfaces = clazz.getInterfaces();
        InvocationHandler invocationHandler = new CustomInvocationHandler();
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, invocationHandler);
    }

    static class CustomInvocationHandler implements InvocationHandler {
        private List<Method> methodList = new CopyOnWriteArrayList<>();

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            /* Here may be any replaced methods' logic and returns depends on name, parameters etc.
             * and your test tasks */

            methodList.add(method);
            System.out.println(method.getName() + " " + method.getReturnType().getSimpleName());
            if (method.getReturnType() == int.class) return 0;
            return method.getDefaultValue();
        }

        public List<Method> getMethodList() {
            return methodList;
        }
    }

    /**
     * Example of using method call history to build a map of method call counters
     *
     * @param mockProxy the reference to MockProxy instance
     * @return Map of method call counters
     */
    public static Map<String, Integer> getMethodCallCounters(Object mockProxy) {
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

    /**
     * @param mockProxy the reference to MockProxy instance
     * @return List of methods call history
     */
    public static List<Method> getMethodCallHistory(Object mockProxy) {
        if (!(mockProxy instanceof Proxy)) {
            throw new RuntimeException("The instance is not a Proxy object!");
        }
        InvocationHandler handler = Proxy.getInvocationHandler(mockProxy);
        if (!(handler instanceof CustomInvocationHandler)) {
            throw new RuntimeException("The instance is not a MockProxy object!");
        }
        return ((CustomInvocationHandler) handler).getMethodList();
    }


}
