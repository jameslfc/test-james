package james.li.concurrencyinpractice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author jamli
 *
 */
public class ImprovedMap implements Map<Object, Object> {

	private Map<Object, Object> innerMap;
	
	public ImprovedMap(Map<Object, Object> map) {
		this.innerMap = new HashMap<Object, Object>(map);
	}

	@Override
	public int size() {
		return innerMap.size();
	}

	@Override
	public synchronized boolean isEmpty() {
		return innerMap.isEmpty();
	}

	@Override
	public synchronized boolean containsKey(Object key) {
		return innerMap.containsKey(key);
	}

	@Override
	public synchronized boolean containsValue(Object value) {
		return innerMap.containsValue(value);
	}

	@Override
	public synchronized Object get(Object key) {
		return innerMap.get(key);
	}

	@Override
	public synchronized Object put(Object key, Object value) {
		return innerMap.put(key, value);
	}

	@Override
	public synchronized Object remove(Object key) {
		return innerMap.remove(key);
	}

	@Override
	public synchronized void putAll(Map m) {
		innerMap.putAll(m);
	}

	@Override
	public synchronized void clear() {
		innerMap.clear();
	}

	@Override
	public synchronized Set keySet() {
		return innerMap.keySet();
	}

	@Override
	public synchronized Collection values() {
		return innerMap.values();
	}

	@Override
	public synchronized Set entrySet() {
		return innerMap.entrySet();
	}

}
