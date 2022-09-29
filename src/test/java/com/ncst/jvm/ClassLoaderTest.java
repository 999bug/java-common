package com.ncst.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * @author Lsy
 * @date 2022/4/25
 */
@Slf4j
public class ClassLoaderTest {

    @Test
    public void test() {
        final URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }
        printClassLoader("扩展类加载器", ClassLoaderTest.class.getClassLoader().getParent());
        printClassLoader("应用类加载器", ClassLoaderTest.class.getClassLoader());

    }

    private void printClassLoader(String name, ClassLoader cl) {
        if (cl != null) {
           log.info("{} ClassLoader -> {}", name, cl);
            printUrlForClassLoader(cl);
        }
    }

    private void printUrlForClassLoader(ClassLoader cl) {
        Object ucp = insightField(cl, "ucp");
        Object path = insightField(ucp, "path");
        List lists = (List) path;
        for (Object list : lists) {
            System.out.println("-->"+list);
        }
    }

    private Object insightField(Object obj, String fName) {
        try {
            Field f;
            if (obj instanceof URLClassLoader) {
                f = URLClassLoader.class.getDeclaredField(fName);
            } else {
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
