/*
 * 
 * Interface partielle d'un RecordSet. Seules les méthodes
 * qui nous sont utiles ont été prises en compte.
 * 
 */

package com.mobilecityguide.gateways;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mobilecityguide.exceptions.RecordSetException;

public interface RecordSet {
	public boolean first() throws RecordSetException, SQLException;
	public boolean last() throws RecordSetException, SQLException;
	public boolean previous() throws RecordSetException, SQLException;
	public boolean next() throws RecordSetException, SQLException;
	public String getString(String column) throws RecordSetException, SQLException;
	public short getShort(String column) throws RecordSetException, SQLException;
	public int getInt(String column) throws RecordSetException, SQLException;
	public double getDouble(String column) throws RecordSetException, SQLException;
	public ArrayList<String> getList(String valCol, String orderCol) throws RecordSetException, SQLException;
}
