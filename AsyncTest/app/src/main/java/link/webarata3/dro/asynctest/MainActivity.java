package link.webarata3.dro.asynctest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

// http://qiita.com/amay077/items/b5f4e98b50d7fbcbaaec
public class MainActivity extends AppCompatActivity
    implements  MainFragment.OnFragmentInteractionListener, TestObserver {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TestModel testModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testModel = TestModel.getInstance();
        testModel.addObserver(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onClickBeginButton() {
        testModel.beginDownload();
    }

    @Override
    public void notify(ModelEvent modelEvent) {
        MainFragment mainFragment =
            (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMain);
        switch(modelEvent) {
            case BEGIN_COUNT:
                runOnUiThread(() -> {
                  mainFragment.viewDownloadStatus(0);
                });
                break;
            case ADD_COUNT:
                runOnUiThread(() -> {
                    mainFragment.viewDownloadStatus(testModel.getDownloadCount());
                });
                break;
            case FINISH_COUNT:
                runOnUiThread(() -> {
                    mainFragment.viewDownloadStatus(testModel.getDownloadCount());
                });
                break;
        }
    }
}
