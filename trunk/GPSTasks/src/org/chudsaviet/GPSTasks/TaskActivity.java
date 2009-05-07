package org.chudsaviet.GPSTasks;

import android.os.*;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.app.*;
import android.database.*;
import android.database.sqlite.*;



public class TaskActivity extends Activity {
	
	static int MINUTES_SCROLL_INTERVAL=5;
	
	
	SQLiteDatabase db;
	Cursor cur;
	int desired_time=30;//in minutes
	
	
	private void fillFieldsFromCursor()
	{
		EditText name=(EditText)findViewById(R.id.task_name_field);
		name.setText(cur.getString(1));
		
		EditText comments=(EditText)findViewById(R.id.task_comments_field);
		comments.setText(cur.getString(6));
		
		EditText dtime=(EditText)findViewById(R.id.task_desired_time_field);
		desired_time=cur.getInt(2);
		dtime.setText(Integer.toString(cur.getInt(2)));
		
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);
        
        try{
        	DatabaseHelper dbh=new DatabaseHelper(this);
        	db=dbh.getWritableDatabase();
        	
        	
        	cur=db.query("task", null, null, null, null, null, "idtask");
        	cur.moveToFirst();
        	this.fillFieldsFromCursor();
        	
        }
        catch(Exception e)
        {
        	Log.e(this.getString(R.string.app_name), e.toString());
        }
        
        
        ImageButton b=(ImageButton)findViewById(R.id.task_button_previuos);
		b.setOnClickListener(     new OnClickListener()
									{
							    		@Override
							    		public void onClick(View v) {
							    			if(!cur.isFirst()){
							    				cur.moveToPrevious();
							    				fillFieldsFromCursor();						    			
							    			}
							    		}
									}
        					);
		
		b=(ImageButton)findViewById(R.id.task_button_next);
		b.setOnClickListener(     new OnClickListener()
									{
							    		@Override
							    		public void onClick(View v) {
							    			if(!cur.isLast()){
							    				cur.moveToNext();
							    				fillFieldsFromCursor();
							    			}
							    		}
									}
        );
		b=(ImageButton)findViewById(R.id.task_time_button_dec);
		b.setOnClickListener(     new OnClickListener()
									{
							    		@Override
							    		public void onClick(View v) {
							    			
							    			EditText dtime=(EditText)findViewById(R.id.task_desired_time_field);
							    			
							    			desired_time-=MINUTES_SCROLL_INTERVAL;
							    			dtime.setText(Integer.toString(desired_time));							    			
							    		}
									}
        );
		
		b=(ImageButton)findViewById(R.id.task_time_button_inc);
		b.setOnClickListener(     new OnClickListener()
									{
							    		@Override
							    		public void onClick(View v) {
							    			EditText dtime=(EditText)findViewById(R.id.task_desired_time_field);
							    	
							    			desired_time+=MINUTES_SCROLL_INTERVAL;
							    			dtime.setText(Integer.toString(desired_time));
							    		}
									}
        );
	}
}