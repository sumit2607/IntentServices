package com.sumit.intentservices;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class DownloaderService extends IntentService {


    public DownloaderService() {
        super("random");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        downloadfile();
    }

    private void downloadfile() {
        try {
            File directry=new File(getFilesDir()+File.separator+"vogilla");

            if(!directry.exists()){
                directry.mkdir();
            }
            File file=new File(directry,"vogilla.html");

            if(!file.exists()){
                file.createNewFile();
            }
            URL url=new URL("https://www.vogella.com/index.html");
            InputStream inputStream=url.openConnection().getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            FileOutputStream writer=new FileOutputStream(file,true);

            int data=inputStreamReader.read();
            while (data!=-1){
                char ch= (char) data;
                writer.write(ch);
                data=inputStreamReader.read();
            }

            //read from file
            FileInputStream fileInputStream=new FileInputStream(file);
            int filedata=fileInputStream.read();
            StringBuffer stringBuffer=new StringBuffer();
            while (filedata!=-1){
                char ch= (char) filedata;
                stringBuffer=stringBuffer.append(ch);
                filedata=fileInputStream.read();
            }
            Intent intent=new Intent("parv.com");
            intent.putExtra("data",stringBuffer.toString());
            sendBroadcast(intent);

        }catch (Exception e){

        }
    }


}