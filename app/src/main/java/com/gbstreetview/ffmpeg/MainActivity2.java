package com.gbstreetview.ffmpeg;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

import java.io.File;


public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FFmpeg ffmpeg = FFmpeg.getInstance(getApplicationContext());
        try {
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {

                @Override
                public void onStart() {}

                @Override
                public void onFailure() {}

                @Override
                public void onSuccess() {
                    File file=new File(Environment.getExternalStorageDirectory()+"/AdeebTests");
 //file.mkdir();

                  /// File destF=new File(file.getAbsolutePath(),"adeesdss11.mp3");

                    file.mkdir();
                    String dest=file.getAbsolutePath();
                    dest+="/adeere6.wav";
                    File sourcF=new File(Environment.getExternalStorageDirectory(),"adeebtest.mp3");

                    if(sourcF.exists()){
                        Toast.makeText(getApplicationContext(),"exist ",Toast.LENGTH_LONG).show();

                    }

                    String source=sourcF.getAbsolutePath();

String command="ffmpeg -i /storage/emulated/0/adeebtest.mp3 -codec:a libmp3lame -b:a 128k "+dest;
              //  runcmd(command);
//"-i", "/storage/emulated/0/adeebtest.mp3", "-c:v", "libx264"
//$ ffmpeg -i file1.mpg -ar 44100 file1-enc.mpg  samplerte
                    String cmd[] = new String[]{"-i", "/storage/emulated/0/adeebtest.mp3",
                            "-ss", "10", "-t","16", "-acodec", "copy", dest};
runcmd(cmd);
//-ss 00:01:30 -t 30 -acodec copy
                }

                @Override
                public void onFinish() {}
            });
        } catch (FFmpegNotSupportedException e) {
            // Handle if FFmpeg is not supported by device
        }
    }


    public void runcmd(String[] command){
        FFmpeg ffmpeg = FFmpeg.getInstance(getApplicationContext());
        // to execute "ffmpeg -version" command you just need to pass "-version"
        try {
            ffmpeg.execute(command, new ExecuteBinaryResponseHandler() {

                @Override
                public void onStart() {}

                @Override
                public void onProgress(String message) {
                    Toast.makeText(getApplicationContext(),"progress "+message,Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(String message) {
                    Toast.makeText(getApplicationContext(),"fail "+message,Toast.LENGTH_LONG).show();

                }

                @Override
                public void onSuccess(String message) {
                    Toast.makeText(getApplicationContext(),"success "+message,Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFinish() {
                    Toast.makeText(getApplicationContext(),"finish ",Toast.LENGTH_LONG).show();

                }

            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }
}
