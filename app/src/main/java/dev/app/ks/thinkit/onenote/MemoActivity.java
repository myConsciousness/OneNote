package dev.app.ks.thinkit.onenote;

import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;
import dev.app.ks.thinkit.onenote.framework.BaseActivity;
import dev.app.ks.thinkit.onenote.framework.Logger;
import dev.app.ks.thinkit.onenote.framework.StringChecker;
import dev.app.ks.thinkit.onenote.model.MemoHolder;
import dev.app.ks.thinkit.onenote.model.MemoInformation;

/**
 * ======================================================================
 * Project Name    : OneNote
 * File Name       : MemoActivity.java
 * Encoding        : UTF-8
 * Creation Date   : 2019/12/04
 * <p>
 * Copyright © 2019 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be
 * reproduced or used in any manner whatsoever.
 * ======================================================================
 * <p>
 * One Noteの画面機能を実装したActivityクラスです。
 * 当該クラスではメモ機能に必要な各機能を実装します。
 *
 * @author Kato Shinya
 * @version 1.0
 * @since 1.0
 */
public final class MemoActivity extends BaseActivity {

    /**
     * クラス名。
     */
    private static final String TAG = MemoActivity.class.getSimpleName();

    /**
     * One Noteの新規メモの名前。
     */
    private static final String NEW_MEMO_NAME = "New Memo";

    /**
     * メモ名登録ダイアログのオブジェクト。
     */
    private AlertDialog registerMemoNameDialog;

    /**
     * 当該アクティビティのコンストラクタ。
     * 基底クラスにレイアウトIDを渡すことで画面を構築します。
     */
    public MemoActivity() {
        super(R.layout.activity_memo);
    }

    @Override
    protected void initializeView() {
        String methodName = "initializeView";
        Logger.Debug.write(TAG, methodName, "START");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(NEW_MEMO_NAME);
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
                EditText editTextMemo = findViewById(R.id.one_note_input_field);
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
        String methodName = "buildRegisterMemoNameDialog";
        Logger.Debug.write(TAG, methodName, "START");

        final View viewDialog = getLayoutInflater().inflate(R.layout.dialog_enter_memo_name, null);

        final Button buttonRegister = viewDialog.findViewById(R.id.button_register_memo_name);
        final Button buttonCancel = viewDialog.findViewById(R.id.button_cancel_memo_name);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextMemoName = viewDialog.findViewById(R.id.input_field_memo_name);
                String memoName = editTextMemoName.getText().toString();

                if (StringChecker.isEffectiveString(memoName)) {
                    MemoHolder memoHolder = new MemoHolder(memoName, memo);
                    MemoInformation memoInformation = MemoInformation.getInstance(MemoActivity.this);

                    Toolbar toolbar = findViewById(R.id.toolbar);
                    String memoTitle = toolbar.getTitle().toString();

                    if (MemoActivity.NEW_MEMO_NAME.equals(memoTitle)) {
                        memoInformation.insert(memoHolder);
                    } else {
                        memoInformation.replace(memoHolder);
                    }

                    toolbar.setTitle(memoName);
                    Snackbar.make(v, "Registered successfully", Snackbar.LENGTH_LONG).show();

                    editTextMemoName.setEnabled(false);
                    buttonCancel.setText(R.string.button_leave);
                    LinearLayout linearLayoutButton = viewDialog.findViewById(R.id.linear_layout_button_memo_name_dialog);
                    linearLayoutButton.removeView(buttonRegister);
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
        registerMemoNameDialog.show();

        Logger.Debug.write(TAG, methodName, "END");
    }
}
