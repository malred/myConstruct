package org.malred.iset.utils;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.malred.iset.Annotations.iset;

import java.lang.reflect.Field;

/**
 * 工具类
 *
 * @author malguy-wang sir
 * @create ---
 */
public class iUtils {
    final static Logger logger = LoggerFactory.getLogger(iConstruct.class);

    //判断字符串是不是数字
    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //传入来自源根的路径,返回全类名
    public static String getClassName(String path) {
        path = path.replace("/", ".");
        path = path.replace("\\", ".");
        path = path.replace(".java", "");
        return path;
    }

    /**
     * 为类实例注入单个字段
     *
     * @param is     注解
     * @param fields 字段数组
     * @param iargs  字段名数组
     * @param iu     类实例
     * @throws Exception
     */
    public static void setOneValue(iset is, Field[] fields, String[] iargs, Object iu) throws Exception {
        // 注入单个属性时
        for (int k = 0; k < fields.length; k++) {
            if (is.setName().equals(iargs[k])) {
                logger.warn("设置参数 { " + iargs[k] + " } 为 { " + is.setData() + " } 中");
                // 根据字段名称获取字段
                Field declaredField = iu.getClass().getDeclaredField(iargs[k]);
                // 设置可访问(私有字段也能被访问)
                declaredField.setAccessible(true);
                // 传入字段和属性,赋值
                if (iUtils.isNumber(is.setData().toString())) {
                    // 如果是数值类型需要转int
                    declaredField.set(iu, Integer.parseInt(is.setData().toString()));
                } else {
                    declaredField.set(iu, is.setData().toString());
                }
                logger.warn("设置参数 => " + iargs[k] + " 完毕");
            }
        }
    }

    /**
     * 为类实例注入多个字段
     *
     * @param is     注解
     * @param fields 字段数组
     * @param iargs  字段名数组
     * @param iu     类实例
     * @throws Exception
     */
    public static void setAllValue(iset is, Field[] fields, String[] iargs, Object iu) throws Exception {
        for (int l = 0; l < fields.length; l++) {
            for (int j = 0; j < is.setNames().length; j++) {
                if (is.setNames()[j].toString().equals(iargs[l])) {
                    logger.warn("设置参数 { " + iargs[l] + " } 为 { "
                            + is.setDatas()[j].toString() + " } 中");
                    // iu是创建了的实例,根据字段名获取字段
                    Field declaredField = iu.getClass().getDeclaredField(iargs[l]);
                    declaredField.setAccessible(true);
                    // 如果是数值类型需要转int
                    if (iUtils.isNumber(is.setDatas()[j].toString())) {
                        declaredField.set(iu, Integer.parseInt(is.setDatas()[j].toString()));
                    } else {
                        declaredField.set(iu, is.setDatas()[j].toString());
                    }
                    logger.warn("设置参数 => " + iargs[l] + " 完毕");
                }
            }
        }
    }
}
