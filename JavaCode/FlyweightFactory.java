import java.util.HashMap;

//Flyweight pattern.

public class FlyweightFactory {

	private HashMap<Integer, Item> characterPool;

	public Item getFlyweight(int key) {
		if (characterPool.get(key) == null) {
			characterPool.put(key, new Item((char) key));
		}

		return characterPool.get(key);
	}

	public FlyweightFactory() {
		characterPool = new HashMap<Integer, Item>();
	}

}
