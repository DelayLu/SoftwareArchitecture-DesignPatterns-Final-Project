


//Decorate pattern, to decorate a character with close tags.
public class CloseTagDecorator extends ListDecorator {
	
	String tag;
	
	
	public void printValue() {
		
		super.printValue();
		System.out.print(tag);
		
	}
	
	public void printOrigin(){
		super.printOrigin();
	}
	
	public ListComponent getCompionent(){
		return super.getComponent();
	}

	
	public CloseTagDecorator(ListComponent listComponent, String newTag, int newRow, int newIndex) {
		
		super(listComponent);
		
		this.tag = newTag;
		
		this.row = newRow;
		this.index = newIndex;
	}


	public ListComponentIterator createIterator() {
		return null;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getIndex(){
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
		return super.getValue() + tag;
	}
	
}