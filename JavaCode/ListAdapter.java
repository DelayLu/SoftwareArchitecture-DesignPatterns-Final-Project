
//Adapter pattern

public class ListAdapter extends JavaArrayList implements List {

	public int count() {
		return list.size();
	}

	public ListComponent getAt(int index) {
		return list.get(index);
	}

	public ListComponent first() {
		return list.get(0);
	}

	public ListComponent last() {
		return list.get(list.size() - 1);
	}

	public boolean include(ListComponent obj) {
		boolean result = false;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == obj) {
				result = true;
			} else {
				result = false;
			}
		}

		return result;
	}

	public void append(ListComponent obj) {
		list.add(obj);
	}

	public void add(int index, ListComponent listComponent) {
		list.add(index, listComponent);
	}

	public void remove(int index) {
		list.remove(index);
	}

	public void prepend(ListComponent obj) {
		list.add(0, obj);
	}

	public void delete(ListComponent obj) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == obj) {
				list.remove(i);
			}
		}
	}

	public void deleteFirst() {
		list.remove(0);
	}

	public void deleteAll() {
		list.removeAll(list);
	}

	public void replace(int index, ListComponent x) {
		list.set(index, x);
	}

	public ListAdapter clone(ListAdapter la) {
		list.addAll(la.list);
		return this;
	}

}
