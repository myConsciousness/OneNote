package dev.app.ks.thinkit.onenote.framework;

import android.app.ProgressDialog;
import android.content.Context;

public final class ProcessingDialogHandler {

    private ProgressDialog myProgressDialog;

    public void showProcessingDialog(Context context) {
        myProgressDialog = new ProgressDialog(context);
        myProgressDialog.setMessage("Processing...");
        myProgressDialog.show();
    }

    public void dismissDialog() {
        if (myProgressDialog != null && myProgressDialog.isShowing()) {
            myProgressDialog.dismiss();
        }
    }
}
