package bean_utils.a_beans;

import java.util.Date;

/**
 * 实体类设计
 *
 */
public class Admin {
    private int id;
    private String userName;
    private String pwd;
    private int age;
    private Date birth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String toString() {
        return "Admin [age=" + age + ", birth=" + birth + ", id=" + id
                + ", pwd=" + pwd + ", userName=" + userName + "]";
    }
}
