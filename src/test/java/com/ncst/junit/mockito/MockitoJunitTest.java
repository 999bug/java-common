package com.ncst.junit.mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Lsy
 * @date 2022/4/11
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoJunitTest {

    @Mock
    private Random random;

    @Mock
    private List<String> lists;

    @Test
    public void testParameter() {
        Mockito.when(lists.get(0)).thenReturn("cc");
        Assert.assertEquals("cc", lists.get(0));

        Mockito.when(lists.get(0)).thenReturn("aa");
        Assert.assertEquals("aa", lists.get(0));

        // ArgumentMatchers.anyInt() 任意 int 值，
        // any()：任意对象
        // anyList() anyMap() anySet()
        Mockito.when(lists.get(ArgumentMatchers.anyInt())).thenReturn("bb");
        Assert.assertEquals("bb", lists.get(9999));

    }

    @Test
    public void test() {
        Mockito.when(random.nextInt()).thenReturn(122);
        Assert.assertEquals(122, random.nextInt());
    }

}
