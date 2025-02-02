package com.zhr.webselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author ZHR
 * @version 1.0
 * @ClassName LoginTest
 * @Date 2019-10-22 17:48
 * @description 自动登陆百度云盘
 **/
public class LoginTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        // 与浏览器同步非常重要，必须等待浏览器加载完毕
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://pan.baidu.com/");
        // 点击页面的账号密码登陆,跳到使用账号密码登陆的页面
        WebElement qqLoginLink = driver
                .findElement(By.xpath("/html/body/div[1]/div[3]/div[6]/div/div[3]/p[2]"));
        qqLoginLink.click();
        Thread.sleep(1000);

        driver.findElement(By.name("userName")).sendKeys("15");
        driver.findElement(By.name("password")).sendKeys("zha");
        driver.findElement(By.id("TANGRAM__PSP_4__submit")).click();
    }
}
