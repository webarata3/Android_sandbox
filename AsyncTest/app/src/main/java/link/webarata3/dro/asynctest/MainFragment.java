package link.webarata3.dro.asynctest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainFragment extends Fragment implements TestThread.TestTask {
    private AppCompatTextView textView;
    private ExecutorService pool;

    private TestModel testModel;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_main, container, false);
        textView = (AppCompatTextView) fragment.findViewById(R.id.textView);

        testModel = TestModel.getInstance();

        fragment.findViewById(R.id.beginButton).setOnClickListener(view -> {
            if (testModel.beginDownload()) {
                textView.setText("処理開始");
                pool = Executors.newSingleThreadExecutor();
                pool.submit(new TestThread(this));
            }
        });

        return fragment;
    }

    // http://qiita.com/syarihu/items/a8bb189d33585238c287
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void progressUpdate(int test) {
        getActivity().runOnUiThread(() -> {
            testModel.setDownloadCount(test);
            textView.setText(""+ test);
            if (testModel.checkDownloadFinished()) {
                testModel.finishDownload();
            }
        });
    }
}

class TestThread extends Thread {
    private static final String TAG = TestThread.class.getSimpleName();

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
            for (int i = 0; i <= 100; i++) {
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
