package ds;

import java.util.*;

class Person{
    String name;
    int id;
    public Person(String name,int id){
        this.name=name;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }
}
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<Person,String> map=new HashMap<>();
        Person p1=new Person("Rituraj",1);
        Person p2=new Person("Shibam",2);
        Person p3=new Person("Rituraj", 1);
        Person p4=new Person("Sanjeeb", 1);
        map.put(p1,"Software Developer");
        map.put(p2,"Software Developer");
        map.put(p3,"Researcher and Entrepreneur");
        System.out.println(map);
        System.out.println("Map Size: "+ map.size());
        System.out.println("Map Contains: Rituraj ?"+ map.containsKey(p1));
        System.out.println("Map Contains: Rituraj ?"+ map.containsKey(p3));
        System.out.println(map.remove(p4));
        System.out.println(map);
        NavigableMap<String, Integer> navMap=new TreeMap<>();
    }
}
