
//Adapter pattern


public interface List {

	public int count();

	public ListComponent getAt(int index);

	public ListComponent first();

	public ListComponent last();

	public boolean include(ListComponent x);

	public void append(ListComponent x);

	public void add(int index, ListComponent listComponent);

	public void remove(int index);

	public void prepend(ListComponent x);

	public void delete(ListComponent x);

	public void deleteFirst();

	public void deleteAll();

	public void replace(int index, ListComponent x);

	public ListAdapter clone(ListAdapter listAdapter);
}
