package link.webarata3.dro.asynctaskloadertest;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.Objects;

public class SampleLoader extends AsyncTaskLoader<String> {
    private Integer count;
    private String result;

    public SampleLoader(Context context, Integer count) {
        super(context);
        this.count = count;
    }

    @Override
    public String loadInBackground() {
        result = "";

        if (count == null) return result;

        for (int i = 0; i < count; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = result + i;
        }

        return result;
    }

    @Override
    public void deliverResult(String data) {
        if (isReset()) {
            if (this.result != null) {
                this.result = null;
            }
            return;
        }

        this.result = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (this.result != null) {
            deliverResult(this.result);
        }
        if (takeContentChanged() || this.result == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
    }
}
