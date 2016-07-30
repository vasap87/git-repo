package FM.View.Tools;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.calendar.SingleDaySelectionModel;


/**
 * Created by vasilenko.aleksandr on 28.07.2016.
 */
public class MyDateTimeTicker extends JXDatePicker {

    public MyDateTimeTicker() {
        super();
        getMonthView().setSelectionModel(new SingleDaySelectionModel());
    }

}
