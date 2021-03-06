package com.gbstreetview.ffmpeg;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

import java.io.File;
/*Converting Audio into Different Formats / Sample Rates
Minimal example: transcode from MP3 to WMA:
ffmpeg -i input.mp3 output.wma

You can get the list of supported formats with:
ffmpeg -formats

Convert WAV to MP3, mix down to mono (use 1 audio channel), set bit rate to 64 kbps and sample rate to 22050 Hz:
ffmpeg -i input.wav -ac 1 -ab 64000 -ar 22050 output.mp3

Convert any MP3 file to WAV 16khz mono 16bit:
ffmpeg -i 111.mp3 -acodec pcm_s16le -ac 1 -ar 16000 out.wav

Convert any MP3 file to WAV 20khz mono 16bit for ADDAC WAV Player:
ffmpeg -i 111.mp3 -acodec pcm_s16le -ac 1 -ar 22050 out.wav
cd into dir for batch process:
for i in *.mp3; do ffmpeg -i "$i" -acodec pcm_s16le -ac 1 -ar 22050 "${i%.mp3}-encoded.wav"; done

Picking the 30 seconds fragment at an offset of 1 minute:
In seconds
ffmpeg -i input.mp3 -ss 60 -t 30 output.wav

In HH:MM:SS format
ffmpeg -i input.mp3 -ss 0:01:00 -t 0:00:30 output.wav
https://gist.github.com/protrolium/e0dbd4bb0f1a396fcb55
*/

public class MainActivity extends AppCompatActivity {

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
                    dest+="/adeere616.wav";
                    File sourcF=new File(Environment.getExternalStorageDirectory(),"adeebtest.mp3");

                    if(sourcF.exists()){
                        Toast.makeText(getApplicationContext(),"exist ",Toast.LENGTH_LONG).show();

                    }

                    String source=sourcF.getAbsolutePath();

String command="ffmpeg -i /storage/emulated/0/adeebtest.mp3 -codec:a libmp3lame -b:a 128k "+dest;
              //  runcmd(command);
//"-i", "/storage/emulated/0/adeebtest.mp3", "-c:v", "libx264"
//$ ffmpeg -i file1.mpg -ar 44100 file1-enc.mpg  samplerte
                    /*String cmd[] = new String[]{"-i", "/storage/emulated/0/adeebtest.mp3",
                            "-ss", "10", "-t","16", "-acodec", "copy", dest};*/

                    String cmd[] = new String[]{"-i", "/storage/emulated/0/adeebtest.mp3",
                            "-acodec",  "pcm_s16le","-ac", "1", "-ar", "8000", dest};
runcmd(cmd);
//-ss 00:01:30 -t 30 -acodec copy


                    //ffmpeg -i 111.mp3 -acodec pcm_s16le -ac 1 -ar 16000 out.wav
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
