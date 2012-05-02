package com.mobilecityguide.gateways.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mobilecityguide.exceptions.RecordSetException;
import com.mobilecityguide.gateways.RecordSet;

public class SQLSet implements RecordSet {

	private ResultSet set;
	
	public SQLSet(ResultSet set) {
		this.set = set;
	}

	@Override
	public boolean first() throws RecordSetException, SQLException {
		boolean r = false;
		try {
			r = this.set.first();
		} catch (SQLException e) {
			throw new SQLException("Error while selecting the first element in RecordSet.");
		}
		return r;
	}

	@Override
	public boolean last() throws RecordSetException, SQLException {
		boolean r = false;
		try {
			r = this.set.last();
		} catch (SQLException e) {
			throw new SQLException("Error while selecting the last element in RecordSet.");
		}
		return r;
	}

	@Override
	public boolean previous() throws RecordSetException, SQLException {
		boolean r = false;
		try {
			r = this.set.previous();
		} catch (SQLException e) {
			throw new SQLException("Error while selecting previous element in RecordSet.");
		}
		return r;
	}

	@Override
	public boolean next() throws RecordSetException, SQLException {
		boolean r = false;
		try {
			r = this.set.next();
		} catch (SQLException e) {
			throw new SQLException("Error while selecting next element in RecordSet.");
		}
		return r;
	}

	@Override
	public String getString(String column) throws RecordSetException, SQLException {
		String r = null;
		try {
			r = this.set.getString(column);
		} catch (SQLException e) {
			throw new SQLException("Error while fetching String element in RecordSet.");
		}
		return r;
	}

	@Override
	public short getShort(String column) throws RecordSetException, SQLException {
		short r = 0;
		try {
			r = this.set.getShort(column);
		} catch (SQLException e) {
			throw new SQLException("Error while fetching Short element in RecordSet.");
		}
		return r;
	}

	@Override
	public int getInt(String column) throws RecordSetException, SQLException {
		int r = 0;
		try {
			r = this.set.getInt(column);
		} catch (SQLException e) {
			throw new SQLException("Error while fetching Int element in RecordSet.");
		}
		return r;
	}

	@Override
	public double getDouble(String column) throws RecordSetException, SQLException {
		double r = 0;
		try {
			r = this.set.getInt(column);
		} catch (SQLException e) {
			throw new SQLException("Error while fetching Double element in RecordSet.");
		}
		return r;
	}

	@Override
	public ArrayList<String> getList(String valCol, String orderCol) throws RecordSetException, SQLException {
		ArrayList<String> list = new ArrayList<String>();
		int pos = this.set.getRow(); // save the cursor position in the resultset
		first();
		while (next())
			list.add((getInt("priority")-1), getString("language")); // add the language at its priority index (minus one)
		this.set.absolute(pos); // get back to previous cursor position
		return list;
	}

}
