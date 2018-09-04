package science.wookup.muzic;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DownloadThread thread = new DownloadThread();
        thread.setName("Downloading Thread");
        thread.start();

        Button downloadSongButton = findViewById(R.id.downloadSong);
        downloadSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Downloading...", Toast.LENGTH_SHORT).show();

                // Send messages or runnable to handler
                for (String song: Songs.songs) {
                    Message message = Message.obtain();
                    message.obj = song;
                    thread.downloadHandler.sendMessage(message);
                }

            }
        });
    }


}
