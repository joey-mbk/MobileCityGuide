package com.mobilecityguide.activity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.mobilecityguide.R;
import com.mobilecityguide.datamappers.MyOverLay;

public class RoutePath extends MapActivity{

	/** Called when the activity is first created. */ 

	MapView mapView; 

	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.directions);

		MapView myMapView = (MapView) findViewById(R.id.myMapView1);
		GeoPoint geoPoint = null;
		myMapView.setSatellite(false);

		/*String pairs[] = getDirectionData("trichy", "thanjavur");
		String[] lngLat = pairs[0].split(",");

		double src_lat = 50.670558; // the testing source 
		double src_long = 4.616135; 
		double dest_lat = 50.667969; // the testing destination 
		double dest_long = 4.591487; 

		// STARTING POINT
		GeoPoint startGP = new GeoPoint((int) (src_lat * 1E6), 
				(int) (src_long * 1E6)); 

		MapController myMC = myMapView.getController();
		geoPoint = startGP;
		myMC.setCenter(geoPoint);
		myMC.setZoom(15);
		myMapView.getOverlays().add(new MyOverLay(startGP, startGP, 2));

		// NAVIGATE THE PATH
		GeoPoint gp1;
		GeoPoint gp2 = startGP;

		for (int i = 1; i < pairs.length; i++) {
			lngLat = pairs[i].split(",");
			gp1 = gp2;
			// watch out! For GeoPoint, first:latitude, second:longitude
			gp2 = new GeoPoint((int) (dest_lat * 1E6), 
					(int) (dest_long * 1E6)); 
			myMapView.getOverlays().add(new MyOverLay(gp1, gp2, 2));
			Log.d("xxx", "pair:" + pairs[i]);
		}

		// END POINT
		myMapView.getOverlays().add(new MyOverLay(gp2, gp2, 2));

		myMapView.getController().animateTo(startGP);
		myMapView.setBuiltInZoomControls(true);
		myMapView.displayZoomControls(true);

	}
		 */
		MapView mapView = (MapView) findViewById(R.id.myMapView1); 
		double src_lat = 50.670558; // the testing source 
		double src_long = 4.616135; 
		double dest_lat = 50.667969; // the testing destination 
		double dest_long = 4.591487; 
		//init
		GeoPoint srcGeoPoint = new GeoPoint((int) (src_lat * 1E6), 
				(int) (src_long * 1E6)); 
		GeoPoint destGeoPoint = new GeoPoint((int) (dest_lat * 1E6), 
				(int) (dest_long * 1E6)); 
		//position actuel??!!
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		Location lastKnownLocation =
				locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
		/////

		DrawPath(srcGeoPoint, destGeoPoint, Color.GREEN, mapView); 

		mapView.getController().animateTo(srcGeoPoint); 
		myMapView.setBuiltInZoomControls(true);
		mapView.getController().setZoom(15); 


	} 

	public static String getUrl(double fromLat, double fromLon,
			double toLat, double toLon) {// connect to map web service
		StringBuffer urlString = new StringBuffer();
		urlString.append("http://maps.google.com/maps?f=d&hl=en");
		urlString.append("&saddr=");// from
		urlString.append(Double.toString(fromLat));
		urlString.append(",");
		urlString.append(Double.toString(fromLon));
		urlString.append("&daddr=");// to
		urlString.append(Double.toString(toLat));
		urlString.append(",");
		urlString.append(Double.toString(toLon));
		urlString.append("&ie=UTF8&0&om=0&output=kml");
		return urlString.toString();
	}

	@Override 
	protected boolean isRouteDisplayed() { 
		// TODO Auto-generated method stub 
		return false; 
	} 


	private void DrawPath(GeoPoint src,GeoPoint dest, int color, MapView mMapView01) 
	{ 
		// connect to map web service 
		StringBuilder urlString = new StringBuilder(); 
		urlString.append("http://maps.google.com/maps?f=d&hl=en"); 
		urlString.append("&saddr=");//from 
		urlString.append( Double.toString((double)src.getLatitudeE6()/1.0E6 )); 
		urlString.append(","); 
		urlString.append( Double.toString((double)src.getLongitudeE6()/1.0E6 )); 
		urlString.append("&daddr=");//to 
		urlString.append( Double.toString((double)dest.getLatitudeE6()/1.0E6 )); 
		urlString.append(","); 
		urlString.append( Double.toString((double)dest.getLongitudeE6()/1.0E6 )); 
		urlString.append("&ie=UTF8&0&om=0&output=kml"); 
		Log.d("xxx","URL="+urlString.toString()); 


		// get the kml (XML) doc. And parse it to get the coordinates(direction route). 
		Document doc = null; 
		HttpURLConnection urlConnection= null; 
		URL url = null; 
		try 
		{ 
			url = new URL(urlString.toString()); 
			urlConnection=(HttpURLConnection)url.openConnection(); 
			urlConnection.setRequestMethod("GET"); 
			urlConnection.setDoOutput(true); 
			urlConnection.setDoInput(true); 
			urlConnection.connect(); 

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db = dbf.newDocumentBuilder(); 
			doc = db.parse(urlConnection.getInputStream()); 

			if(doc.getElementsByTagName("GeometryCollection").getLength()>0) 
			{ 
				//String path1 = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getNodeName(); 
				String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue() ; 
				//System.out.print(path1);
				Log.d("xxx","path="+ path); 
				String [] pairs = path.split(" "); 
				String[] lngLat = pairs[0].split(","); // lngLat[0]=longitude lngLat[1]=latitude lngLat[2]=height 
				// src 
				GeoPoint startGP = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6)); 
				mMapView01.getOverlays().add(new MyOverLay(startGP,startGP,1)); 
				GeoPoint gp1; 
				GeoPoint gp2 = startGP; 
				TextView textView;
				NodeList nl = doc.getElementsByTagName("LineString");
				String pathConent = "";
				for (int s = 0; s < nl.getLength(); s++) {
					Node rootNode = nl.item(s);
					NodeList configItems = rootNode.getChildNodes();
					for (int x = 0; x < configItems.getLength(); x++) {
						Node lineStringNode = configItems.item(x);
						NodeList path3 = lineStringNode.getChildNodes();
						pathConent = path3.item(0).getNodeValue();
						
						textView = (TextView) findViewById(R.id.textView3);
						textView.setText(path3.item(0).getNodeName().intern());
						
						//System.out.print(pathConent);
					}
				}
				for(int i=1;i<pairs.length;i++) // the last one would be crash 
				{ 
					lngLat = pairs[i].split(","); 
					gp1 = gp2; 
					// watch out! For GeoPoint, first:latitude, second:longitude 
					gp2 = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6)); 
					mMapView01.getOverlays().add(new MyOverLay(gp1,gp2,3,color)); 
					Log.d("xxx","pair:" + pairs[i]); 
				} 
				mMapView01.getOverlays().add(new MyOverLay(dest,dest, 3, Color.RED)); // use the default color 
			} 
		} 
		catch (MalformedURLException e) 
		{ 
			e.printStackTrace(); 
		} 
		catch (IOException e) 
		{ 
			e.printStackTrace(); 
		} 
		catch (ParserConfigurationException e) 
		{ 
			e.printStackTrace(); 
		} 
		catch (SAXException e) 
		{ 
			e.printStackTrace(); 
		} 
	}

	private String[] getDirectionData(String srcPlace, String destPlace) {

		String urlString = "http://maps.google.com/maps?f=d&hl=en&saddr="
				+ srcPlace + "&daddr=" + destPlace
				+ "&ie=UTF8&0&om=0&output=kml";
		Log.d("URL", urlString);
		Document doc = null;
		HttpURLConnection urlConnection = null;
		URL url = null;
		String pathConent = "";
		try {

			url = new URL(urlString.toString());
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.connect();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(urlConnection.getInputStream());

		} catch (Exception e) {
		}

		NodeList nl = doc.getElementsByTagName("LineString");
		for (int s = 0; s < nl.getLength(); s++) {
			Node rootNode = nl.item(s);
			NodeList configItems = rootNode.getChildNodes();
			for (int x = 0; x < configItems.getLength(); x++) {
				Node lineStringNode = configItems.item(x);
				NodeList path = lineStringNode.getChildNodes();
				pathConent = path.item(0).getNodeValue();
			}
		}
		String[] tempContent = pathConent.split(" ");
		return tempContent;
	}
}


