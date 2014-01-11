package ms.cloudtea.HelloReminder.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

/**
 * Created by Shahab on 1/11/14.
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onActionButtonClicked(View view) {

    }
}