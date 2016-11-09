package training.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class represents factory of mock proxy. These object can store threadsafe method call history.
 *
 * Methods of this class can easily retrieve history.
 *
 * By default proxy returns default values for all methods.
 * You can override method {@link #getReturnValue(Method)} to define needed behaivor and return values.
 *
 * @version 1.1 09 NOV 2016
 * @author oleksij.onysymchuk@gmail
 */
public class MockFactory {

    /**
     * Creates mock proxy defined by instance of this class.
     * Proxy methods behaivor defined by {@link #getReturnValue(Method)}
     *
     * @param clazz The value of class, mock instance of which has to be created
     * @param <T>  the future reference type of proxy which is creating
     * @return The reference to the instance of mock proxy object with defined by the instance of factory.
     */
    public <T> T getMockProxy(Class clazz) {
        Class[] interfaces = clazz.getInterfaces();
        InvocationHandler invocationHandler = new CustomInvocationHandler();
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, invocationHandler);
    }

    /**
     * Defining of invocation handler, which stores thread-safe method call history
     */
    class CustomInvocationHandler implements InvocationHandler {
        private List<Method> methodList = new CopyOnWriteArrayList<>();

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            methodList.add(method);
            return getReturnValue(method);
        }

        public List<Method> getMethodList() {
            return methodList;
        }
    }

    /**
     * Default implementation of return method logic.
     *
     * You can override this method for defining needed methods behaviour and return values
     * depending on it's type, params, modifiers etc and any your needs.
     *
     * @param method The value of method, which behavior has to be defined
     * @return The default type value in this implementation.
     */
    protected Object getReturnValue(Method method){
        if (method.getReturnType() == byte.class) return 0;
        if (method.getReturnType() == short.class) return 0;
        if (method.getReturnType() == char.class) return 0;
        if (method.getReturnType() == int.class) return 0;
        if (method.getReturnType() == long.class) return 0;
        if (method.getReturnType() == float.class) return 0.0;
        if (method.getReturnType() == double.class) return 0.0;
        if (method.getReturnType() == boolean.class) return false;
        return method.getDefaultValue();
    }

    /**
     * @param mockProxy the reference to MockFactory instance
     * @return List of methods call history
     */
    public List<Method> getMethodCallHistory(Object mockProxy) {
        if (!(mockProxy instanceof Proxy)) {
            throw new RuntimeException("The instance is not a Proxy object!");
        }
        InvocationHandler handler = Proxy.getInvocationHandler(mockProxy);
        if (!(handler instanceof CustomInvocationHandler)) {
            throw new RuntimeException("The instance is not a MockFactory object!");
        }
        return ((CustomInvocationHandler) handler).getMethodList();
    }


}
