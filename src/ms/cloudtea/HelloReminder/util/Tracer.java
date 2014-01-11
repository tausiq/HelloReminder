package ms.cloudtea.HelloReminder.util;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import ms.cloudtea.HelloReminder.app.Const;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Shahab on 1/11/14.
 */
public class Tracer {

    public static final String TAG = "h_reminder";


    // private constructor - utility class
    private Tracer() {}


    /**
     * Logs the value of the intent datas - which can be viewed
     *
     * @param i
     */
    public static void dumpIntent(Intent i)
    {

        Bundle bundle = i.getExtras();
        if (bundle != null) {
            Set<String> keys = bundle.keySet();
            Iterator<String> it = keys.iterator();
            Tracer.e(TAG,"Dumping Intent start");
            while (it.hasNext()) {
                String key = it.next();
                Tracer.e(TAG,"[" + key + "=" + bundle.get(key)+"]");
            }
            Tracer.e(TAG,"Dumping Intent end");
        }
    }


    /**
     * Debug (Tracer.d) is invoked
     */
    public static void d(final String tag, final String message)
    {
        if (Const.DEBUG) {
            Log.d(tag, message == null ? "[NULL]" : message);
        }
    }

    /**
     * Debug (Tracer.d) is invoked
     */
    public static void d(final String tag, final Exception e)
    {
        if (Const.DEBUG) {
            Log.d(tag, e == null ? "[NULL]" : e.getMessage());
        }
    }

    /**
     * Debug (Tracer.d) is invoked
     */
    public static void d(final String message)
    {
        if (Const.DEBUG) {
            Log.d(TAG, message == null ? "[NULL]" : message);
        }
    }


    /**
     * Debug (Tracer.d) is invoked
     */
    public static void e(final String tag, final String message)
    {
        if (Const.DEBUG) {
            Log.e(tag, message == null ? "[NULL]" : message);
        }
    }


}

