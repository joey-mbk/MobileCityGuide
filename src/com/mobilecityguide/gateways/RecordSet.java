/*
 * 
 * Interface partielle d'un RecordSet. Seules les m�thodes
 * qui nous sont utiles ont �t� prises en compte.
 * 
 */

package com.mobilecityguide.gateways;

import java.util.ArrayList;

import com.mobilecityguide.exceptions.RecordSetException;

public interface RecordSet {
	public boolean first() throws RecordSetException, Exception;
	public boolean last() throws RecordSetException, Exception;
	public boolean previous() throws RecordSetException, Exception;
	public boolean next() throws RecordSetException, Exception;
	public String getString(String column) throws RecordSetException, Exception;
	public short getShort(String column) throws RecordSetException, Exception;
	public int getInt(String column) throws RecordSetException, Exception;
	public double getDouble(String column) throws RecordSetException, Exception;
	public String[] getList(String valCol, String orderCol) throws RecordSetException, Exception;
	public int size();
}
