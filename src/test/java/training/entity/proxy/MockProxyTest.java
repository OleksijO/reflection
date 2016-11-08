package training.entity.proxy;

import org.junit.Assert;
import org.junit.Test;
import training.proxy.MockProxy;

import java.util.Map;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public class MockProxyTest {

    @Test
    public void getImmutableProxyTest() {
        TestInterface mockTestObj = MockProxy.getMockProxy(TestInterfaceImpl.class);
        mockTestObj.setField(5);
        mockTestObj.toString();
        mockTestObj.getField();
        mockTestObj.getField();
        mockTestObj.getField();
        Map<String, Integer> methodCallCounters = MockProxy.getMethodCallCounters(mockTestObj);
        Assert.assertEquals(new Integer(1), methodCallCounters.get("setField"));
        Assert.assertEquals(new Integer(1), methodCallCounters.get("toString"));
        Assert.assertEquals(null, methodCallCounters.get("getClass"));
        Assert.assertEquals(new Integer(3), methodCallCounters.get("getField"));
        Assert.assertEquals(5, MockProxy.getMethodCallHistory(mockTestObj).size());
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
