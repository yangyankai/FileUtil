package com.example.administrator.fileutil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*******************************************************************************
 * Author  : Yankai
 * Date    : 2016-04-27 19:03
 * Email   : yk_yang@wesugarfree.com
 * Company : 上海无糖运动
 ******************************************************************************/
public class BitmapUtil {

    public  void downLoad_saveBitmap(final String string) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte data[] = getUrlData(string);
                    Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                    saveBitmap(bm);
                } catch (Exception e) {
                }
            }
        }).start();
    }

    /**
     * 通过Urlconnection 请求获取数据
     *
     * @return
     * @throws Exception
     */
    public byte[] getUrlData(String PATH) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            URL url = new URL(PATH);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte data[] = new byte[1024];
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream input = conn.getInputStream();
            int len = 0;
            while ((len = input.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw e;
        } finally {
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
    }

    public void saveBitmap(Bitmap bitmap) {
        String sdcardBmpPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/person_avator1";
        File file = new File(sdcardBmpPath);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 70, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Bitmap loadBitmap() {
        return BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/person_avator1");
    }
}
