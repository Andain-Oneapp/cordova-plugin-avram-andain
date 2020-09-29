package cordova.plugin.beaconsff;

import org.altbeacon.beacon.Beacon;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import java.util.UUID;

import io.ionic.starter.MainActivity;

public class Beaconsff extends CordovaPlugin {

    private static Context CONTEXT = null;
    public static Intent MONITORING_INTENT = null;
    public static Intent ADVERTISEMENT_INTENT = null;

    private static final int PERMISSION_REQUEST_FINE_LOCATION = 1;
    private static final int PERMISSION_REQUEST_BACKGROUND_LOCATION = 2;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Beaconsff.CONTEXT = this.cordova.getActivity().getBaseContext();
    }

    /**
     * Ejecuta un método a partir de la acción que entra por parámetro
     *
     * @param action          The action to execute.
     * @param args            The exec() arguments.
     * @param callbackContext The callback context used when calling back into JavaScript.
     * @return
     * @throws JSONException
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getAvailableRAM")) {
            this.getAvailableRAM(callbackContext);
            return true;
        }
        return false;
    }

    /**
     * Retorna la cantidad de memoria RAM disponible en MB 
     * en el momento que se llama esta función
     *
     * @param title
     * @param callbackContext
     * @throws JSONException
     */
    private void getAvailableRAM(CallbackContext callbackContext) throws JSONException {
        JSONObject result = new JSONObject();
        try {
            Runtime runtime = Runtime.getRuntime();   
                result.put("success", true);
                result.put("freeMemory", (runtime.freeMemory() / 1048576L));


                callbackContext.success(result);    

        } catch (Exception ex) {
            result.put("success", false);
            result.put("error", ex.toString());
            callbackContext.error(result);
        }
    }
}
