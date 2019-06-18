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


private PluginResult setAndroidPreferences(JSONArray args) {

      try {
        JSONObject options = args.getJSONObject(0);

        String flat = options.optString("flat","0");
        String flon = options.optString("flon","0");
        String tlat = options.optString("tlat","0");
        String tlon = options.optString("tlon","0");


		 Context context = cordova.getActivity().getApplicationContext();
	            Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setAction(Intent.ACTION_SEND);
				intent.setType("vnd.android.cursor.item/vnd.net.probki.cityguide.cmd");
				intent.setPackage("cityguide.probki.net");								
				intent.putExtra(Intent.EXTRA_TEXT,"cgcmd delroute setroute 2 "+flat+" "+flon+" "+tlat+" "+tlon+" view "+tlat+" "+tlon+" 361 -1 100000");

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