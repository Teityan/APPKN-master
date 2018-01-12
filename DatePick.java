package teityan.com.appkn;

/**
 * Created by tenko_w8othx0 on 2017/11/01.
 */

        import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.os.Bundle;
        import android.support.v4.app.DialogFragment;
        import java.util.Calendar;
public class DatePick extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(this.getActivity(), (RecordActivity)getActivity(), year, month, day);
    }
    @Override
    public void onDateSet(android.widget.DatePicker view, int year,
                          int monthOfYear, int dayOfMonth) {
    }

}