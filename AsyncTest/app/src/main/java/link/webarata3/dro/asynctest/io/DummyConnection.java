package link.webarata3.dro.asynctest.io;

public class DummyConnection {
    public interface Callback {
        public void onProgress(int rate);

        public void onSuccess(String content);

        public void onFailure(Throwable throwable);
    }

    private Callback callback;

    public DummyConnection(Callback callback) {
        this.callback = callback;
    }

    public void get() {
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(30);
                callback.onProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        callback.onSuccess("100");
    }
}
