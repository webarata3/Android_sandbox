package link.webarata3.dro.intentservicetest;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class ServiceUtil {
    public static boolean isServiceRunning(Context context, Class<?> clazz) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningService = am.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningService) {
            if (clazz.getName().equals(runningServiceInfo.service.getClassName())) {
                Log.d("#######", "service running");
                return true;
            }
        }
        Log.d("##########", "service not running");
        return false;
    }
}
