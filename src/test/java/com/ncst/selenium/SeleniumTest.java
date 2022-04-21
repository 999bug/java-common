package com.ncst.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Lsy
 * @date 2022/4/11
 */
public class SeleniumTest {
    @Test
    public void dayLogTest() throws InterruptedException {
        //配置浏览器驱动地址
        System.setProperty("webdriver.chrome.driver",
                "D:\\java\\driver\\chromedriver.exe");
        //打开Chrome浏览器
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        //打开百度网站
        webDriver.get("https://dev.info2soft.com/index.php?m=my&f=index");
        //TimeUnit.SECONDS.sleep(5);

        webDriver.findElement(By.id("account")).sendKeys("lisy1");
        webDriver.findElement(By.xpath("//*[@id=\"loginPanel\"]/div/div[2]/form/table/tbody/tr[2]/td/input")).sendKeys("977721Fs");
        webDriver.findElement(By.xpath("//*[@id=\"submit\"]")).click();

        webDriver.get("https://dev.info2soft.com/index.php?m=doc&f=create&objectType=custom&objectID=0&libID=0&t=html#app=doc");

        final WebElement element = webDriver.findElement(By.xpath("//*[@id=\"lib_chosen\"]"));
        element.click();


        Select select = new Select(element);
        select.selectByValue("5");


//        webDriver.findElement(By.xpath("//*[@id=\"module_chosen\"]/a")).sendKeys("李石岩");


    }

    @Test
    public void test() throws InterruptedException {
        //配置浏览器驱动地址
        System.setProperty("webdriver.chrome.driver",
                "D:\\java\\driver\\chromedriver.exe");
        //打开Chrome浏览器
        WebDriver webDriver = new ChromeDriver();
        TimeUnit.SECONDS.sleep(5);
        //打开百度网站
        webDriver.get("https://www.baidu.com");
        TimeUnit.SECONDS.sleep(2);
        //输入框输入搜索关键词 selenium 中文官网
        webDriver.findElement(By.id("kw")).sendKeys("selenium 中文官网");
        TimeUnit.SECONDS.sleep(2);
        //点击百度一下按钮
        webDriver.findElement(By.id("su")).submit();
        TimeUnit.SECONDS.sleep(2);
        //查询所有搜索的结果
        List<WebElement> resultElements = webDriver.findElements(By.className("result"));
        if (!resultElements.isEmpty()) {
            //找到第一条结果的第一个链接
            List<WebElement> aTagElements = resultElements.get(0).findElements(By.tagName("a"));
            if (!aTagElements.isEmpty()) {
                //新开一个窗口打开此链接
                String href = aTagElements.get(0).getAttribute("href");
                System.out.println(href);
                ((JavascriptExecutor) webDriver).executeScript(String.format("window.open('%s')", href));
            }
        }
        TimeUnit.SECONDS.sleep(10);
        //关闭浏览器
        webDriver.quit();

    }
}
