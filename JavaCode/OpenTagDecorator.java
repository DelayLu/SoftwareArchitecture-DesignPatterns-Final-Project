

//Decorate pattern, to decorate a character with open tags.


public class OpenTagDecorator extends ListDecorator {

	String tag;

	public void printValue() {
		System.out.print(tag);
		super.printValue();
	}

	public void printOrigin() {
		super.printOrigin();
	}

	public OpenTagDecorator(ListComponent listComponent, String newTag,
			int newRow, int newIndex) {

		super(listComponent);

		this.tag = newTag;

		this.row = newRow;
		this.index = newIndex;
	}

	public ListComponent getComponent() {
		return super.getComponent();
	}

	public ListComponentIterator createIterator() {

		return null;
	}

	public int getRow() {
		return this.row;
	}

	public int getIndex() {
		return this.index;
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
		// TODO Auto-generated method stub
		return tag + super.getValue();
	}

}