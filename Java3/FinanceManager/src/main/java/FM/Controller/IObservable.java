package FM.Controller;

/**
 * Created by vasilenko.aleksandr on 26.07.2016.
 */
public interface IObservable {
    void notifyObservers();
    void addObserver(IObserver observer);
}
