package training.proxy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public class ImmutableProxyTest {

    @Test (expected = UnsupportedOperationException.class)
    public void getImmutableProxyTest() {
        TestInterfaceImpl testObj = new TestInterfaceImpl();
        assertEquals(-1, testObj.getField());
        testObj.setField(1);
        assertEquals(1, testObj.getField());
        TestInterface immutable = ImmutableProxy.getImmutableProxy(testObj);
        assertTrue(immutable instanceof TestInterface);
        assertEquals(1, immutable.getField());
        testObj.setField(2);
        assertEquals(2, immutable.getField());
        immutable.setField(-1);
    }


    public interface TestInterface {
        int getField();
        void setField(int i);
    }

    public class TestInterfaceImpl implements TestInterface {
        private int field = -1;

        @Override
        public int getField() {
            return field;
        }

        @Override
        public void setField(int field) {
            this.field = field;
        }
    }


}
