package com.hwdax.cordova.plugin;
// The native Toast API
import android.widget.Toast;
// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.cordova.*;
import android.content.Intent;
import android.content.Context;
import android.util.Log;


public class CitiGuidePlugin extends CordovaPlugin {

  @Override
  public boolean execute(
    String action, 
    JSONArray args,
    final CallbackContext callbackContext) 
  {

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                callbackContext.sendPluginResult(setAndroidPreferences(args));
            }
        });

	return true;

  }  

private static final String TAG = "CitiGuideTAG";

private PluginResult setAndroidPreferences(JSONArray args) {

      try {

        JSONObject options = args.getJSONObject(0);
        JSONArray jArray = options.getJSONArray("points");

	String s=Integer.toString(jArray.length())+" ";

for(int i=0; i<jArray.length(); i++){
    JSONObject json_data = jArray.getJSONObject(i);
    s=s+" "+json_data.optString("Lat","")+" "+json_data.optString("Lon","");
}

String cm = "cgcmd delroute setroute "+s+" 361 -1 100000";

Log.v(TAG, "s:" + s);
Log.v(TAG, "cmd:" + s);
        
		 Context context = cordova.getActivity().getApplicationContext();
	            Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setAction(Intent.ACTION_SEND);
				intent.setType("vnd.android.cursor.item/vnd.net.probki.cityguide.cmd");
				intent.setPackage("cityguide.probki.net");								
				intent.putExtra(Intent.EXTRA_TEXT,cm);

		final CordovaInterface mycordova = cordova;
		final CordovaPlugin plugin = this;

		mycordova.startActivityForResult(plugin, intent, 0);	            
            return new PluginResult(PluginResult.Status.OK);                     
	} catch (JSONException e) {
	PluginResult result = new PluginResult(PluginResult.Status.ERROR, "{'err':"+e.getMessage()+"}");
            return result;  
      }
}
}