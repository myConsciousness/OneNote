package dev.app.ks.thinkit.onenote.framework;

import android.app.ProgressDialog;
import android.content.Context;

public final class ProgressDialogHandler {

    private final Context context;
    private ProgressDialog progressDialog;

    public ProgressDialogHandler(Context context) {
        this.context = context;
    }

    public void showSpinnerDialog(String title, String message) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void showHolizontalDialog(String title, String message) {

        showHolizontalDialog(title, message, 0);
    }

    public void showHolizontalDialog(String title, String message, int max) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setMax(max);
        progressDialog.show();
    }

    public void setMax(int max) {

        progressDialog.setMax(max);
    }

    public void incrementProgress() {

        progressDialog.incrementProgressBy(1);
    }

    public void dismissDialog() {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
