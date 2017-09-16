package link.webarata3.dro.intentservicetest2;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class TestIntentService extends IntentService {
    private static final String TAG = "TestIntetnService";

    public TestIntentService() {
        super(TestIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "Service Started!");

        ResultReceiver receiver = intent.getParcelableExtra("receiver");

        Bundle bundle = new Bundle();

        for (int i = 0; i < 100; i++) {
            try {
                bundle.putString("progress", i + "%");
                receiver.send(1, bundle);

                TimeUnit.MILLISECONDS.sleep(100);
            }catch(InterruptedException e) {
            }
        }
        Log.d(TAG, "Service Stopping!");
        stopSelf();
    }
}
