

//Command pattern

public class Memento {

	private ListAdapter list = new ListAdapter();

	public ListAdapter getState() {
		
		return list;
		
	}


	public Memento(ListAdapter list) {
		
		this.list = list;
		
	}
}