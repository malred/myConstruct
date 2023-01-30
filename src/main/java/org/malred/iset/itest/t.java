package org.malred.iset.itest;


import org.malred.iset.entitys.User;
import org.malred.iset.entitys.tStu;
import org.malred.iset.utils.iConstruct;
import org.malred.iset.utils.iset;

import java.util.Map;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class t {
    @iset(setNames = {"name", "age", "email", "id"}, setDatas = {"李四", "18", "lisi@qq.com", "200"})
    private User user;
    @iset(setNames = {"name", "age"}, setDatas = {"张三", "56"})
    tStu stu;
    @iset(setNames = {"name", "age"}, setDatas = {"王五", "8"})
    tStu stu1;
    @iset(setName = "age" , setData = "8" )
    tStu stu2;

    public void t1() throws Exception {
        Map iuser = iConstruct.
                generateDogs("org/malred/iset/itest/t.java");
        System.out.println(iuser);
    }

    public static void main(String[] args) throws Exception {
        new iConstruct(t.class.getName());
        Map map = iConstruct.getDogs();
        System.out.println(map.get("user"));
        System.out.println(map.get("stu"));
        System.out.println(map.get("stu1"));
        System.out.println(map.get("stu2"));
    }
}
