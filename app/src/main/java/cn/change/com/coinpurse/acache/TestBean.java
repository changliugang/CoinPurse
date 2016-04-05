package cn.change.com.coinpurse.acache;

/**
 * 测试实体
 * Created by chang on 2016/4/5.
 */
public class TestBean {

    private String name;
    private int age;

    public TestBean() {
    }

    public TestBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
