package com.example.sensor;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {
	private ImageView imageView;
    private SensorManager manager;
    private SensorListener Listener = new SensorListener();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        imageView = (ImageView) this.findViewById(R.id.imageView);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }
    
	@Override
	protected void onPause() {
		manager.unregisterListener(Listener);
		super.onPause();
	}


	@Override
	protected void onResume() {
		Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		manager.registerListener(Listener, sensor, SensorManager.SENSOR_DELAY_GAME);
		super.onResume();
	}
	
	private final class SensorListener implements SensorEventListener{
		private float preDegree = 0;

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			
			float degree = event.values[0];//获取方向的值
			RotateAnimation animation = new RotateAnimation(preDegree, -degree,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setDuration(200);
			imageView.startAnimation(animation);
			preDegree = -degree;
		}
		
		
	}


}
