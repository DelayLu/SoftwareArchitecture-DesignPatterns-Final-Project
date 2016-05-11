
//Iterator pattern


public interface ListComponentIterator {
	public void first();

	public void next();

	public boolean isDone();

	public ListComponent currentItem();
}