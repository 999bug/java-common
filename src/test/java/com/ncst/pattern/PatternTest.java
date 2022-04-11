package com.ncst.pattern;

import cn.hutool.core.util.ReUtil;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lsy
 * @date 2022/4/11
 */
public class PatternTest {
    private static final Logger logger = LoggerFactory.getLogger(PatternTest.class);

    @Test
    public void test1() {

        String content = "ZZZaaabbbccc中文1234";
        String regex = "[\\d]";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            String result= matcher.group();
            System.out.println("result = " + result);
        }

    }



    @Test
    public void test2() {
        String content = "ZZZaaabbbccc中文1234";
        String resultExtractMulti = ReUtil.extractMulti("(\\w)aa(\\w)", content, "$1-$2");
        logger.info(resultExtractMulti);
        Assert.assertEquals("Z-a", resultExtractMulti);
    }


}
