package link.webarata3.dro.intentservicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class TestIntentService extends IntentService {

    public TestIntentService(String name) {
        super(name);
    }

    public TestIntentService(){
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("IntentService","onHandleIntent Start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("IntentService","onHandleIntent finish");
    }
}
