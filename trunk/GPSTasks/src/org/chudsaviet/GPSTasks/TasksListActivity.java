package org.chudsaviet.GPSTasks;

import android.app.*;
import android.os.Bundle;
import com.bitsetters.android.*;

public class TasksListActivity extends ListActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        BulletedTextListAdapter btla = new BulletedTextListAdapter(this);
        
        for(int i=0;i<10;i++){
        	BulletedText bt1 = new BulletedText("Android",getResources().getDrawable(R.drawable.android));
        	BulletedText bt2 = new BulletedText("Gmaps",getResources().getDrawable(R.drawable.gmaps));
        	BulletedText bt3 = new BulletedText("Pizza",getResources().getDrawable(R.drawable.pizza));
        	BulletedText bt4 = new BulletedText("Application",getResources().getDrawable(R.drawable.icon));
        	btla.addItem(bt1);
        	btla.addItem(bt2);
        	btla.addItem(bt3);
        	btla.addItem(bt4);
        }
        setListAdapter(btla);
    }
}