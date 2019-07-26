package com.example.mydeepnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements AsyncCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpenDetail = findViewById(R.id.btn_open_detail);
        btnOpenDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_TITLE,"Hai apa kabar");
                intent.putExtra(DetailActivity.EXTRA_MESSAGE,"Ini adalah sebuah program aplikasi yg luar biasa mas bro");
                startActivity(intent);
            }
        });

        //Menjalankan AsyncTask
        DelayAsync delayAsync = new DelayAsync(this);
        delayAsync.execute();
    }

    @Override
    public void postAsync() {
        AjatHelper.showNotificationBisaDiklik(this,"Hai, lagi apa?","Apakah kamu punya rencana hari ini? Main yuk",110,"Channel 3","Channel email",R.drawable.ic_email_black);
    }

    private static class DelayAsync extends AsyncTask<Void,Void,Void>{
        WeakReference<AsyncCallback> callbackWeakReference;
        DelayAsync(AsyncCallback callback){
            this.callbackWeakReference = new WeakReference<>(callback);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            callbackWeakReference.get().postAsync();
        }
    }
}
