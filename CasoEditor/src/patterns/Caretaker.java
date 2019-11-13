package patterns;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.text.StyledDocument;

public class Caretaker {
	private LinkedBlockingDeque<Memento> previousMementos;
	private LinkedBlockingDeque<Memento> nextMementos;
	
	private static Caretaker instance = null;
	
	private Caretaker(){
		previousMementos = new LinkedBlockingDeque<>();
		nextMementos = new LinkedBlockingDeque<>();
	}
	
	public static Caretaker getInstance() {
		if(instance == null) {
			instance = new Caretaker();
		}
		return instance;
	}
	
	public boolean checkPreviousMementoStateChange(Memento memento) {
		if(!this.previousMementos.isEmpty()) {
			ArrayList<String> textState = this.previousMementos.peek().getTextState();
			for(int i=0; i<textState.size(); i++) {
				String text = textState.get(i);
				if(!text.equals(memento.getTextState().get(i)) || memento.getTextState().size() != textState.size()) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	public void savePreviousMemento(Memento memento) {
		if(checkPreviousMementoStateChange(memento)) {
			addPreviousMemento(memento);
		}
	}
	
	public void addPreviousMemento(Memento memento) {
		if(checkMementosSize()) {
			replaceOldMementos(memento);
			return;
		}
		this.previousMementos.push(memento);
	}
	
	public void addNextMemento(Memento memento) {
		if(checkMementosSize()) {
			replaceNewMementos(memento);
			return;
		}
		this.nextMementos.push(memento);
	}
	
	public Memento getPreviousMemento(Memento nextMemento) {
		if(!this.previousMementos.isEmpty()) {
			addNextMemento(nextMemento);
			return this.previousMementos.pop();
		}
		return null;
	}
	
	public Memento getNextMemento(Memento memento) {
		if(!this.nextMementos.isEmpty()) {
			if(this.previousMementos.peek().getTextState().size() != memento.getTextState().size())
				this.addPreviousMemento(memento);
			return this.nextMementos.pop();
		}
		return null;
	}
	
	private boolean checkMementosSize() {
		if(this.previousMementos.size() >= 20 || this.nextMementos.size() >= 20)
			return true;
		return false;
	}
	
	private void replaceOldMementos(Memento memento) {
		this.previousMementos.pollLast();
		this.previousMementos.push(memento);
	}
	
	private void replaceNewMementos(Memento memento) {
		this.nextMementos.pollLast();
		this.nextMementos.push(memento);
	}
	
	public void restartStacks() {
		this.previousMementos.clear();
		this.nextMementos.clear();
	}	
}
