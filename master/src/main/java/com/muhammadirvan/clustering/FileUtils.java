package com.muhammadirvan.clustering;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.net.URISyntaxException;

/**
 * Created by Irvan on 06/12/2016.
 */
public class FileUtils {
    public static String getPath(Context context, Uri uri)throws URISyntaxException{
        if("content".equalsIgnoreCase(uri.getScheme())){
            String[] projection = { "_data" };
            Cursor cursor = null;

            try{
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if(cursor.moveToFirst()){
                    return cursor.getString(column_index);
                }
            }catch (Exception e){

            }
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            return uri.getPath();
        }
        return null;
    }
}
