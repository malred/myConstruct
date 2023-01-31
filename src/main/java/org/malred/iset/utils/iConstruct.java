package org.malred.iset.utils;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.malred.iset.Annotations.iset;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class iConstruct {
    final static Logger logger = LoggerFactory.getLogger(iConstruct.class);
    /**
     * a map to save all class (保存所有创建的类)
     */
    private static Map<Object, Object> dogs;

    /**
     * 获取所有创建好的类
     */
    public static Map<Object, Object> getDogs() {
        return dogs;
    }

    /**
     * 传入当前类的路径,创建新的实例
     */
    public static Map<Object, Object> generateDogs(String targetClass) throws Exception {
        logger.warn("\t\t==========>>> iset运行开始 <<<==========");
        //路径改为全类名
        targetClass = iUtils.getClassName(targetClass);
        //寻找并加载要创建的类
        Class clazz = Class.forName(targetClass);
        //准备返回的类
        Map<Object, Object> u = new HashMap<>();
        //获取当前类所有的全类名
        String[] classes = new String[clazz.getDeclaredFields().length];
        for (int i = 0; i < clazz.getDeclaredFields().length; i++) {
            classes[i] = clazz.getDeclaredFields()[i].toString();
            // 会带有当前类和全类名+空格+作为属性的类的全类名
            // private org.malred.iset.entitys.User org.malred.iset.itest.t.user
            logger.warn("获取当前类所有的全类名 => " + classes[i]);
        }
        // 获取类实例的字段名称 ( org.malred.iset.itest.t.user => user )
        for (int i = 0; i < classes.length; i++) {
            logger.warn("解析类实例字段名 => " + classes[i].toString());
            classes[i] = classes[i].substring(
                    classes[i].toString().lastIndexOf(" ") + 1, classes[i].length());
            classes[i] = classes[i].substring(
                    classes[i].toString().lastIndexOf(".") + 1, classes[i].length());
            logger.warn("解析结果 => " + classes[i]);
        }
        // 根据解析完成的类实例字段名,获取类实例需要的字段,然后赋值
        for (int i = 0; i < classes.length; i++) {
            logger.warn("为 => { " + classes[i] + " } 类实例字段,注入属性值开始");
            Field obj = clazz.getDeclaredField(classes[i]); // 获取所有字段
            // isAnnotationPresent(iset.class));//判断有没有加注解
            if (obj.isAnnotationPresent(iset.class)) {
                iset is = obj.getAnnotation(iset.class); // 获取注解
                Class<?> type = clazz.getDeclaredField(classes[i]).getType();//获取类
                Object iu = type.getConstructor().newInstance();//创建实例
                Field[] fields = iu.getClass().getDeclaredFields();//获取属性名
                String[] iargs = new String[fields.length]; // 属性名数组
                // 解析属性名(全类名 -> 字段名)
                for (int j = 0; j < fields.length; j++) {
                    iargs[j] = fields[j].toString().substring(
                            // 属性会带全类名,比如org.malred.iset.itest.t.user
                            fields[j].toString().lastIndexOf(".") + 1,
                            fields[j].toString().length());
                    logger.warn("获取类属性名称 => " + iargs[j].toString());
                }
                // 如果是注入单个属性
                if (is.setName() != null && is.setData() != null &&
                        is.setNames().length <= 0 && is.setDatas().length <= 0) {
                    iUtils.setOneValue(is, fields, iargs, iu);
                } else { // 注入多个属性
                    iUtils.setAllValue(is, fields, iargs, iu);
                }
                logger.warn("为 => { " + classes[i] + " } 类实例字段,注入属性值完毕");
                logger.warn("将 { " + classes[i] + " } 放入map中");
                // 放入dogs集合
                u.put(classes[i], iu);
            }
        }
        logger.warn("\t\t==========>>> iset运行结束 <<<==========");
        return u;
    }

    /**
     * 生成类,并获取所有创建好的类(map)
     *
     * @param nowClass 当前类的路径或全类名
     * @return
     * @throws Exception
     */
    public static Map<Object, Object> getDogs(String nowClass) throws Exception {
        dogs = generateDogs(nowClass);
        return dogs;
    }

    /**
     * 通过id(字段名)获取类实例
     *
     * @param className
     * @return
     * @throws Exception
     */
    public static Object getDogByName(String className) throws Exception {
        return dogs.get(className);
    }

    //just new class , than U can get a map named dogs which have all class you need
    public iConstruct(String nowClass) throws Exception {
        getDogs(nowClass);
    }

}
