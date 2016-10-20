package com.amalgam.content.res;

import android.content.res.AssetFileDescriptor;
import android.util.Log;

import java.io.IOException;

/**
 * Android中没有提供专门支持的文件 叫原始资源文件。android原始资源一般放在/res/raw目录和/assets/目录下；
 * 在assets目录下的文件通过AssetMananger来管理，它提供了几个常用的方法来访问Assets资源
 InputStream open(String fileName):根据文件名来获取原始资源对应的输入流；
 AssetFileDescriptor   openFd(String fileName)：根据文件名来获取原始资源对应的AssetFileDescriptor
 资源描述，应用程序可以通过它来获取原始资源
 package com.android.xiong.rawrestest;

 import android.app.Activity;
 import android.content.res.AssetFileDescriptor;
 import android.content.res.AssetManager;
 import android.media.MediaPlayer;
 import android.os.Bundle;
 import android.view.Menu;
 import android.view.View;
 import android.view.View.OnClickListener;
 import android.widget.Button;

 public class MainActivity extends Activity {

 private Button bt1, bt2;
 MediaPlayer mediaPlayer1 = null;
 MediaPlayer mediaPlayer2 = null;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);
 bt1 = (Button) findViewById(R.id.bt1);
 bt2 = (Button) findViewById(R.id.bt2);
 // 直接根据声音文件的ID来创建MediaPlayer
 mediaPlayer1 = MediaPlayer.create(this, R.raw.bomb);
 // 获取该应用的AssetManager
 AssetManager am = this.getAssets();
 try {
 // 获取指定文件对应的AssetFileDescriptor
 AssetFileDescriptor afd = am.openFd("shot.mp3");
 mediaPlayer2 = new MediaPlayer();
 // 使用MediaPlayer加载指定的声音文件
 mediaPlayer2.setDataSource(afd.getFileDescriptor());
 mediaPlayer2.prepare();
 } catch (Exception e) {
 e.printStackTrace();
 }
 bt1.setOnClickListener(new OnClickListener() {

 @Override
 public void onClick(View v) {
 // 播放声音
 mediaPlayer1.start();
 }
 });
 bt2.setOnClickListener(new OnClickListener() {

 @Override
 public void onClick(View v) {
 // 播放声音
 mediaPlayer2.start();


 }
 });
 }

 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
 // Inflate the menu; this adds items to the action bar if it is present.
 getMenuInflater().inflate(R.menu.main, menu);
 return true;
 }

 }

  * Utility for the {@link android.content.res.AssetFileDescriptor}.
 */

public final class AssetFileDescriptorUtils {
    public static final String TAG = AssetFileDescriptorUtils.class.getSimpleName();

    private AssetFileDescriptorUtils() {
        throw new AssertionError();
    }

    /**
     * Close the descriptor, and if the exception during close will be logged.
     * @param descriptor to close.
     */
    public static void close(AssetFileDescriptor descriptor) {
        if (descriptor == null) {
            return;
        }
        try {
            descriptor.close();
        } catch (IOException e) {
            Log.e(TAG, "something went wrong on close descriptor: ", e);
        }
    }
}