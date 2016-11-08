package training.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public class ImmutableProxy {
    public static <T> T getImmutableProxy(Object obj) {
        Class clazz = obj.getClass();
        Class[] interfaces = clazz.getInterfaces();

        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().startsWith("set")) {
                    throw new UnsupportedOperationException("Object is immutable!");
                }
                return method.invoke(obj, args);
            }
        };
        @SuppressWarnings("unchecked")
        T immutableObject = (T) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, invocationHandler);
        return immutableObject;

    }
}
