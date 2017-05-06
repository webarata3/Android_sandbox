package link.webarata3.dro.asynctest;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import link.webarata3.dro.asynctest.io.DummyConnection;

public class TestModel implements DummyConnection.Callback {
    private static final TestModel instance = new TestModel();

    private boolean downloading;
    private int downloadCount;

    private ExecutorService downloadPool;

    private TestModel() {
        downloading = false;
        downloadPool = Executors.newSingleThreadScheduledExecutor();
    }

    public static TestModel getInstance() {
        return instance;
    }

    private CopyOnWriteArrayList<TestObserver> testObserverList = new CopyOnWriteArrayList<>();

    public void addObserver(TestObserver testObserver) {
        testObserverList.add(testObserver);
    }

    private void notifyAll(ModelEvent modelEvent) {
        for (TestObserver testObserver : testObserverList) {
            testObserver.notify(modelEvent);
        }
    }

    @Override
    public void onProgress(int rate) {
        setDownloadCount(rate);
    }

    @Override
    public void onSuccess(String content) {
        setDownloadCount(100);
    }

    @Override
    public void onFailure(Throwable throwable) {
    }

    public synchronized void beginDownload() {
        if (downloading) return;
        downloading = true;
        downloadCount = 0;
        notifyAll(ModelEvent.BEGIN_COUNT);
        DummyConnection dummyConnection = new DummyConnection(this);
        downloadPool.submit(new Runnable() {
            @Override
            public void run() {
                dummyConnection.get();
            }
        });
    }

    public synchronized void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
        for (TestObserver testObserver : testObserverList) {
            testObserver.notify(ModelEvent.ADD_COUNT);
        }
        if (downloadCount == 100) {
            downloading = false;
            notifyAll(ModelEvent.ADD_COUNT);
        }
    }

    public synchronized int getDownloadCount() {
        return downloadCount;
    }
}
