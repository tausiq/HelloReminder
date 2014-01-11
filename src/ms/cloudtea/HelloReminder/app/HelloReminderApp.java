package ms.cloudtea.HelloReminder.app;

import android.app.Application;
import ms.cloudtea.HelloReminder.R;

/**
 * Created by Shahab on 1/11/14.
 */
public class HelloReminderApp extends Application {

    protected static HelloReminderApp instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static HelloReminderApp getInstance() {
        return instance;
    }

    public static String getAppTitle() {
        return getInstance().getString(R.string.app_name);
    }

}
