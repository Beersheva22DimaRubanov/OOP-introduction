package telran.util;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	protected Set<Entry<K, V>> set;

	@Override
	public V put(K key, V value) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
			entry.setValue(value);
		} else {
			set.add(new Entry<>(key, value));
		}
		return res;
	}

		@Override
	public V get(K key) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
		}
		return res;
	}

	@Override
	public boolean containsKey(K key) {
		return get(key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		return set.stream().anyMatch(x -> x.getValue().equals(value));
	}

	@Override
	public Collection<V> values() {
		List<V> list = new ArrayList<V>();
		set.stream().forEach(x -> list.add(x.getValue()));
		return list;
	}

	@Override
	public Set<K> keySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<K> res = set.getClass().getConstructor().newInstance();
			set.stream().forEach(x -> res.add(x.getKey()));
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<Entry<K, V>> res = set.getClass().getConstructor().newInstance();
			set.stream().forEach(res::add);
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public V remove(K key) {
		V res = get(key);
		if (res != null) {
			set.remove(new Entry<>(key, null));
		}
		return res;
	}
}
