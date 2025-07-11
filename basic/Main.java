package basic;

class A{
    int a;
}
class B extends A{
    int b;
}
class Main {
    public static void main(String[] args) {
        A obj=new B();
        String type= String.valueOf(obj.getClass());
        System.out.println(type);
        System.out.println(obj.getClass());

    }
}
