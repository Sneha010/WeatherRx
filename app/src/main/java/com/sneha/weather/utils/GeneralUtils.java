package com.sneha.weather.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sneha.weather.R;
import com.sneha.weather.model.FailureResponse;
import com.sneha.weather.widget.MyCustomFontBold;

import org.simpleframework.xml.core.ValueRequiredException;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/26/2016.
 */
public class GeneralUtils {



    public static String getFileFromAssets(Context context, String name) {
        String fileString = null;
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(name);
            int length = is.available();
            byte[] data = new byte[length];
            is.read(data);
            fileString = new String(data);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return fileString;
    }


    public static String toDisplayCase(String s, Context context) {

        final String ACTIONABLE_DELIMITERS = " '-/()"; // these cause the
        // character following
        // to be capitalized

        StringBuilder sb = new StringBuilder();
        boolean capNext = true;

        for (char c : s.toCharArray()) {
            c = (capNext) ? Character.toUpperCase(c) : Character.toLowerCase(c);
            sb.append(c);
            capNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0); // explicit
            // cast
            // not
            // needed
        }
        return sb.toString();
    }

    public static ProgressDialog progressDialog(Context context) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View layout = mInflater.inflate(R.layout.prog_dialog, null);
        ProgressDialog progDialog = new ProgressDialog(context);
        progDialog.show();
        progDialog.setContentView(layout);
        progDialog.setCancelable(true);
        progDialog.setCanceledOnTouchOutside(false);
        return progDialog;
    }

    public static boolean isEmpty(Object obj) {
        boolean result = true;
        if (obj != null) {
            if (obj instanceof String) {
                if (obj.toString().trim().length() != 0){
                    if (!obj.toString().equalsIgnoreCase("null"))
                        result = false;
                }
            } else if (obj instanceof List) {
                if (((List) obj).size() > 0)
                    result = false;
            } else if (obj instanceof Map) {
                if (((Map) obj).size() > 0)
                    result = false;
            }
        }

        return result;

    }

    public static void displayCustomToast(Context context, String message) {

        MyCustomFontBold textView = new MyCustomFontBold(context);
        Toast toast = Toast.makeText(context, "  "+message+"  ", Toast.LENGTH_SHORT);
        View toastView = toast.getView(); //This'll return the default View of the Toast.

        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
        toastMessage.setTextSize(16);
        toastMessage.setTextColor(context.getResources().getColor(R.color.white));
        toastMessage.setTypeface(textView.getDefaultTypeface());
        toastMessage.setGravity(Gravity.CENTER);
        toastMessage.setPadding(10,10,10,10);
        toastView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast_bg));
        toast.show();
    }

    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }


    public static boolean isOnline(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        if (info != null)
            return info.isConnected();
        else
            return false;
    }

    private static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static void saveStringDataInPref(Context context, String key,
                                            String data) {
        getPreferences(context).edit().putString(key, data).commit();
    }

    public static void saveIntDataInPref(Context context, String key, int data) {
        getPreferences(context).edit().putInt(key, data).commit();
    }

    public static void saveLongDataInPref(Context context, String key, long data) {

        getPreferences(context).edit().putLong(key, data).commit();
    }

    public static void saveBooleanDataInPref(Context context, String key,
                                             boolean data) {
        getPreferences(context).edit().putBoolean(key, data).commit();
    }

    public static String getSavedStringDataFromPref(Context context, String key) {

        return getPreferences(context).getString(key, null);
    }

    public static boolean getSavedBooleanDataFromPref(Context context,
                                                      String key) {
            return getPreferences(context).getBoolean(key, false);
    }

    public static int getSavedIntDataFromPref(Context context, String key) {
        return getPreferences(context).getInt(key, 0);
    }

    public static long getSavedLongDataFromPref(Context context, String key) {
        return getPreferences(context).getLong(key, 0);
    }


    public static String getScreenType(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        switch (metrics.densityDpi) {

            case DisplayMetrics.DENSITY_HIGH:
                return Constants.HDPI;
            case DisplayMetrics.DENSITY_XHIGH:
                return Constants.XHDPI;
            case DisplayMetrics.DENSITY_XXHIGH:
                return Constants.XXHDPI;
            case DisplayMetrics.DENSITY_XXXHIGH:
                return Constants.XXXHDPI;
            default:
                return Constants.XXHDPI;
        }
    }

    public static boolean isAlive(Context context) {
        try {
            if (context != null && !((Activity) context).isFinishing()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean notEmpty(Object obj) {
        boolean result = true;
        if (obj != null) {
            if (obj instanceof String) {

                if (obj.toString().trim().length() != 0
                        && !obj.toString().trim().equalsIgnoreCase("null"))
                    result = false;
            } else if (obj instanceof List) {
                if (((List) obj).size() > 0)
                    result = false;
            } else if (obj instanceof Map) {
                if (((Map) obj).size() > 0)
                    result = false;
            }
        }

        return !result;

    }

    public static OkHttpClient getLoggingClient(){

        //This interceptor enables logging for all the retrofit requests

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okClient = new OkHttpClient.Builder()
                .addInterceptor(logging);

        return okClient.build();

    }

    @NonNull
    public static FailureResponse buildFailureResponse(Throwable t) {

        int statusCode = FailureResponse.TAG_NOT_FOUND;

        if(t != null)
        if (t instanceof IOException) {
            statusCode = FailureResponse.NO_INTERNET;
        } else if (t instanceof ConnectException) {
            statusCode = FailureResponse.SERVER_ERROR;
        } else if (t instanceof ValueRequiredException) {
            statusCode = FailureResponse.TAG_NOT_FOUND;
        }

        return new FailureResponse(statusCode, "");
    }

    public static String fetchErrorFromCode(Context context , FailureResponse failureResponse){

        switch (failureResponse.getStatusCode()){
            case FailureResponse.NO_INTERNET :
                context.getString(R.string.no_internet_connectivity);
                break;
            case FailureResponse.SERVER_ERROR:
                context.getString(R.string.server_error);
                break;
            case FailureResponse.TAG_NOT_FOUND:
                context.getString(R.string.tag_not_found);
                break;
            default:
                context.getString(R.string.server_error);
        }

        return context.getString(R.string.server_error);
    }

    public static float convertTemp(int celcius) {
        // TODO Auto-generated method stub
        double faran;
        int x;
        faran = (1.8 * celcius) + 32;
        x = (int) Math.round(faran);
        return x;
    }

    // Converts to fahrenheit
    /*public static int convertTemp(int celsius) {
        return ((celsius * 9) / 5) + 32;
    }
*/

}
