import java.util.HashMap;

//Command pattern, for undo and redo


public class RemoveCommand implements Command {

	private Memento openMemento;
	private Memento closeMemento;
	

	private ListBuilder builder;
	private HashMap<ListDecorator, ListDecorator> pair;
	private int index;
	private int row;
	ListDecorator firstDelete;
	ListDecorator pairDelete;

	private ListComponent openComponent;
	private ListComponent closeComponent;

	public RemoveCommand(ListBuilder builder,
			HashMap<ListDecorator, ListDecorator> newHashMap, int index, int row) {
		
		this.builder = builder;
		this.pair = newHashMap;
		this.index = index;
		this.row = row;

		this.openComponent = builder.getComponent(row);
		this.closeComponent = builder.getComponent(pair.get(builder.findTag(index, row)).getRow());
		
		
	}

	public void execute() {

		this.openMemento = openComponent.createMemento();
		this.closeMemento = closeComponent.createMemento();
		
		firstDelete = builder.deleteTag(index, row);
		pairDelete = pair.get(firstDelete);
		builder.deletePair(pairDelete);

	}

	public void unexecute() {

		openComponent.setState(openMemento);
		closeComponent.setState(closeMemento);
	}
}