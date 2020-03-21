package com.nssliu.dataserver.trueversion.util;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/21 17:14
 * @describe:
 */
import com.nssliu.dataserver.utils.PropertiesUtils.PropertiesUtil;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
  * 根据properties中配置的路径把jar和配置文件加载到classpath中。
  * @author jnbzwm
  *
  */
public final class ExtClasspathLoader {
    /**
     * URLClassLoader的addURL方法
     */
    private static Method addURL = initAddMethod();

    private static URLClassLoader classloader = (URLClassLoader) ClassLoader.getSystemClassLoader();

    /**
     * 初始化addUrl 方法.
     *
     * @return 可访问addUrl方法的Method对象
     */
    private static Method initAddMethod() {
        try {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            add.setAccessible(true);
            return add;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载jar classpath。
     */
    public static void loadClasspath() {
        List<String> files = getJarFiles();
        for (String f : files) {
            loadClasspath(f);
        }

        List<String> resFiles = getResFiles();
        if(resFiles!=null){

            for (String r : resFiles) {
                loadResourceDir(r);
            }
        }
    }

    private static void loadClasspath(String filepath) {
        File file = new File(filepath);
        loopFiles(file);
    }

    private static void loadResourceDir(String filepath) {
        File file = new File(filepath);
        loopDirs(file);
    }

    /**
     * 循环遍历目录，找出所有的资源路径。
     *
     * @param file 当前遍历文件
     */
    private static void loopDirs(File file) {
        // 资源文件只加载路径
        if (file.isDirectory()) {
            addURL(file);
            File[] tmps = file.listFiles();
            for (File tmp : tmps) {
                loopDirs(tmp);
            }
        }
    }

    /**
     * 循环遍历目录，找出所有的jar包。
     *
     * @param file 当前遍历文件
     */
    private static void loopFiles(File file) {
        if (file.isDirectory()) {
            File[] tmps = file.listFiles();
            for (File tmp : tmps) {
                loopFiles(tmp);
            }
        } else {
            if (file.getAbsolutePath().endsWith(".jar") || file.getAbsolutePath().endsWith(".zip")) {
                addURL(file);
            }
        }
    }

    /**
     * 通过filepath加载文件到classpath。
     *
     * @param file 文件路径
     * @return URL
     * @throws Exception 异常
     */
    private static void addURL(File file) {
        try {
            addURL.invoke(classloader, new Object[]{file.toURI().toURL()});
        } catch (Exception e) {
        }
    }

    /**
     * 从配置文件中得到配置的需要加载到classpath里的路径集合。
     *
     * @return
     */
    private static List<String> getJarFiles() {
        // TODO 从properties文件中读取配置信息略
        //String[] strings = {"E:\\supermap\\work\\2020\\cc\\0createEntity2\\jars\\cc.jar"};
        List<String> jarFiles = new ArrayList<>();
        String jarRoot = PropertiesUtil.getProperties_1("jarRoot");
        File file = new File(jarRoot);
        if(file.exists()){
            String[] list = file.list();
            for (String jarFile:list)
                jarFiles.add(file.getPath()+File.separator+jarFile);
        }
        return jarFiles;
    }

    /**
     * 从配置文件中得到配置的需要加载classpath里的资源路径集合
     *
     * @return
     */
    private static List<String> getResFiles() {
        //TODO 从properties文件中读取配置信息略
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ExtClasspathLoader.loadClasspath();
        Class<?> objects = Thread.currentThread().getContextClassLoader().loadClass("AqiData");
        Class isExistDoc =  Thread.currentThread().getContextClassLoader().loadClass("IsExistDoc");
        //objects = AqiData.class;
        Field[] declaredFields = objects.getDeclaredFields();
       for(Field field:declaredFields){
           Annotation annotation = field.getAnnotation(isExistDoc);
           if(annotation!=null){
               Class<? extends Annotation> aClass = annotation.annotationType();

               System.out.println(aClass);
               System.out.println(annotation);
           }
        }
        //System.out.println(group);
    }
}
