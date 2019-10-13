package Design.Single;

public class SingleLazy3 {
	
	//同步的
	private	static SingleLazy3 instance;
	
	private SingleLazy3(){}
	
	public static synchronized SingleLazy3 getinstance() {
		if (instance == null) {
			instance = new SingleLazy3();
		}
		
		return instance;
	}
}
