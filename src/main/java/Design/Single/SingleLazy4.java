package Design.Single;

public class SingleLazy4 {
	
	//加入volatile 增加可见性  避免指令重排
	private	static volatile SingleLazy4 instance;
	
	private SingleLazy4(){}
	
	//双重校验
	public static SingleLazy4 getinstance() {
		if (instance == null) {
			synchronized (SingleLazy4.class) {
				if (instance == null) {
					instance = new SingleLazy4();
				}
			}
		}
		
		return instance;
	}
}
