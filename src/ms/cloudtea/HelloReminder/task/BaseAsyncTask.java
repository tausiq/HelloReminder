package ms.cloudtea.HelloReminder.task;

import android.os.AsyncTask;
import ms.cloudtea.HelloReminder.R;
import ms.cloudtea.HelloReminder.app.BaseActivity;

/**
 * Created by Shahab on 1/11/14.
 */
public abstract class BaseAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    protected IAsyncCallback mCallBack;

    // current activity reference
    protected BaseActivity mActivity;

    //
    protected boolean shouldShowProgress;

    //
    protected String progressMessage;


    public BaseAsyncTask(BaseActivity activity, boolean shouldShowProgress, IAsyncCallback callback)
    {
        mCallBack = callback;
        this.mActivity = activity;
        this.shouldShowProgress = shouldShowProgress;
        setProgressMessage(null);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        if (mCallBack != null) mCallBack.onComplete(result);
    }

    public void setProgressMessage(String progressMessage) {
        // default waiting message
        if (progressMessage == null || progressMessage.equals("")) {
            this.progressMessage = mActivity.getString(R.string.please_wait);

        } else {
            this.progressMessage = progressMessage;
        }

    }
}
