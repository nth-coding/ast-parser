package nth_coding.testcase;

import nth_coding.testcase.fruit.Fruit;

public class Apple extends Fruit {
    public void test() {
        System.out.println("hello");
    }
    public void test2() {
        Apple apple2 = new Apple();
        apple2.name = "Apple2";

        apple2.printName("hello2");
        apple2.test();
    }

    public static void main(String[] args) {
        Apple apple3 = new Apple();
        apple3.name = "sang";

        apple3.test();
        apple3.test2();
        apple3.printName("hello3");
        apple3.sayHello();
    }

    @Override
    public void printName(String str) {
        System.out.println("hello im hai");
    }
}