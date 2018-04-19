import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class OccurrenceSet<T> implements Set<T>{
	public HashMap<T, Integer> map = new HashMap<T, Integer>();
	public Comparator<T> comp = new Comparator<T>(){
		@Override
		public int compare(T o1, T o2){
			return map.get(o1) - map.get(o2);
		}
	};


	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return map.containsKey(o);
	}

	@Override
	public Iterator<T> iterator() {
		ArrayList<T> list = new ArrayList<T>(map.keySet());
		Collections.sort(list, comp);
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		ArrayList<T> list = new ArrayList<T>(map.keySet());
		Collections.sort(list, new Comparator<T>(){
			@Override
			public int compare(T o1, T o2){
				return map.get(o1) - map.get(o2);
			}
		});
		return list.toArray();

	}


	@Override
	public <T> T[] toArray(T[] a) {
		ArrayList<T> list = new ArrayList<T>((Collection<? extends T>) map.keySet());
		Collections.sort(list, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2){
				return map.get(o1) - map.get(o2);
			}
		});
		return list.toArray(a);
	}


	@Override
	public boolean add(T e) {
		if (map.containsKey(e)){
			map.put(e, map.get(e)+1);
			return false;
		}
		else{
			map.put(e, 1);
			return true;

		}
	}

	@Override
	public boolean remove(Object o) {
		if(map.containsKey(o)){
			map.remove(o);
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o: c){
			if (this.contains(o)){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		ArrayList<T> list = new ArrayList<>(c);
		for (int i = 0; i < list.size(); i++){
			this.add(list.get(i));
			return true;
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean bool = false;
		for (T i: this){
			if(! c.contains(i)){
				bool = this.remove(i);
			}
		}
		return bool;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		ArrayList<?> list = new ArrayList<>(c);
		for(Object o: list){
			this.remove(o);
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		map.clear();
	}
	public String toString(){
		System.out.print("set: [");
		Object[] ob = this.toArray();
		String str = new String();
		int i = 0;
		for (; i<this.size() - 1; i++){
			str += ob[i] + ", ";
		}
		str += ob[i] + "]";
		return str;
	}
}

