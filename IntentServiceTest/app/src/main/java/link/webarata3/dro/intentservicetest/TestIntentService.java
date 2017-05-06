package link.webarata3.dro.intentservicetest;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class TestIntentService extends IntentService {
    public TestIntentService(String name) {
        super(name);
    }

    public TestIntentService() {
        super("TestIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("IntentService", "onHandleIntent Start");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            for (int i = 0; i < 100; i++) {
                Messenger messenger = (Messenger) bundle.get("messenger");
                Message msg = Message.obtain();
                Bundle bundle2 = new Bundle();
                bundle2.putCharSequence("test", "test" + i);
                msg.setData(bundle2);
                try {
                    messenger.send(msg);
                } catch (RemoteException e) {
                    Log.i("error", "error");
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d("IntentService", "onHandleIntent finish");
    }
}
