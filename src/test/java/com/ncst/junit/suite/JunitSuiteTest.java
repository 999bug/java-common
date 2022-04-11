package com.ncst.junit.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Lsy
 * @date 2022/4/11
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        Test1.class,
        Test2.class
})
public class JunitSuiteTest {
}
