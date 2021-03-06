
package com.example.pinly;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class outfits extends Activity implements OnClickListener {
	
	String HOT="True";
	String COLD="False";
	String MILD="False";
	String SNOW="False";
	String RAIN="False";
	String SUN="False";
	ImagesDB db;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outfits_activity);
        
        db = new ImagesDB(this);
        
        Button randoBut = (Button) findViewById(R.id.random);
        randoBut.setOnClickListener(this);

    }
    
    /*
     * These methods find the photo for the frames based on the path
     * */
    public void findTopFrame(String path){
    	
        ImageView image = (ImageView) findViewById(R.id.top);
        FileInputStream in;
        BufferedInputStream buf;
        
        try {
        	
            in = new FileInputStream(path);
            buf = new BufferedInputStream(in);
            Bitmap bMap = BitmapFactory.decodeStream(buf);
            image.setImageBitmap(bMap);
            if (in != null) {
                in.close();
            }
            if (buf != null) {
                buf.close();
            }
        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }
    }

    public void findMiddleFrame(String path){
        ImageView image = (ImageView) findViewById(R.id.middle);
        FileInputStream in;
        BufferedInputStream buf;
        try {
            in = new FileInputStream(path);
            buf = new BufferedInputStream(in);
            Bitmap bMap = BitmapFactory.decodeStream(buf);
            image.setImageBitmap(bMap);
            if (in != null) {
                in.close();
            }
            if (buf != null) {
                buf.close();
            }
        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }
    }

    public void findBottomFrame(String path){

        ImageView image = (ImageView) findViewById(R.id.bottom);
        FileInputStream in;
        BufferedInputStream buf;

        try {
            in = new FileInputStream(path);
            buf = new BufferedInputStream(in);
            Bitmap bMap = BitmapFactory.decodeStream(buf);
            image.setImageBitmap(bMap);
            if (in != null) {
                in.close();
            }
            if (buf != null) {
                buf.close();
            }
        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }
    }

	@Override
	public void onClick(View arg0) {
		String find = HOT+","+COLD+","+MILD+","+SNOW+","+SUN+","+RAIN;
		
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.random:
			Random rand = new Random();
			//Generate random top:
			Cursor cursor0 = db.queryDB(find+",top");
			Toast.makeText(getApplicationContext(), 
	                cursor0.getString(0), Toast.LENGTH_LONG).show();
			if(cursor0.getCount()!=0){
			int item0 = rand.nextInt(cursor0.getCount()-1);
			Toast.makeText(getApplicationContext(), 
	                "into curs", Toast.LENGTH_LONG).show();
			cursor0.moveToPosition(item0);
			String path0 = cursor0.getString(6);
			findTopFrame(path0);
			}
			
			//Generate random bottoms:
			Cursor cursor1 = db.queryDB(find+",bottom");
			int item1 = rand.nextInt()*cursor1.getCount();
			if(cursor0.getCount()==0) break;
			cursor1.moveToPosition(item1);
			String path1 = cursor1.getString(6);
			findMiddleFrame(path1);
			
			//Generate random shoes:
			Cursor cursor2 = db.queryDB(find+",shoes");
			int item2 = rand.nextInt()*cursor2.getCount();
			if(cursor0.getCount()==0) break;
			cursor2.moveToPosition(item2);
			String path2 = cursor2.getString(6);
			findBottomFrame(path2);
			
			break;
		}
	}



}
