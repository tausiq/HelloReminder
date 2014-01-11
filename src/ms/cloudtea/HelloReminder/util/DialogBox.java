package ms.cloudtea.HelloReminder.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;
import ms.cloudtea.HelloReminder.app.HelloReminderApp;

/**
 * Created with IntelliJ IDEA.
 * User: Shahab
 * Date: 6/29/13
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class DialogBox {

    public static void toast(String message) {
        Toast.makeText(HelloReminderApp.getInstance().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


    public static void showAlert(Context context, String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (title == null || title.length() == 0)
            title = HelloReminderApp.getAppTitle();

        builder.setTitle(title).setMessage(message).setCancelable(false).setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }

}

