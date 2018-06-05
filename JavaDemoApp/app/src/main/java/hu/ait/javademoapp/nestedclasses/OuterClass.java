package hu.ait.javademoapp.nestedclasses;

public class OuterClass {

    private int a;

    public void foo() {
        new InnerClass().bar();
    }

    public class InnerClass {
        public static final int SOME_CONSTANT = 1;
        private int b;

        public void bar() {
            a = 10;
            b = 20;
        }
    }
}

