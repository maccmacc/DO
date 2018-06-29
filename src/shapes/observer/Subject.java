package shapes.observer;

public interface Subject {
	public void addObserver(Observer ob);
	public void deleteObserver(Observer ob);
	public void notifyAllObservers();
}
