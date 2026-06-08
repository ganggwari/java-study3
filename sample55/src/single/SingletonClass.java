package single;

public class SingletonClass {
	private static SingletonClass sc = null;
	public String name;
	
	private SingletonClass() {
	}
	
	public static SingletonClass getInstance() {
		if(sc==null) {
			sc = new SingletonClass(); // 여기서 한 번만 생성
		}
		// 이미 있으면 생성 안 하고 이미 있는 거 호출
		return sc;
	}
}
