package com.origwood.liuxue.ui;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.FROYO;
import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;

import com.origwood.liuxue.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
/**
 * Progress dialog 自定义进度条
 */
public class CustomProgressDialog extends ProgressDialog {

    /**
     * Create progress dialog
     *
     * @param context
     * @param resId
     * @return dialog
     */
    public static AlertDialog create(Context context, int resId) {
        return create(context, context.getResources().getString(resId));
    }

    /**
     * Create progress dialog
     *
     * @param context
     * @param message
     * @return dialog
     */
    public static AlertDialog create(Context context, CharSequence message) {
        if (SDK_INT > FROYO) {
            ProgressDialog dialog;
            if (SDK_INT >= ICE_CREAM_SANDWICH)
                dialog = new CustomProgressDialog(context, message);
            else {
                dialog = new ProgressDialog(context);
                dialog.setInverseBackgroundForced(true);
            }
            dialog.setMessage(message);
            dialog.setIndeterminate(true);
            dialog.setProgressStyle(STYLE_SPINNER);
//            dialog.setIndeterminateDrawable(context.getResources().getDrawable(
//                    drawable.spinner));
            return dialog;
        } else {
            AlertDialog dialog = CustiomAlertDialog.create(context);
            dialog.setInverseBackgroundForced(true);
            View view = LayoutInflater.from(context).inflate(
                    R.layout.progress_dialog, null);
            ((TextView) view.findViewById(R.id.tv_loading)).setText(message);
            dialog.setView(view);
            return dialog;
        }
    }

    private CustomProgressDialog(Context context, CharSequence message) {
        super(context, THEME_HOLO_LIGHT);
    }
}
