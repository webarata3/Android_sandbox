package link.webarata3.dro.asynctest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import link.webarata3.dro.asynctest.R;

import static android.content.ContentValues.TAG;

// http://qiita.com/amay077/items/b5f4e98b50d7fbcbaaec
public class MainActivity extends AppCompatActivity implements TestThread.TestTask {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ExecutorService pool;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pool = Executors.newSingleThreadExecutor();

        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.begin_button).setOnClickListener(view -> {
            textView.setText("処理中");
            Log.d(TAG, "Start");
            pool.submit(new TestThread(this));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void progressUpdate(final int test) {
        runOnUiThread(new Runnable() {
            public void run() {
                textView.setText(test + "%");
            }
        });
    }
}

class TestThread extends Thread {
    interface TestTask {
        void progressUpdate(int test);
    }

    private TestTask testTask;

    TestThread(TestTask testTask) {
        this.testTask = testTask;
    }

    @Override
    public void run() {
        Log.d(TAG, "Run task A. ThreadId:" + Thread.currentThread().getId());
        try {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                testTask.progressUpdate(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
