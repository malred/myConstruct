package utils;

import entitys.User;
import entitys.tStu;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class iConstruct {
    //a map to save all class
    private static Map<Object,Object> dogs;
    //传入当前类的路径,创建新的user实例
    public static Map<Object, Object> iuser(String targetClass) throws Exception {
        //路径改为全类名
        targetClass = iUtils.getClassName(targetClass);
        Class clazz = Class.forName(targetClass);
        Map<Object,Object> u = new HashMap<>(); //准备返回的类

//            System.out.println(clazz.getDeclaredFields()[1].toString());
            //获取当前类所有的全类名
            String[] classes = new String[clazz.getDeclaredFields().length];
            for (int i = 0; i < clazz.getDeclaredFields().length; i++) {
//                System.out.println(clazz.getDeclaredFields()[i].toString());
                classes[i] = clazz.getDeclaredFields()[i].toString();
            }
            for (int i = 0; i < classes.length; i++) {
                classes[i] = classes[i].substring(classes[i].toString().lastIndexOf(" ") + 1, classes[i].length());
//                System.out.println(classes[i].toString());//com.example.mybatispluslearn01.utils.t.user
                classes[i] = classes[i].substring(classes[i].toString().lastIndexOf(".") + 1, classes[i].length());
//                System.out.println(classes[i].toString());
            }
            for (int i = 0; i < classes.length; i++) {
            Field user = clazz.getDeclaredField(classes[i]);
//            System.out.println(user.isAnnotationPresent(iset.class));//判断有没有加注解
            if (user.isAnnotationPresent(iset.class)) {
                iset is = user.getAnnotation(iset.class);
                Class<?> type = clazz.getDeclaredField(classes[i]).getType();//获取类
                Object iu = type.getConstructor().newInstance();//创建实例
                Field[] fields = iu.getClass().getDeclaredFields();//获取属性
                String[] iargs = new String[fields.length];
                for (int j = 0; j < fields.length; j++) {
                    iargs[j] = fields[j].toString().
                            substring(fields[j].toString().lastIndexOf(".") + 1, fields[j].toString().length());
                }
                System.out.println("当前装填的类属性有:");
                for (int j = 0; j < iargs.length; j++) {
                    System.out.print(iargs[j].toString() + "-=-");
                }
                System.out.println();
//                System.out.println("当前装填的类属性有:"+iargs.toString());
//                System.out.println("当前注解将装填的类属性有:"+is.setName());
//                System.out.println("当前注解将装填的类属性的值有:"+is.setData());
                for (int k = 0; k < fields.length; k++) {
                    if (is.setName().equals(iargs[k])) {
                        System.out.println("设置参数" + iargs[k] + "中");
                        Field declaredField = iu.getClass().getDeclaredField(iargs[k]);
                        declaredField.setAccessible(true);
                        declaredField.set(iu, is.setData());
                    }
                }
                for (int l = 0; l < fields.length; l++) {
                    for (int j = 0; j < is.setNames().length; j++) {
                        if (is.setNames()[j].toString().equals(iargs[l])) {
                            System.out.println("设置参数" + iargs[l] + "中");
                            Field declaredField = iu.getClass().getDeclaredField(iargs[l]);
                            declaredField.setAccessible(true);
                            if (iUtils.isNumber(is.setDatas()[j].toString())) {
                                declaredField.set(iu, Integer.parseInt(is.setDatas()[j].toString()));
                            } else {
                                declaredField.set(iu, is.setDatas()[j].toString());
                            }
                        }
                    }
                }
                u.put(classes[i],iu);
            }
        }

        return u;
    }
    public static Map<Object, Object> getDogs(String nowClass)throws Exception{
        dogs = iuser(nowClass);
        return dogs;
    }
    //get class by id
    public static Object getDogByName(String className)throws Exception{
        return dogs.get(className);
    }
    //just new class , than U can get a map named dogs which have all class you need
    public iConstruct(String nowClass) throws Exception{
        getDogs(nowClass);
    }

}
