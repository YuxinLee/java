package base.jvm_learning;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class For2_Type {
    public static void main(String[] args) {
    }

    @Test
    public void test1(){
        //第一种: 在for循环外创建对象
        List<User> userList = new ArrayList<>();
        User user = new User();
        for (int i = 0; i < 3; i++) {
            user.setID(i);
            user.setName("he"+i);
            userList.add(user);
        }
        for(User user1 : userList) {
            System.out.println(user1);
        }
    }
    @Test
    public void test2(){
        //第二种: 在for循环里创建对象
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setID(i);
            user.setName("he"+i);
            userList.add(user);
        }
        for(User user1 : userList) {
            System.out.println(user1);
        }
    }
    @Test
    public void test3(){
        //第三种: 在for循环外声明引用，里面创建对象
        User user = null;
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            user = new User();
            user.setID(i);
            user.setName("he"+i);
            userList.add(user);
        }
        for(User user1 : userList) {
            System.out.println(user1);
        }
    }
}

class User{
    private int ID;
    private String name;

    public User() {
    }

    public User(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
