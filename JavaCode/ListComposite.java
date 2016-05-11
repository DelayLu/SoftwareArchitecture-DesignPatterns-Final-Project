
//Composite pattern, Composite class

public class ListComposite extends ListComponent {

	private ListAdapter childrenList = new ListAdapter();
	
	public static boolean firstLine = true;

	public void printValue() {
		//System.out.print("\n");
		for (int i = 0; i < childrenList.count(); i++) {
			childrenList.getAt(i).printValue();
		}

	}

	public void printOrigin() {

	}

	public ListComponent getComponent() {
		return this;
	}

	public void addChild(ListComponent child, int index) {
		childrenList.add(index, child);
	}

	public ListComponent getChild(int index) {

		return childrenList.getAt(index);

	}

	public void remove(int index) {
		childrenList.remove(index);
	}

	public int numberOfChildren() {

		return childrenList.count();

	}

	public ListComponentIterator createIterator() {

		return new JavaListIterator(childrenList);

	}


	public void setState(Memento state) {
		this.childrenList = state.getState();

	}

	public Memento createMemento() {
		ListAdapter newList = new ListAdapter();
		newList.clone(childrenList);
		return new Memento(newList);
	}

	@Override
	public String getValue() {
		String result = "";
		if(firstLine){
			result = "";
			firstLine = false;
		}
		else{
			result = "\n";
		}
		
		return result;
	}

}
