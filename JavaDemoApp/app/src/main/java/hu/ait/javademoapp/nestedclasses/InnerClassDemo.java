package hu.ait.javademoapp.nestedclasses;

public class InnerClassDemo {

    public void main(){
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
    }

}
