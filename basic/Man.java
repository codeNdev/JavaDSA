package basic;

public enum Man {
    Shibam(1,24),Sambit(2,24),Rituraj(3,23);
    int id;
    int age;
    private Man(int id,int age){
        this.id=id;
        this.age=age;
    }
    int getId(){
        return id;
    }
    int getAge(){
        return age;
    }
}
