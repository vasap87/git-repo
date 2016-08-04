package FM.Controller.Account;

import FM.Controller.IObservable;
import FM.Controller.IObserver;
import FM.View.Tools.JList.AccountJList;


/**
 * Created by vasilenko.aleksandr on 26.07.2016.
 */
public class AccountList implements IObservable {
    private static AccountList instance = new AccountList();
    private AccountJList accountJList;

    private AccountList(){}

    public static AccountList getInstance(){
        return instance;
    }

    @Override
    public void notifyObservers() {
        accountJList.handelEvent();
    }

    @Override
    public void addObserver(IObserver observer) {
        accountJList = (AccountJList) observer;
    }
}
