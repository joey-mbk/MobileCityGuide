package com.mobilecityguide.gateways.SQL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import android.database.Cursor;

import com.mobilecityguide.exceptions.RecordSetException;
import com.mobilecityguide.gateways.RecordSet;

public class SQLSet implements RecordSet {

	private Cursor set;
	
	public SQLSet(Cursor cursor) {
		this.set = cursor;
	}

	@Override
	public boolean first() throws Exception {
		boolean r = false;
		try {
			r = this.set.moveToFirst();
		} catch (Exception e) {
			throw new Exception("Error while selecting the first element in RecordSet.");
		}
		return r;
	}

	@Override
	public boolean last() throws Exception {
		boolean r = false;
		try {
			r = this.set.moveToLast();
		} catch (Exception e) {
			throw new Exception("Error while selecting the last element in RecordSet.");
		}
		return r;
	}

	@Override
	public boolean previous() throws Exception {
		boolean r = false;
		try {
			r = this.set.moveToPrevious();
		} catch (Exception e) {
			throw new Exception("Error while selecting previous element in RecordSet.");
		}
		return r;
	}

	@Override
	public boolean next() throws Exception {
		boolean r = false;
		try {
			r = this.set.moveToNext();
		} catch (Exception e) {
			throw new Exception("Error while selecting next element in RecordSet.");
		}
		return r;
	}

	@Override
	public String getString(String column) throws Exception {
		String r = null;
		try {
			r = this.set.getString(this.set.getColumnIndex(column));
		} catch (Exception e) {
			throw new Exception("Error while fetching String element in RecordSet.");
		}
		return r;
	}

	@Override
	public short getShort(String column) throws Exception {
		short r = 0;
		try {
			r = this.set.getShort(this.set.getColumnIndex(column));
		} catch (Exception e) {
			throw new Exception("Error while fetching Short element in RecordSet.");
		}
		return r;
	}

	@Override
	public int getInt(String column) throws Exception {
		int r = 0;
		try {
			r = this.set.getInt(this.set.getColumnIndex(column));
		} catch (Exception e) {
			throw new Exception("Error while fetching Int element in RecordSet.");
		}
		return r;
	}

	@Override
	public double getDouble(String column) throws Exception {
		double r = 0;
		try {
			r = this.set.getDouble(this.set.getColumnIndex(column));
		} catch (Exception e) {
			throw new Exception("Error while fetching Double element in RecordSet.");
		}
		return r;
	}

	@Override
	public String[] getList(String valCol, String orderCol) throws Exception {
		String[] list = new String[this.set.getCount()];
		int pos = this.set.getPosition(); // save the cursor position in the resultset
		first(); // set position to last so that the next row is the first row
		while (!this.set.isAfterLast()) {
			list[(getInt("priority")-1)] = getString("language"); // add the language at its priority index (minus one)
			next();
		}
		this.set.moveToPosition(pos); // get back to previous cursor position
		return list;
	}

	@Override
	public int size() {
		return this.set.getCount();
	}

}
