package Design.Single;

public class SingleLazy2 {
	
	//懒汉式 私有化构造方法
	private	static SingleLazy2 instance;
	
	private SingleLazy2(){}
	
	public static SingleLazy2 getinstance() {
		if (instance == null) {
			instance = new SingleLazy2();
		}
		
		return instance;
	}
}
