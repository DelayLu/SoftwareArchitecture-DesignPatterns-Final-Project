import java.util.Stack;


//Iterator pattern


public class PreorderIterator implements ListComponentIterator {

	private ListComponent root;
	private ListComponent currentItem;
	private Stack<ListComponentIterator> iterStack = new Stack<ListComponentIterator>();
	private boolean done = false;

	public PreorderIterator(ListComponent root) {
		this.root = root;
	}

	public ListComponent currentItem() {
		return this.currentItem;
	}

	public boolean isDone() {
		boolean currentDone = done;
		while (!iterStack.isEmpty() && iterStack.peek().isDone())
			iterStack.pop();
		if (iterStack.isEmpty())
			done = true;
		return currentDone;
	}

	public void first() {
		if (root != null) {
			this.currentItem = root;
			ListComponentIterator iter = currentItem.createIterator();
			if (!iter.isDone()) {
				iter.first();
				iterStack.push(iter);
			}
		}
	}

	public void next() {
		while (!iterStack.isEmpty() && iterStack.peek().isDone()){
			iterStack.pop();
		}
			
		if (!iterStack.isEmpty()) {
			ListComponentIterator iter = iterStack.peek();
			this.currentItem = iter.currentItem();
			ListComponentIterator iter2 = currentItem.createIterator();
			
			if (iter2 != null && !iter2.isDone()) {
				iter2.first();
				iterStack.push(iter2);
			}
			
			iter.next();

		}
	}

}