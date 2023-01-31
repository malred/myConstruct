package org.malred.iset.itest;

/**
 * 测试在类外面访问创建的类
 *
 * @author malguy-wang sir
 * @create ---
 */
public class t1 {
    static t t = new t();
    public static void main(String[] args) throws Exception {
        t.t1();
        System.out.println(t); // 全部为null -> 需要自动扫描并创建
    }
}
