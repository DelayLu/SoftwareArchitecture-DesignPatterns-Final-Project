

//Decorate pattern, to decorate a character with tags.

public abstract class ListDecorator extends ListComponent {

	private ListComponent listComponent;

	protected int row;
	protected int index;

	public void printValue() {
		listComponent.printValue();
	}

	public void printOrigin() {
		listComponent.printOrigin();
	}

	public ListComponent getComponent() {
		return listComponent;
	}

	public ListDecorator(ListComponent listComponent) {

		this.listComponent = listComponent;
	}

	public abstract int getRow();

	public abstract int getIndex();
	
	public String getValue(){
		return listComponent.getValue();
	}
}