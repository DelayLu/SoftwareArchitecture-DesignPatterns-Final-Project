import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class MainUI {
	
	ListBuilder builder = ListBuilder.getInstance();
	
	HashMap<ListDecorator, ListDecorator> pair = new HashMap<ListDecorator, ListDecorator>();
	
	EnterCommand enterCommand;
	RemoveCommand removeCommand;
	Stack<Command> undoStack = new Stack<Command>();
	Stack<Command> redoStack = new Stack<Command>();
	
	OpenTagDecorator open;
	CloseTagDecorator close;
	
	ListDecorator firstDecorator;
	ListDecorator pairDecorator;
	
	String openTag;
	String closeTag;
	
	int openTagIndex;
	int closeTagIndex;
	int openRow;
	int closeRow;
	
	JButton exportButton;
	JTextArea textArea;
	JLabel infoLabel;
	
	ListComponentIterator iterator;
	ListComponentIterator newIterator;
	ListComponent list;
	ListComponent newList;
	JComboBox<String> tags;
	
	JTextField openIndexText;
	JTextField closeIndexText;
	JTextField openRowText;
	JTextField closeRowText;
	JTextField deleteRowText;
	JTextField deleteIndexText;
	
	JLabel openLabel;
	JLabel closeLabel;
	JLabel openRowLabel;
	JLabel closeRowLabel;
	JLabel deleteRowLabel;
	JLabel deleteIndexLabel;

	public void doWork() throws FileNotFoundException {
		
		File file = new File("src/textFile.txt");
		Scanner scanner = new Scanner(file);
		
		
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		openIndexText = new JTextField(2);
		closeIndexText = new JTextField(2);
		openRowText = new JTextField(2);
		closeRowText = new JTextField(2);
		deleteRowText = new JTextField(2);
		deleteIndexText = new JTextField(2);
		
		openLabel = new JLabel("openTagIndex:");
		closeLabel = new JLabel("closeTagIndex:");
		openRowLabel = new JLabel("open row:");
		closeRowLabel = new JLabel("close row:");
		deleteRowLabel = new JLabel("delete row:");
		deleteIndexLabel = new JLabel("delete index:");
		infoLabel = new JLabel("This is information label");
		
		
		String reader = "";
		
		while(scanner.hasNextLine()){
			reader = scanner.nextLine();
			for(int i = 0; i < reader.length(); i++){
				builder.buildElement(reader.charAt(i));
			}
			builder.buildRow();
		}
		
		scanner = new Scanner(file);
		while(scanner.hasNextLine()){
			textArea.append(scanner.nextLine());
			textArea.append("\n");
		}
		
		list = builder.getList();
		
		JFrame guiFrame = new JFrame();

		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("CS4015Project By Yang Lu");
		guiFrame.setSize(800, 600); 

		guiFrame.setLocationRelativeTo(null);

		String[] tagOptions = {" ", "HTML", "HEAD", "TITLE", "BODY",
				"H1", "H2", "H3", "B", "I", "U", "P", "BR/" };

		final JPanel comboPanel = new JPanel();
		JLabel comboLbl = new JLabel("Tags:");
		tags = new JComboBox<String>(tagOptions);

		comboPanel.add(comboLbl);
		comboPanel.add(tags);
		comboPanel.add(openRowLabel);
		comboPanel.add(openRowText);
		comboPanel.add(openLabel);
		comboPanel.add(openIndexText);
		comboPanel.add(closeRowLabel);
		comboPanel.add(closeRowText);
		comboPanel.add(closeLabel);
		comboPanel.add(closeIndexText);
		comboPanel.add(deleteRowLabel);
		comboPanel.add(deleteRowText);
		comboPanel.add(deleteIndexLabel);
		comboPanel.add(deleteIndexText);
		
		
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		JButton addButton = new JButton("Add");
		JButton deleteButton = new JButton("Delete");
		JButton undoButton = new JButton("Undo");
		JButton redoButton = new JButton("Redo");
		exportButton = new JButton("Export");
		buttonPanel.add(infoLabel);
		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(undoButton);
		buttonPanel.add(redoButton);
		buttonPanel.add(exportButton);

		
		
		tags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if(tags.getSelectedItem().equals("HTML")){
					openTag = "<HTML>";
					closeTag = "</HTML>";
				}
				else if(tags.getSelectedItem().equals("HEAD")){
					openTag = "<HEAD>";
					closeTag = "</HEAD>";
				}
				else if(tags.getSelectedItem().equals("TITLE")){
					openTag = "<TITLE>";
					closeTag = "</TITLE>";
				}
				else if(tags.getSelectedItem().equals("BODY")){
					openTag = "<BODY>";
					closeTag = "</BODY>";
				}
				else if(tags.getSelectedItem().equals("H1")){
					openTag = "<H1>";
					closeTag = "</H1>";
				}
				else if(tags.getSelectedItem().equals("H2")){
					openTag = "<H2>";
					closeTag = "</H2>";
				}
				else if(tags.getSelectedItem().equals("H3")){
					openTag = "<H3>";
					closeTag = "</H3>";
				}
				else if(tags.getSelectedItem().equals("B")){
					openTag = "<B>";
					closeTag = "</B>";
				}
				else if(tags.getSelectedItem().equals("I")){
					openTag = "<I>";
					closeTag = "</I>";
				}
				else if(tags.getSelectedItem().equals("U")){
					openTag = "<U>";
					closeTag = "</U>";
				}
				else if(tags.getSelectedItem().equals("P")){
					openTag = "<P>";
					closeTag = "</P>";
				}
				else if(tags.getSelectedItem().equals("BR/")){
					openTag = "<BR/>";
					closeTag = "";
				}

			}
		});
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				ListComposite.firstLine = true;

				openTagIndex = Integer.parseInt(openIndexText.getText());
				closeTagIndex = Integer.parseInt(closeIndexText.getText());
				openRow = Integer.parseInt(openRowText.getText());
				closeRow = Integer.parseInt(closeRowText.getText());

				if(openRow > closeRow){
					if(openTagIndex > closeTagIndex){
						infoLabel.setText("");
						infoLabel.setText("Wrong! Open tag should be in front of close tag!");
					}
					
				}
				else{
					enterCommand = new EnterCommand(builder, openTag, closeTag, openTagIndex, closeTagIndex, openRow, closeRow);
					enterCommand.execute();
					undoStack.push(enterCommand);
					open = enterCommand.getOpenTag();
					close = enterCommand.getCloseTag();
					
					pair.put(open, close);
					pair.put(close, open);
					
					textArea.setText("");
					
					String result = "";
					
					newList = builder.getList();
					
					newIterator = newList.createPreorderIterator(list);
					
					for (newIterator.first(); !newIterator.isDone(); newIterator.next()){
						
						ListComponent listComponent = newIterator.currentItem();
						
						result += listComponent.getValue();
					}
					System.out.println(result);
					textArea.setText(result);
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				ListComposite.firstLine = true;

				int index = 0;
				int row = 0;
				
				
				
				if(deleteIndexText.getText().equals("") || deleteRowText.getText().equals("")){
					infoLabel.setText("Error! Enter delete row and index!");
				}
				else{
					index = Integer.parseInt(deleteIndexText.getText());
					row = Integer.parseInt(deleteRowText.getText());
					
					removeCommand = new RemoveCommand(builder, pair, index, row);
					removeCommand.execute();
					undoStack.push(removeCommand);
					
					textArea.setText("");
					
					String result = "";
					
					newList = builder.getList();
					
					newIterator = newList.createPreorderIterator(list);
					
					for (newIterator.first(); !newIterator.isDone(); newIterator.next()){
						
						ListComponent listComponent = newIterator.currentItem();
						
						result += listComponent.getValue();
					}
					System.out.println(result);
					textArea.setText(result);
				}
			}
		});
		
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				ListComposite.firstLine = true;

				if(undoStack.size() != 0){
					Command command = undoStack.pop();
					redoStack.push(command);
					command.unexecute();
				}
				
				textArea.setText("");
				
				String result = "";
				
				newList = builder.getList();
				
				newIterator = newList.createPreorderIterator(list);
				
				for (newIterator.first(); !newIterator.isDone(); newIterator.next()){
					
					ListComponent listComponent = newIterator.currentItem();
					
					result += listComponent.getValue();
				}
				System.out.println(result);
				textArea.setText(result);

			}
		});
		
		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				ListComposite.firstLine = true;

				if(redoStack.size() != 0){
					Command command = redoStack.pop();
					command.execute();
				}

				
				textArea.setText("");
				
				String result = "";
				
				newList = builder.getList();
				
				newIterator = newList.createPreorderIterator(list);
				
				for (newIterator.first(); !newIterator.isDone(); newIterator.next()){
					
					ListComponent listComponent = newIterator.currentItem();
					
					result += listComponent.getValue();
				}
				System.out.println(result);
				textArea.setText(result);
			}
		});
		
		
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				
				
				String result = "";//textArea.getText();
				
				String fileName = "htmlFile";

				
				
				newList = builder.getList();
				
				newIterator = newList.createPreorderIterator(list);
				
				for (newIterator.first(); !newIterator.isDone(); newIterator.next()){
					
					ListComponent listComponent = newIterator.currentItem();
					
					result += listComponent.getValue();
				}
				
				

				File newFile = new File("result/" + fileName + ".html");
				BufferedWriter output;
				
					try {
						output = new BufferedWriter(new FileWriter(newFile));
						output.write(result);
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoLabel.setText("Export success! File is in project/result/htmlFile.html");
					
					
					System.out.println(result);
				} 
			
			
			
		});
		

		guiFrame.add(comboPanel, BorderLayout.NORTH);

		guiFrame.add(textArea, BorderLayout.CENTER);
		
		guiFrame.add(buttonPanel, BorderLayout.SOUTH);

		guiFrame.setVisible(true);
	}
	
	public MainUI() {

	}

}