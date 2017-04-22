package osdchack.com.locmon.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import osdchack.com.locmon.Models.Guard;

/**
 * Created by DravitLochan on 22-04-2017.
 */

public class GuardDetails {
    SharedPreferences pref;
    Context context;
    SharedPreferences.Editor editor;


    int PRIVATE_MODE = 0;
    private static final String ID = "Id";
    private static final String PREF_NAME = "LocMon";
    private static final String USER_NAME = "UserName";
    private static final String PASSWORD = "password";
    private static final String IS_GUARD_SET = "IsGuardSet";

    public GuardDetails(Context c) {
        this.context = c;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setGuard(Guard guard) {
        editor.putString(ID, "1");
        editor.putString(USER_NAME, guard.getUser_name());
        editor.putString(PASSWORD, guard.getPassword());
        editor.commit();
    }

    public Guard getGuard() {
        Guard guard = new Guard(pref.getString(ID, null), pref.getString(USER_NAME, null), pref.getString(PASSWORD, null));
        return guard;
    }

    public boolean getIsGuardSet() {
        return pref.getBoolean(IS_GUARD_SET, false);
    }

    public void setIsGuardSet(boolean set) {
        editor.putBoolean(IS_GUARD_SET, set);
        editor.commit();
    }
}
