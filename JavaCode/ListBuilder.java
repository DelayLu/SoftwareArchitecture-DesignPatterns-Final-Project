import java.util.ArrayList;


//Builder pattern

public class ListBuilder {
	
	
	private ListComponent listRoot;

	private ArrayList<ListComponent> nodeList = new ArrayList<ListComponent>();
	
	private CompositeFactory compositeFactory = new CompositeFactory();

	private FlyweightFactory flyweightFactory = new FlyweightFactory();
	

	public OpenTagDecorator buildOpenTag(int row, int index, String tag) {
		
		ListComponent newComponent = compositeFactory.createComposite();
		
		OpenTagDecorator open = null;
		
		if(nodeList.size() == 0){
			listRoot = newComponent;
			nodeList.add(listRoot);
		}
		else{
			ListComponent topComponent = (ListComposite)nodeList.get(row - 1);
			int size = topComponent.numberOfChildren();
			
			if(index == size + 1){
				ListComponent openDecorator = new OpenTagDecorator(new Item(' '), tag, row, index);
				newComponent = openDecorator;
				topComponent.addChild(newComponent, index - 1);
				open = (OpenTagDecorator) openDecorator;
			}
			else{
				ListComponent openDecorator = new OpenTagDecorator(topComponent.getChild(index - 1), tag, row, index);
				newComponent = openDecorator;
				topComponent.remove(index - 1);
				topComponent.addChild(newComponent, index - 1);
				open = (OpenTagDecorator) openDecorator;
			}
		}
		
		return open;
		
	}
	
	
	

	public CloseTagDecorator buildCloseTag(int row, int index, String tag) {
		
		CloseTagDecorator close = null;

		if(nodeList.size() != 0){
			ListComponent topComponent = (ListComposite)nodeList.get(row - 1);
			int size = topComponent.numberOfChildren();
			
			if(index < size + 1){
				ListComponent lastChild = topComponent.getChild(index - 1);
				CloseTagDecorator closeDecorator = new CloseTagDecorator(lastChild, tag, row, index);
				topComponent.remove(index - 1);
				topComponent.addChild(closeDecorator, index - 1);
				close = closeDecorator;
			}
		}
		return close;
	}

	public void buildElement(char item) {
		
		ListComponent newItem = flyweightFactory.getFlyweight((int)item);
		
		if (nodeList.size() == 0) {
			listRoot = compositeFactory.createComposite();
			nodeList.add(listRoot);
		}

			ListComponent topComponent = (ListComposite) nodeList.get(nodeList.size() - 1);

			int size = topComponent.numberOfChildren();

			topComponent.addChild(newItem, size);
		
	}
	
	public void buildRow(){
		
		ListComponent nextRow = compositeFactory.createComposite();
		
		if(nodeList.size() == 0){
			listRoot = nextRow;
			nodeList.add(listRoot);
		}
		
		ListComponent topComponent = (ListComposite)nodeList.get(nodeList.size() - 1);
		int size = topComponent.numberOfChildren();
		topComponent.addChild(nextRow, size);
		nodeList.add(nextRow);
	}
	
	public ListComponent getList() {
		
		return listRoot;
	}
	
	public ListDecorator deleteTag(int index, int row){
		ListComponent decorator;
		
		ListComponent newComponent;
		ListComponent topComponent = (ListComposite)nodeList.get(row - 1);
		newComponent = topComponent.getChild(index - 1);
		decorator = newComponent;
		
		newComponent = newComponent.getComponent();
		
		topComponent.remove(index - 1);
		topComponent.addChild(newComponent, index - 1);
		
		return (ListDecorator)decorator;
		
	}
	
	public ListDecorator findTag(int index, int row){
		ListDecorator decorator;
		
		decorator =  (ListDecorator)nodeList.get(row - 1).getChild(index - 1);
		
		return (ListDecorator)decorator;
	}
	
	
	public void deletePair(ListDecorator deleteDec){
		ListComponent newComponent;
		ListComponent topComponent = (ListComposite)nodeList.get(deleteDec.getRow() - 1);
		newComponent = topComponent.getChild(deleteDec.getIndex() - 1).getComponent();
		
		topComponent.remove(deleteDec.getIndex() - 1);
		topComponent.addChild(newComponent, deleteDec.getIndex() - 1);
	}
	
	public ListComponent getComponent(int row){
		return nodeList.get(row - 1);
	}

	
	//Singleton
	private static ListBuilder singleInstance;
	private ListBuilder(){
		
	}
	public static ListBuilder getInstance(){
		if(singleInstance == null){
			singleInstance = new ListBuilder();
		}
		return singleInstance;
	}
	
}
