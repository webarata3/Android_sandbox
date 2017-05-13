package link.webarata3.dro.intentservicetest;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.Objects;

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
            for (int i = 0; i <= 100; i++) {
                // https://techbooster.org/android/application/6191/
                // obtainすることでリサイクル対象となったオブジェクトプールから再利用してMessageを生成する
                Messenger messenger = (Messenger) bundle.get("messenger");
                Objects.requireNonNull(messenger);
                Message msg = Message.obtain();
                msg.obj = i + " / 100";
                msg.what = 1;
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
