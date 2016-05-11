

//Composite pattern, Item class

public class Item extends ListComponent {

	private char value;
	
	public void printValue() {
		System.out.print(value+"");
	}

	public void printOrigin() {
		System.out.print(value+"");
	}

	public Item(char character) {
		this.value = character;
	}

	public ListComponent getComponent() {
		return this;
	}

	public ListComponentIterator createIterator() {
		return null;
	}

	public void setPairKey(int pairKey) {

	}

	@Override
	public void setState(Memento state) {
		// TODO Auto-generated method stub

	}

	@Override
	public Memento createMemento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		return value + "";
	}
	
}
