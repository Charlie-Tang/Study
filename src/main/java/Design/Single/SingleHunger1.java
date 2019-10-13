package Design.Single;

public class SingleHunger1 {
	
	private static final SingleHunger1 instance = new SingleHunger1();
	
	private SingleHunger1(){}
	
	private static SingleHunger1 getInstance() {
		return instance;
		
	}
}
