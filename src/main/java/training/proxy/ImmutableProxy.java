package training.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * This class represents factory of proxy wrappers around object to change their behaviour to immutable - all methods
 * except that begins with 'set' are allowed. Setter throws instance of {@link UnsupportedOperationException}.
 *
 * NOTE. Wrapped object can be easily changed using it's direct (not proxy) reference.
 *
 * @version 1.0 08 NOV 2016
 * @author oleksij.onysymchuk@gmail
 */
public class ImmutableProxy {

    /**
     * Creates an 'immutable' proxy wrapper and returns it.
     *
     * @param obj the reference to the object to be wrapped by immutable proxy
     * @param <T> the future reference type of proxy which is creating
     * @return the reference to immutable proxy wrapper around object obj
     */
    public static <T> T getImmutableProxy(Object obj) {
        Class clazz = obj.getClass();
        Class[] interfaces = clazz.getInterfaces();

        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                /* in case of setter method - throw an exception */
                if (method.getName().startsWith("set")) {
                    throw new UnsupportedOperationException("Object is immutable!");
                }

                /* return wrapped object method's result in other cases */
                return method.invoke(obj, args);
            }
        };
        @SuppressWarnings("unchecked")
        T immutableObject = (T) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, invocationHandler);
        return immutableObject;

    }
}
