

//Iterator pattern

public class JavaListIterator implements ListComponentIterator {

	private List javaList = new ListAdapter();

	private int index = 0;

	public JavaListIterator(List childrenList) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		this.javaList = childrenList;
		// end-user-code
	}

	public void first() {
		// begin-user-code
		// TODO Auto-generated method stub
		index = 0;
		// end-user-code
	}

	public void next() {
		// begin-user-code
		// TODO Auto-generated method stub
		index++;
		// end-user-code
	}

	public boolean isDone() {
		// begin-user-code
		// TODO Auto-generated method stub
		return index >= javaList.count();
		// end-user-code
	}

	public ListComponent currentItem() {
		// begin-user-code
		// TODO Auto-generated method stub
		return javaList.getAt(index);
		// end-user-code
	}
}