package org.chudsaviet.LocationPrototype;

import java.util.List;

import android.app.*;
import android.content.*;
import android.location.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.*;
import android.widget.*;

public class LocationPrototype extends Activity {
	
	public OnClickListener buttonListener=new OnClickListener()
	{
		@Override
		public void onClick(View v) {
			LocationManager lm=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		       
	        
	        Log.i("GPS_PROVIDER", LocationManager.GPS_PROVIDER);
	        Log.i("NETWORK_PROVIDER", LocationManager.NETWORK_PROVIDER);
	        Log.i("KEY_PROXIMITY_ENTERING", LocationManager.KEY_PROXIMITY_ENTERING);
	        
	        List<String> ls=lm.getAllProviders();
	        
	        for(int i=0;i<ls.size();i++)
	        	Log.i("Provider "+i, ls.get(i));

	        LocationProvider lp=lm.getProvider("gps");
	        Log.i("lp.getName()",lp.getName());
	        
	        
	        if(lm.isProviderEnabled("gps"))
	        {
	        	Log.i("Location Provider", "gps enabled");
	        	
	        	try{
	        		Location l=lm.getLastKnownLocation("gps");
	        		Log.i("Location", l.toString());
	        	}catch(Exception e)
	        	{
	        		Log.e("Location", e.toString());
	        	}
	        }
	        else
	        	Log.i("Location Provider", "gps disabled");
	        	
		}

	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.main);
        
        Button b=(Button)findViewById(R.id.button);
        b.setOnClickListener(buttonListener);
    }
}