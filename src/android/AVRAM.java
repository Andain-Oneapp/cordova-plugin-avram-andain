package cordova.plugin.avram;

import android.app.ActivityManager;
import android.content.Context;
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

        // TODO -> Usar Response para response
        // Response response = new Response();
        JSONObject result = new JSONObject();
        try {
            Context context = this.cordova.getActivity().getApplicationContext();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            result.put("availableMemory", memoryInfo.availMem / 1048576L);
            callbackContext.success(result);
        } catch (Exception ex) {
            result.put("success", false);
            result.put("error", ex.toString());
            callbackContext.error(result);
        }
    }
}