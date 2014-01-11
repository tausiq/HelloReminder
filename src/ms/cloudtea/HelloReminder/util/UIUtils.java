package ms.cloudtea.HelloReminder.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.*;
import ms.cloudtea.HelloReminder.R;
import ms.cloudtea.HelloReminder.model.CalendarModel;

import java.util.ArrayList;

/**
 * Created by Shahab on 1/11/14.
 */
public class UIUtils {

    /**
     * Uses static final constants to detect if the device's platform version is ICS or
     * later.
     */
    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }


    public static void showCalendarChooserDialog(FragmentActivity activity, ArrayList<CalendarModel> items) {

        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("calendar_chooser_dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        Bundle args = new Bundle(1);
        args.putParcelableArrayList("items", items);

        CalendarChooserDialog dialog = new CalendarChooserDialog();
        dialog.setArguments(args);
        dialog.show(ft, "calendar_chooser_dialog");
    }

    public static class CalendarChooserDialog extends DialogFragment {

        public interface OnCalendarChooseListener {
            void onSelectCalendar(long id, String name);
        }

        private OnCalendarChooseListener listener;

        public CalendarChooserDialog() {
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            listener = (OnCalendarChooseListener) getActivity();
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final ArrayList<CalendarModel> calendarsArrayList = getArguments().getParcelableArrayList("items");

            if (calendarsArrayList == null) return null;

            CharSequence[] items = new CharSequence[calendarsArrayList.size()];

            for (int i = 0; i < calendarsArrayList.size(); i++) {
                items[i] = calendarsArrayList.get(i).getDisplayName();
            }

            return new AlertDialog.Builder(getActivity())
                    .setTitle(getActivity().getString(R.string.title_calendar_chooser))
                    .setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            listener.onSelectCalendar(calendarsArrayList.get(which).getCalendarId(), calendarsArrayList.get(which).getDisplayName());
                        }
                    })
                    .create();
        }
    }
}
