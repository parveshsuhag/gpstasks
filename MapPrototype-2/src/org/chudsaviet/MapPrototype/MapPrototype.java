package org.chudsaviet.MapPrototype;

import java.util.List;

import com.google.android.maps.*;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MapPrototype extends MapActivity {
   
	List<Overlay> mapOverlays;
	Drawable drawable;
	NuclearItemizedOverlay itemizedOverlay;	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button b=(Button)findViewById(R.id.button);
        b.setOnClickListener(buttonListener);
        
        
        drawable = this.getResources().getDrawable(R.drawable.radioactive_old);
    }

    
    public OnClickListener buttonListener=new OnClickListener()
	{
		public void onClick(View v) {
			
	        	LinearLayout linearLayout = (LinearLayout) findViewById(R.id.zoomview);
	        	MapView mapView=(MapView)findViewById(R.id.mapview);
	        	ZoomControls mZoom=(ZoomControls) mapView.getZoomControls();
	        	
	        	linearLayout.addView(mZoom);
	        	
	        	
	        	mapOverlays = mapView.getOverlays();        	
	        	itemizedOverlay = new NuclearItemizedOverlay(drawable);
	        	
	        	GeoPoint point1 = new GeoPoint(	34050000,-118250000);
	        	OverlayItem overlayitem1 = new OverlayItem(point1, "Los Angeles", "20 Megatons");
	        	itemizedOverlay.addOverlay(overlayitem1);
	        	
	        	GeoPoint point2 = new GeoPoint(40716667,-74000000);
	        	OverlayItem overlayitem2 = new OverlayItem(point2, "New York", "50 Megatons");
	        	itemizedOverlay.addOverlay(overlayitem2);
	        	
	        	
	        	mapOverlays.add(itemizedOverlay);
	     }
	};
    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}

