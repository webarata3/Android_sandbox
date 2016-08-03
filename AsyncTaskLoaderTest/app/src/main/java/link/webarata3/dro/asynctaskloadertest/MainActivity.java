package link.webarata3.dro.asynctaskloadertest;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SampleLoaderListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SampleLoaderCallbacks sampleLoaderCallbacks = new SampleLoaderCallbacks(this);

        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.begin_button).setOnClickListener(view -> {
            textView.setText("処理中");

            Bundle bundle = new Bundle();
            bundle.putInt("count", 10);
            getLoaderManager().initLoader(
                SampleLoaderCallbacks.LOADER_ID, bundle, sampleLoaderCallbacks);
        });
    }

    @Override
    public void onSampleLoaderFinished(String s) {
        textView.setText(s);
    }
}

interface SampleLoaderListener {
    void onSampleLoaderFinished(String s);
}

class SampleLoaderCallbacks implements LoaderManager.LoaderCallbacks<String> {
    private SampleLoaderListener sampleLoaderListener;

    public static final int LOADER_ID = 1;

    public SampleLoaderCallbacks(SampleLoaderListener sampleLoaderListener) {
        this.sampleLoaderListener = sampleLoaderListener;
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle bundle) {
        if (id == LOADER_ID) {
            Integer count = bundle.getInt("count");
            return new SampleLoader((Context) sampleLoaderListener, count);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        if (loader.getId() == LOADER_ID) {
            sampleLoaderListener.onSampleLoaderFinished(s);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
    }
}
