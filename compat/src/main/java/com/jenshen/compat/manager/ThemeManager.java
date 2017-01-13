package com.jenshen.compat.manager;


import android.content.Context;
import android.support.annotation.StyleRes;
import android.util.TypedValue;

import com.jenshen.compat.R;

public class ThemeManager {

    @StyleRes
    public static int getAlertDialogTheme(Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.jenshenSoft_compat_alertDialogStyle, value, true);
        return value.data;
    }
}
