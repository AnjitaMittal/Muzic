package science.wookup.muzic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import static science.wookup.muzic.MainActivity.KEY_SONG;

public class DownloadService extends Service {
    private static final String TAG = DownloadService.class.getSimpleName();
    private DownloadHandler downloadHandler;

    @Override
    public void onCreate() {
        DownloadThread downloadThread = new DownloadThread();
        downloadThread.setName("DownloadThread");
        downloadThread.start();

        while (downloadThread.downloadHandler == null) { }

        downloadHandler = downloadThread.downloadHandler;
        downloadHandler.setService(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String song = intent.getStringExtra(KEY_SONG);
        Message message = Message.obtain();
        message.obj = song;
        message.arg1 = startId;
        downloadHandler.sendMessage(message);
        return Service.START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
