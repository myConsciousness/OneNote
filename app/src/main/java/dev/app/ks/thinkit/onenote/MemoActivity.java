package dev.app.ks.thinkit.onenote;

import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;
import dev.app.ks.thinkit.onenote.framework.BaseActivity;
import dev.app.ks.thinkit.onenote.framework.Logger;
import dev.app.ks.thinkit.onenote.framework.StringChecker;
import dev.app.ks.thinkit.onenote.model.MemoHolder;
import dev.app.ks.thinkit.onenote.model.MemoInformation;

public final class MemoActivity extends BaseActivity {

    /**
     * クラス名。
     */
    private static final String TAG = MemoActivity.class.getSimpleName();

    /**
     * メモ名登録ダイアログのオブジェクト。
     */
    private AlertDialog registerMemoNameDialog;

    public MemoActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void initializeView() {
        String methodName = "initializeView";
        Logger.Debug.write(TAG, methodName, "START");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Logger.Debug.write(TAG, methodName, "END");
    }

    @Override
    protected void setListeners() {
        String methodName = "setListeners";
        Logger.Debug.write(TAG, methodName, "START");

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO 既にファイル名が設定されている場合は更新処理
                // TODO ファイル名が存在しない場合は新規登録処理
                EditText editTextMemo = view.findViewById(R.id.one_note_input_field);
                buildRegisterMemoNameDialog(editTextMemo.getText().toString());
            }
        });

        Logger.Debug.write(TAG, methodName, "END");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String methodName = "onCreateOptionsMenu";
        Logger.Debug.write(TAG, methodName, "START");

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        Logger.Debug.write(TAG, methodName, "END");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * メモ名登録ダイアログのオブジェクトを構築し画面上に出力します。
     *
     * @param memo 画面へ入力されたメモ。
     */
    protected void buildRegisterMemoNameDialog(final String memo) {

        final View viewDialog = getLayoutInflater().inflate(R.layout.dialog_enter_memo_name, null);

        if (registerMemoNameDialog == null) {
            Button buttonRegister = findViewById(R.id.button_register_memo_name);
            Button buttonCancel = findViewById(R.id.button_cancel_memo_name);

            buttonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText editTextMemoName = viewDialog.findViewById(R.id.input_field_memo_name);
                    String memoName = editTextMemoName.getText().toString();

                    if (StringChecker.isEffectiveString(memoName)) {
                        MemoHolder memoHolder = new MemoHolder(memoName, memo);
                        MemoInformation memoInformation = MemoInformation.getInstance(MemoActivity.this);
                        memoInformation.replace(memoHolder);

                        Snackbar.make(v, "Registered successfully", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(v, "Memo name is required", Snackbar.LENGTH_LONG).show();
                    }
                }
            });

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerMemoNameDialog.dismiss();
                }
            });

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setView(viewDialog);
            registerMemoNameDialog = dialogBuilder.create();
        }

        registerMemoNameDialog.show();
    }
}
