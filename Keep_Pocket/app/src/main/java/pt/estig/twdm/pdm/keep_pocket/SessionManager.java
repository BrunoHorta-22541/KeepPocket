package pt.estig.twdm.pdm.keep_pocket;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class SessionManager {

    private SessionManager() {

    }


    private static SharedPreferences sharedPreferences;

    private static SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("sessionPreferences", Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static void saveSession(Context context, String username, boolean rememberMe, long userId) {
        getSharedPreferences(context)
                .edit()
                .putString("username", username)
                .putLong("userId", userId)
                .putBoolean("rememberMe", rememberMe).apply();
    }

    public static pt.estig.twdm.pdm.keep_pocket.Session getActiveSession(Context context) {
        String username = getSharedPreferences(context).getString("username", null);
        long userId = getSharedPreferences(context).getLong("userId", 0);
        return new pt.estig.twdm.pdm.keep_pocket.Session(username,userId);
    }

    public static boolean sessionExists(Context context) {
        String loggedInUser = getSharedPreferences(context).getString("username", null);
        return loggedInUser != null;
    }

    public static boolean persistedSession(Context context) {
        return getSharedPreferences(context).getBoolean("rememberMe", false);
    }

    public static void clearSession(Context context) {
        getSharedPreferences(context).edit().clear().apply();
    }
}
