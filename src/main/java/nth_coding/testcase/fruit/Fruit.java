package nth_coding.testcase.fruit;

public abstract class Fruit {
    protected String name;
    protected boolean eatable;

//    public void printName(String str) {
//        System.out.println("name: " + this.name + str);
//    }
    public abstract void printName(String str);

    public void sayHello() {
        System.out.println("hello");
    }
}
