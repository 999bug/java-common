package com.ncst.junit.mockito.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.when;

/**
 * @author Lsy
 * @date 2022/4/11
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ExampleService.class)
public class PowerMockTest {


    @Test
    public void test() {

        PowerMockito.mockStatic(ExampleService.class);  // 这也是必须的

        when(ExampleService.add(1, 2)).thenReturn(100);
        // success
        Assert.assertEquals(100, ExampleService.add(1, 2));
        // fail
        Assert.assertEquals(0, ExampleService.add(4, 2));
        // true
        Assert.assertEquals(0, ExampleService.add(3, 2));

    }
}
