package cordova.plugin.avram;

import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Avram extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
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
        Log.i("AVRAM", action);
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