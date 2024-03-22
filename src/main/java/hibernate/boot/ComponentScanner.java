package hibernate.boot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComponentScanner {

    public static List<Class<?>> scan(String basePackage) {
        List<Class<?>> classes = new ArrayList<>();
        String path = basePackage.replace(".", "/");
        File baseDir = new File(Thread.currentThread().getContextClassLoader().getResource(path).getFile());

        if (baseDir.exists() && baseDir.isDirectory()) {
            for (File file : baseDir.listFiles()) {
                if (file.isDirectory()) {
                    classes.addAll(scan(basePackage + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    String className = basePackage + "." + file.getName().substring(0, file.getName().length() - 6);
                    try {
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return classes;
    }
}