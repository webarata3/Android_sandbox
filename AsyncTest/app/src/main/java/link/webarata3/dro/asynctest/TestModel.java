package link.webarata3.dro.asynctest;

public class TestModel {
    private static final TestModel instance = new TestModel();

    private boolean downloading;
    private int downloadCount;

    private TestModel() {
        downloading = false;
    }

    public static TestModel getInstance() {
        return instance;
    }

    public synchronized boolean beginDownload() {
        if (downloading) return false;
        downloading = true;
        downloadCount = 0;
        return true;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public boolean checkDownloadFinished() {
        return downloadCount >= 100;
    }

    public void finishDownload() {
        downloading = false;
        downloadCount = 0;
    }
}
