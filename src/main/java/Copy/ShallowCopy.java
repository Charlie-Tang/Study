package Copy;

//测试浅拷贝
public class ShallowCopy {
	
	//一个是引用传递类型；另一个是字符串类型（属于常量）。
	public static void main(String[] args) {
		
		Age age = new Age();
		age.setAge(20);
		Person p1 = new Person("小红", age);
		Person p2 = new Person(p1);
		
		System.out.println(p1);
		System.out.println(p2);
		
		p1.setName("aaa");
		age.setAge(99);
		
		System.out.println(p1);
		System.out.println(p2);
		
//		Person [name=小红, age=20]
//		Person [name=小红, age=20]
//		Person [name=aaa, age=99]
//		Person [name=小红, age=99]
	}
}
class Person {
	
	private String name;
	private Age age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Age getAge() {
		return age;
	}

	public void setAge(Age age) {
		this.age = age;
	}

	public Person(String name, Age age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	//拷贝方法
	public Person(Person p) {
        this.name=p.name;
        this.age=p.age;
    }

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
}

