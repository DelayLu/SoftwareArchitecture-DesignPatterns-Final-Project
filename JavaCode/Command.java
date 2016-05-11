
//Command pattern, for undo and redo control.

public interface Command {

	Memento memento = null;

	ListComponent pqueue = null;

	public void execute();

	public void unexecute();
}