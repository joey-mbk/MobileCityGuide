package com.mobilecityguide.gateways.SQL;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.*;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobilecityguide.exceptions.GatewayException;

public class SQLGateway extends SQLiteOpenHelper {
	
	private Context context;
	public static SQLiteDatabase db;
	
	public SQLGateway(Context context) {
		super(context, "mobilecityguide.db", null, 1);
		this.context = context; // context is needed for accessing assets (see below)
		SQLGateway.db = getReadableDatabase(); // open the database only once and store its object
	}

	public SQLiteDatabase writeMode() {
		db = getWritableDatabase();
		return db;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		executeSQLScript(db, "createdb.sql");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
	
	
	/* Next method is inspired from http://www.drdobbs.com/database/232900584?pgno=1 */
	private void executeSQLScript(SQLiteDatabase db, String file) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte buf[] = new byte[1024];
		int len;
		AssetManager assetManager = context.getAssets();
		InputStream inputStream = null;
		try {
			inputStream = assetManager.open(file);
			
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}
			
			outputStream.close();
			inputStream.close();
			
			String[] createScript = outputStream.toString().split("\n");
			for (int i = 0; i < createScript.length; i++) {
				String sqlStatement = createScript[i].trim();
				if (sqlStatement.length() > 0) {
					System.out.println(sqlStatement);
					db.execSQL(sqlStatement);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
