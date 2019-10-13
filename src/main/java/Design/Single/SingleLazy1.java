package Design.Single;

public class SingleLazy1 {
	
	//懒汉式
	private	static SingleLazy1 instance;
	
	public static SingleLazy1 getinstance() {
		if (instance == null) {
			instance = new SingleLazy1();
		}
		
		return instance;
	}
}
