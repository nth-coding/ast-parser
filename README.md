# ast-parser
Using JDT and try to bind method 

# How to use

You need to init `nth_coding.ASTParserTest` with the file name and filepath (path from content root).
- use `getOutputToFile(String filename)` to get the output to file.
- use `getOutputToString()` to get the output as String.

# Output

For example, we try it with these .java file
<h4 a><strong><code>Apple.java</code></strong></h4>

```
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
```

<h4 a><strong><code>Fruit.java</code></strong></h4>

```
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
```

And the result: 

```
Method: public void println(java.lang.String) 
-> From class: PrintStream
Method: public sealed void printName(java.lang.String) 
-> From class: Apple
Method: public void test() 
-> From class: Apple
Method: public void test() 
-> From class: Apple
Method: public void test2() 
-> From class: Apple
Method: public sealed void printName(java.lang.String) 
-> From class: Apple
Method: public void sayHello() 
-> From class: Fruit
Method: public void println(java.lang.String) 
-> From class: PrintStream
```

