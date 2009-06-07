package org.chudsaviet.GPSTasks;

import android.app.*;
import android.database.*;
import android.database.sqlite.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PlaceActivity extends Activity {
	
	static int RADIUS_SCROLL_INTERVAL=50;
	static int RADIUS_DEFAULT=100;
	
	enum State {ViewingEditing,AddingMode};
	
	SQLiteDatabase db;
	Cursor cur;
	int radius=RADIUS_DEFAULT;//in minutes
	int x=0;
	int y=0;
	private State state;
	int idplace;
	
	private class InsertNewPlaceListener implements OnClickListener
	{
		//@Override
		public void onClick(View v) {
					Log.d(getString(R.string.app_name),"saving new place");
					
					EditText name=(EditText)findViewById(R.id.place_name_field);
					EditText comments=(EditText)findViewById(R.id.place_comments_field);	
					EditText radius=(EditText)findViewById(R.id.place_radius_field);
					EditText x_field=(EditText)findViewById(R.id.place_x_field);
					EditText y_field=(EditText)findViewById(R.id.place_y_field);
					
					
					
					String query="INSERT INTO place (name,comments,radius,x,y) VALUES ('"+
						name.getText().toString()+"','"+
						comments.getText().toString()+"',"+
						radius.getText().toString()+",0,0);";
					
					try{
						db.execSQL(query);
					}
					catch(SQLException e)
					{
						Log.e(getString(R.string.app_name), e.toString());
					}
					
					requery();	
		}
		
	}
	
	private class CancelNewPlaceListener implements OnClickListener
	{
		//@Override
		public void onClick(View v) {			
					requery();	
		}
		
	}
	
	private class DeletePlaceListener implements OnClickListener
	{
		//@Override
		public void onClick(View v) {
					
					Log.d(getString(R.string.app_name),"deleting place");
					
					String query="DELETE FROM place WHERE idplace="+idplace;
					
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
		Log.d(getString(R.string.app_name), "PlaceActivity: requery()");
		
		if(cur!=null) cur.close();
		
		try{
			cur=db.query("place", null, null, null, null, null, "idplace");
		}
		catch(SQLException e)
		{
			Log.e(getString(R.string.app_name), e.toString());
		}
		
		if(cur.getCount()!=0)
    	{
    		state=State.ViewingEditing;
        	cur.moveToFirst();
        	this.fillFieldsFromCursor();
        	
        	this.setState(State.ViewingEditing);
    	}
    	else
    	{
    		this.setState(State.AddingMode);
    	}
	}
	
	private void setState(State state)
	{
		this.state=state;
		
		switch (state) {
		case AddingMode:
			
			Button b=(Button)findViewById(R.id.place_new_save);
			b.setText(this.getString(R.string.save));
			b.setOnClickListener(new InsertNewPlaceListener());
			
			b=(Button)findViewById(R.id.place_delete);
			b.setText(this.getString(R.string.cancel));
			b.setOnClickListener(new CancelNewPlaceListener());
			
			ImageButton ib=(ImageButton)findViewById(R.id.place_button_previous);
			ib.setClickable(false);
			
			ib=(ImageButton)findViewById(R.id.place_button_next);
			ib.setClickable(false);
			
			EditText name=(EditText)findViewById(R.id.place_name_field);
			name.setText(this.getString(R.string.enter_name_of_the_place));
			
			EditText comments=(EditText)findViewById(R.id.place_comments_field);
			comments.setText("");
			
			EditText x=(EditText)findViewById(R.id.place_x_field);
			x.setText("");
			
			EditText y=(EditText)findViewById(R.id.place_x_field);
			y.setText("");
			
			EditText radius=(EditText)findViewById(R.id.place_radius_field);
			radius.setText(Integer.toString(RADIUS_DEFAULT));
			
			break;
		case ViewingEditing:
			ImageButton ib1=(ImageButton)findViewById(R.id.place_button_previous);
			ib1.setClickable(true);
			
			ib1=(ImageButton)findViewById(R.id.place_button_next);
			ib1.setClickable(true);
			
			
			Button b1=(Button)findViewById(R.id.place_new_save);
			b1.setText(this.getString(R.string.create_new_place));
			b1.setOnClickListener(     new OnClickListener()
										{
								    		//@Override
								    		public void onClick(View v) {
								    			setState(State.AddingMode);
								    		}
										}
	        );
			
			b1=(Button)findViewById(R.id.place_delete);
			b1.setOnClickListener(new DeletePlaceListener());
			b1.setText(getString(R.string.delete_place));
		break;
		}
	}
	
	private boolean isThereAnyChanges()
	{		
		EditText name=(EditText)findViewById(R.id.place_name_field);
		if(!name.getText().toString().equals(cur.getString(1))) return true;
		
		EditText comments=(EditText)findViewById(R.id.place_comments_field);
		if(!comments.getText().toString().equals(cur.getString(5))) return true;
		
		EditText radius=(EditText)findViewById(R.id.place_radius_field);
		if(cur.getInt(4)!=Integer.parseInt(radius.getText().toString())) return true;
		
		EditText xf=(EditText)findViewById(R.id.place_x_field);
		if(cur.getInt(2)!=Integer.parseInt(xf.getText().toString())) return true;
		
		EditText yf=(EditText)findViewById(R.id.place_y_field);
		if(cur.getInt(3)!=Integer.parseInt(yf.getText().toString())) return true;
		
		return false;
	}
	
	private void fillFieldsFromCursor()
	{
		idplace=cur.getInt(0);
		
		EditText name=(EditText)findViewById(R.id.place_name_field);
		name.setText(cur.getString(1));
		
		EditText comments=(EditText)findViewById(R.id.place_comments_field);
		comments.setText(cur.getString(5));
		
		EditText radius_f=(EditText)findViewById(R.id.place_radius_field);
		radius=cur.getInt(4);
		radius_f.setText(Integer.toString(cur.getInt(4)));
		
		EditText xf=(EditText)findViewById(R.id.place_x_field);
		x=cur.getInt(2);
		xf.setText(Integer.toString(cur.getInt(2)));
		
		EditText yf=(EditText)findViewById(R.id.place_y_field);
		y=cur.getInt(3);
		yf.setText(Integer.toString(cur.getInt(3)));
	}
	
	private void updatePlaceFromForm()
	{
		Log.d(getString(R.string.app_name),"updating place "+idplace);
		
		EditText name=(EditText)findViewById(R.id.place_name_field);
		EditText comments=(EditText)findViewById(R.id.place_comments_field);
		
		
		String query="UPDATE place SET name='"+
			name.getText().toString()+"',comments='"+
			comments.getText().toString()+"',radius="+
			Integer.toString(radius)+",x="+
			Integer.toString(x)+",y="+
			Integer.toString(y)+
			" WHERE idplace="+idplace;
		
		try{
			db.execSQL(query);
			this.requery();
		}
		catch(SQLException e)
		{
			Log.e(getString(R.string.app_name), e.toString());
		}
	}
	
	//@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_activity);
        
        try{
        	DatabaseHelper dbh=new DatabaseHelper(this);
        	db=dbh.getWritableDatabase();
        }
        catch(Exception e)
        {
        	Log.e(this.getString(R.string.app_name), e.toString());
        }
        
        requery();
        
        ImageButton ib=(ImageButton)findViewById(R.id.place_button_previous);
		ib.setOnClickListener(     new OnClickListener()
									{
							    		//@Override
							    		public void onClick(View v) {
							    			if(isThereAnyChanges())
						    					updatePlaceFromForm();
							    			
							    			if(!cur.isFirst()){
							    				cur.moveToPrevious();
							    				fillFieldsFromCursor();						    			
							    			}
							    		}
									}
        					);
		
		ib=(ImageButton)findViewById(R.id.place_button_next);
		ib.setOnClickListener(     new OnClickListener()
									{
							    		//@Override
							    		public void onClick(View v) {
							    			if(isThereAnyChanges())
						    					updatePlaceFromForm();
							    			
							    			if(!cur.isLast()){
							    				cur.moveToNext();
							    				fillFieldsFromCursor();
							    			}
							    		}
									}
        );
		ib=(ImageButton)findViewById(R.id.place_radius_dec);
		ib.setOnClickListener(     new OnClickListener()
									{
							    		//@Override
							    		public void onClick(View v) {
							    			
							    			EditText radius_f=(EditText)findViewById(R.id.place_radius_field);
							    			
							    			radius-=RADIUS_SCROLL_INTERVAL;
							    			radius_f.setText(Integer.toString(radius));							    			
							    		}
									}
        );
		
		ib=(ImageButton)findViewById(R.id.place_radius_inc);
		ib.setOnClickListener(     new OnClickListener()
									{
							    		//@Override
							    		public void onClick(View v) {
							    			
							    			EditText radius_f=(EditText)findViewById(R.id.place_radius_field);
							    			
							    			radius+=RADIUS_SCROLL_INTERVAL;
							    			radius_f.setText(Integer.toString(radius));							    			
							    		}
									}
        );
    }
}