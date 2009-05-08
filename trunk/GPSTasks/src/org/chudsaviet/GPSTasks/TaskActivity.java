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
	static int MINUTES_DEFAULT=30;
	
	enum State {ViewingEditing,NewTask};
	
	SQLiteDatabase db;
	Cursor cur;
	int desired_time=30;//in minutes
	State state;
	int idtask;
	
	private class InsertNewTaskListener implements OnClickListener
	{
		@Override
		public void onClick(View v) {
					Log.d(getString(R.string.app_name),"saving new task");
					
					
					setMode(State.ViewingEditing);
					
		}
		
	}
	
	private class DeleteTaskListener implements OnClickListener
	{
		@Override
		public void onClick(View v) {
					Log.d(getString(R.string.app_name),"deleting task");
					
					String query="DELETE FROM task WHERE idtask="+idtask;
					
					try{				
						db.execSQL(query);
						requery();
					}
					catch(Exception e)
				        {
				        	Log.e(getString(R.string.app_name), e.toString());
				        }
		}
		
	}
	
	private void requery()
	{
		cur.close();
		cur=db.query("task", null, null, null, null, null, "idtask");
		fillFieldsFromCursor();
	}
	
	private void setMode(State state)
	{
		this.state=state;
		
		switch (state) {
		case NewTask:
			cur.close();
			
			Button b=(Button)findViewById(R.id.task_new_save);
			b.setText(this.getString(R.string.save));
			b.setOnClickListener(new InsertNewTaskListener());
			
			
			ImageButton ib=(ImageButton)findViewById(R.id.task_button_previuos);
			ib.setClickable(false);
			
			ib=(ImageButton)findViewById(R.id.task_button_next);
			ib.setClickable(false);
			
			EditText name=(EditText)findViewById(R.id.task_name_field);
			name.setText(this.getString(R.string.enter_name_of_the_task));
			
			EditText comments=(EditText)findViewById(R.id.task_comments_field);
			comments.setText("");
			
			EditText dtime=(EditText)findViewById(R.id.task_desired_time_field);
			dtime.setText(Integer.toString(MINUTES_DEFAULT));
			
			break;
		case ViewingEditing:
			
			cur=db.query("task", null, null, null, null, null, "idtask");
			cur.moveToFirst();
			this.fillFieldsFromCursor();
			
			ImageButton ib1=(ImageButton)findViewById(R.id.task_button_previuos);
			ib1.setClickable(true);
			
			ib1=(ImageButton)findViewById(R.id.task_button_next);
			ib1.setClickable(true);
			
			
			Button b1=(Button)findViewById(R.id.task_new_save);
			b1.setText(this.getString(R.string.create_new_task));
			b1.setOnClickListener(     new OnClickListener()
										{
								    		@Override
								    		public void onClick(View v) {
								    			setMode(State.NewTask);
								    		}
										}
	        );
			
			b1=(Button)findViewById(R.id.task_delete);
			b1.setOnClickListener(new DeleteTaskListener());
		break;
		}
	}
	
	private void fillFieldsFromCursor()
	{
		idtask=cur.getInt(0);
		
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
        	if(cur.getCount()!=0)
        	{
        		state=State.ViewingEditing;
	        	cur.moveToFirst();
	        	this.fillFieldsFromCursor();
	        	
	        	this.setMode(State.ViewingEditing);
        	}
        	else
        	{
        		this.setMode(State.NewTask);
        	}
        }
        catch(Exception e)
        {
        	Log.e(this.getString(R.string.app_name), e.toString());
        }
        
        
        ImageButton ib=(ImageButton)findViewById(R.id.task_button_previuos);
		ib.setOnClickListener(     new OnClickListener()
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
		
		ib=(ImageButton)findViewById(R.id.task_button_next);
		ib.setOnClickListener(     new OnClickListener()
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
		ib=(ImageButton)findViewById(R.id.task_time_button_dec);
		ib.setOnClickListener(     new OnClickListener()
									{
							    		@Override
							    		public void onClick(View v) {
							    			
							    			EditText dtime=(EditText)findViewById(R.id.task_desired_time_field);
							    			
							    			desired_time-=MINUTES_SCROLL_INTERVAL;
							    			dtime.setText(Integer.toString(desired_time));							    			
							    		}
									}
        );
		
		ib=(ImageButton)findViewById(R.id.task_time_button_inc);
		ib.setOnClickListener(     new OnClickListener()
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