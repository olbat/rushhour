package solver;

import java.util.HashMap;

public class NodeMap {
	private static HashMap<String, Node> map = new HashMap<String,Node>();
	
	
	public static boolean contains(String key) {
		return map.containsKey(key);
	}
	
	public static void put(String key, Node n) {	
		map.put(key,n);
	}
	
	public static Node get(String key) {
		return map.get(key);
	}
	
	public static void clean(){
		map.clear();
	}
}
