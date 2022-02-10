package com.example.sensoresenandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int whip = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acelerometroSensor();
        aproximidadSensor();

    }








    //ACELEROMETRO-------------------------------------------------------------

    private void acelerometroSensor(){

        final EditText nombre = (EditText)this.findViewById(R.id.nombre);
        final TextView recibir1 = (TextView)findViewById(R.id.recibir1);
        final TextView recibir2 = (TextView)findViewById(R.id.recibir2);
        final int[] tamano = new int[1];
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        if(sensor == null) finish();
        sensorEventListener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                if(x < -5 && whip == 0){
                    whip++;
                    String nom = nombre.getText().toString();
                    tamano[0] = nom.length();
                    recibir1.setText(nom);
                    recibir2.setText(tamano[0] + " Letras en total");
                    Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(50);
                }
                if(whip == 1){
                    whip = 0;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }


        };
        start();
    }

    private void start(){
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    //TERMINA ACELEROMETRO-------------------------------------------------------------








    //APROXIMIDAD--------------------------------------------------------------

    private void aproximidadSensor(){

        final EditText nombre = (EditText)this.findViewById(R.id.nombre);
        final TextView recibir1 = (TextView)findViewById(R.id.recibir1);
        final TextView recibir2 = (TextView)findViewById(R.id.recibir2);
        sensorManager = (SensorManager)getSystemService( SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);


        if(sensor==null)finish();
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] < sensor.getMaximumRange()){
                    nombre.setText("");
                    recibir1.setText("");
                    recibir2.setText("");
                }else{

                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        startProx();
    }

    public void startProx(){
        sensorManager.registerListener(sensorEventListener,sensor,2000*1000);
    }

    //TERMINA APROXIMIDAD------------------------------------------------------






}