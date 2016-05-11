

//Composite pattern, Component class

public abstract class ListComponent {

	public abstract void printOrigin();

	public abstract void printValue();

	public void addChild(ListComponent child, int index) {

	}

	public void remove(int index) {
	}

	public int numberOfChildren() {
		return 0;
	}

	public ListComponent getChild(int index) {
		return null;
	}

	public abstract ListComponent getComponent();

	public abstract ListComponentIterator createIterator();

	public ListComponentIterator createPreorderIterator(ListComponent list) {
		return new PreorderIterator(list);
	}

	public abstract void setState(Memento state);

	public abstract Memento createMemento();
	
	public abstract String getValue();

}
