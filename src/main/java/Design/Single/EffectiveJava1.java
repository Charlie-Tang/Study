package Design.Single;

public class EffectiveJava1 {
	
	//使用静态构造类
	private static class SingletonHolder {

        private static final EffectiveJava1 INSTANCE = new EffectiveJava1();

    }
	
	private EffectiveJava1 (){}

    public static final EffectiveJava1 getInstance() {

        return SingletonHolder.INSTANCE;

    }
}
