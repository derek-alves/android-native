package com.derek.beberagua;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify;
    private EditText editMinutes;
    private TimePicker timePicker;

    private int hour;
    private int minute;
    private int interval;
    private boolean activated;
    private ActionBar appBar;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBar = getSupportActionBar();
        appBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF03DAC5")));

        btnNotify = findViewById(R.id.btn_notify);
        editMinutes = findViewById(R.id.edit_txt_number_interval);
        timePicker = findViewById(R.id.timer_picker);

        preferences = getSharedPreferences("db", Context.MODE_PRIVATE);
        activated = preferences.getBoolean("activated",false);

        if(activated){
            btnNotify.setText(R.string.pause);
            btnNotify.setBackgroundColor(ContextCompat.getColor(this,android.R.color.black));

            int interval = preferences.getInt("interval",0);
            int hour = preferences.getInt("hour",timePicker.getCurrentHour());
            int minute = preferences.getInt("minute",timePicker.getCurrentMinute());

            editMinutes.setText(String.valueOf(interval));
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }



        timePicker.setIs24HourView(true);

    }
//     1 - EVENTO DE CLICK VIA XML
    public void notifyClick(View view) {
       String sInterval =  editMinutes.getText().toString();
        if(sInterval.isEmpty()){
            Toast.makeText(this,R.string.error_message,Toast.LENGTH_LONG).show();
            return;
        }

       hour = timePicker.getCurrentHour();
       minute = timePicker.getCurrentMinute();
       interval = Integer.parseInt(sInterval);

       if(!activated){
           btnNotify.setText(R.string.pause);
           btnNotify.setBackgroundColor(ContextCompat.getColor(this,android.R.color.black));
           activated = true;

           SharedPreferences.Editor editor = preferences.edit();

           editor.putBoolean("activated",true);
           editor.putInt("interval",interval);
           editor.putInt("hour",hour);
           editor.putInt("minute",minute);
           editor.apply();

       }else{
           btnNotify.setText(R.string.notify);
           btnNotify.setBackgroundColor(ContextCompat.getColor(this,R.color.teal_200));
           activated = false;

           SharedPreferences.Editor editor = preferences.edit();
           editor.putBoolean("activated",false);
           editor.remove("interval");
           editor.remove("hour");
           editor.remove("minute");
           editor.apply();
       }



       Log.d("teste","hora: " + hour + " minuto: " + minute + " intervalo: " + interval);
    }
}





    //2 - EVENTO DE CLICK COM OBJETO ANONIMO
//        btnNotify.setOnClickListener(new View.OnClickListener(){
//        @Override
//        public void onClick(View v) {
//            String sInterval =  editMinutes.getText().toString();
//            hour = timePicker.getCurrentHour();
//            minute = timePicker.getCurrentMinute();
//            interval = Integer.parseInt(sInterval);
//            Log.d("teste","hora: " + hour + " minuto: " + minute + " intervalo: " + interval);
//        }
//    });


    //3- EVENTO DE CLICK COM VARIAVEL ANONIMA
//    public View.OnClickListener notifyClick = new View.OnClickListener(){
//        @Override
//        public void onClick(View v) {
//            String sInterval =  editMinutes.getText().toString();
//            hour = timePicker.getCurrentHour();
//            minute = timePicker.getCurrentMinute();
//            interval = Integer.parseInt(sInterval);
//            Log.d("teste","hora: " + hour + " minuto: " + minute + " intervalo: " + interval);
//        }
//    };
