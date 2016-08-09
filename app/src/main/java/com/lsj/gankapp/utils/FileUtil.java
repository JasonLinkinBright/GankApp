package com.lsj.gankapp.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ClassName: FileUtil
 * Desc:
 * Created by lsj on 16/7/28.
 */
public class FileUtil {

    public static Uri saveBitmap(Bitmap bitmap,String name){
        File dir= new File(Environment.getExternalStorageDirectory(),"Gank");
        if (!dir.exists()){
            dir.mkdirs();
        }

        File file=new File(dir,name);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream=new FileOutputStream(file);
            if (bitmap!=null){
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(file);
    }
}
