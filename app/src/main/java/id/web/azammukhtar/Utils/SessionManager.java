package id.web.azammukhtar.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {
    private static final String TAG = "SessionManager";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private static SessionManager singleTonInstance = null;
    private static final int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "SessionManager";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String USER_TOKEN = "userToken";

    private static final String USER_NAME = "userName";
    private static final String USER_PHONE = "userPhone";

    public static SessionManager getInstance() {
        if (singleTonInstance == null) {
            singleTonInstance = new SessionManager(MyApplication.getInstance().getApplicationContext());
        }
        return singleTonInstance;
    }

    private SessionManager(Context context) {
        super();
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
    }


    public void setLogin(boolean isLoggedIn, String userToken) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString(USER_TOKEN, userToken);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public void setData(String name, String phone) {
        editor.putString(USER_NAME, name);
        editor.putString(USER_PHONE, phone);
        editor.commit();
    }

    public String getUserName(){
        return pref.getString(USER_NAME, null);
    }
    public String getUserPhone(){
        return pref.getString(USER_PHONE, null);
    }


    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getUserToken(){
        return pref.getString(USER_TOKEN, null);
    }

    public void clearData() {
        editor.clear().commit();
    }

}
