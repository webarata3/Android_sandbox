package link.webarata3.dro.asynctest;

import android.util.Log;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestModel {
    private static final TestModel instance = new TestModel();

    private boolean downloading;
    private int downloadCount;

    private ExecutorService pool;

    private TestModel() {
        downloading = false;
    }

    public static TestModel getInstance() {
        return instance;
    }

    private CopyOnWriteArrayList<Observer> observerList = new CopyOnWriteArrayList<>();

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public synchronized void beginDownload() {
        if (downloading) return;
        downloading = true;
        downloadCount = 0;
        for (Observer observer : observerList) {
            observer.notify(ModelEvent.BEGIN_COUNT);
        }
        pool = Executors.newSingleThreadExecutor();
        pool.submit(new TestThread(this));
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
        for (Observer observer : observerList) {
            observer.notify(ModelEvent.ADD_COUNT);
        }
        if (downloadCount == 100) {
            downloading = false;
            for (Observer observer : observerList) {
                observer.notify(ModelEvent.FINISH_COUNT);
            }
        }
    }

    public int getDownloadCount() {
        return downloadCount;
    }
}

class TestThread implements Runnable {
    private static final String TAG = TestThread.class.getSimpleName();

    private TestModel testModel;

    TestThread(TestModel testModel) {
        this.testModel = testModel;
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
                testModel.setDownloadCount(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

