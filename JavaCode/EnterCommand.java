
//Command pattern, for undo and redo

public class EnterCommand implements Command {

	private Memento openMemento;
	private Memento closeMemento;
	
	private OpenTagDecorator openDecorator;
	private CloseTagDecorator closeDecorator;
	private ListBuilder builder;
	private ListComponent openComponent;
	private ListComponent closeComponent;
	private int openIndex;
	private int closeIndex; 
	private int openRow;
	private int closeRow;
	private String openTag;
	private String closeTag;

	public EnterCommand(ListBuilder builder, String openTag, String closeTag,
			int openIndex, int closeIndex, int openRow, int closeRow) {

		this.openComponent = builder.getComponent(openRow);
		this.closeComponent = builder.getComponent(closeRow);
		this.builder = builder;
		this.openIndex = openIndex;
		this.openRow = openRow;
		this.openTag = openTag;
		this.closeIndex = closeIndex;
		this.closeTag = closeTag;
		this.closeRow = closeRow;
	}

	public void execute() {

		this.openMemento = openComponent.createMemento();
		this.closeMemento = closeComponent.createMemento();

		openDecorator = builder.buildOpenTag(openRow, openIndex, openTag);
		closeDecorator = builder.buildCloseTag(closeRow, closeIndex, closeTag);

	}

	public OpenTagDecorator getOpenTag() {
		return openDecorator;
	}

	public CloseTagDecorator getCloseTag() {
		return closeDecorator;
	}

	public void unexecute() {

		openComponent.setState(openMemento);
		closeComponent.setState(closeMemento);

	}
}