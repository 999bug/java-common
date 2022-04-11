package com.ncst.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * @author Lsy
 * @date 2022/4/11
 */
public class JunitTest {

    // 方法执行超过2秒，代表超时
    @Rule
    public Timeout timeout = Timeout.seconds(2);

    @Test
    public void test3S() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void test1S() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
    }
}
