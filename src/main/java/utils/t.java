package utils;


import entitys.User;
import entitys.tStu;

import java.util.HashMap;
import java.util.Map;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class t {
    @iset(setNames = {"name", "age","email","id"}, setDatas = {"李四", "18","lisi@qq.com","200"})
    User user;
    @iset(setNames = {"name", "age"}, setDatas = {"张三", "56"})
    tStu stu;
    @iset(setNames = {"name", "age"}, setDatas = {"王五", "8"})
    tStu stu1;
    public void t1()throws Exception{
        Map iuser = iConstruct.
                iuser("utils/t.java", User.class);
        System.out.println(iuser);
    }
    public static void main(String[] args) throws Exception{
        Map iuser = iConstruct.
                iuser("utils/t.java",User.class);
        System.out.println(iuser);
        User user = (User)iuser.get("user");
        tStu stu = (tStu) iuser.get("stu");
        tStu stu1 = (tStu) iuser.get("stu1");
        System.out.println(user);
        System.out.println(stu);
        System.out.println(stu1);
    }
}
