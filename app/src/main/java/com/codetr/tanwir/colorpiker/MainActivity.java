/*
 * Copyright (c) 2016. Tanwir. All Rights Reserver.
 * <p>
 * Save to the extent permitted by law, you may not use,copy,modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Tanwir.
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package com.codetr.tanwir.colorpiker;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import cn.qqtheme.framework.picker.ColorPicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by Tanwir on 06/09/2017.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AdView mAdView;
    private AppCompatButton btnCopy, btnText, btnBackGround;
    private EditText txtInput;
    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(" Comments Style");
        }
        
        MobileAds.initialize(getApplicationContext(), getString(R.string.AppQuizTR));
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        btnText = (AppCompatButton) findViewById(R.id.btnText);
        btnBackGround = (AppCompatButton) findViewById(R.id.btnBackGround);
        txtInput = (EditText) findViewById(R.id.txtInput);
        btnCopy = (AppCompatButton) findViewById(R.id.btnCopy);

        btnText.setOnClickListener(this);
        btnBackGround.setOnClickListener(this);
        btnCopy.setOnClickListener(this);
        txtInput.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnText:
                ColorPicker("fg");
                break;
            case R.id.btnBackGround:
                ColorPicker("bg");
                break;
            case R.id.btnCopy:
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copy", txtInput.getText());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(this, "Copy", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void ColorPicker(final String COLOR) {
        ColorPicker picker = new ColorPicker(this);
        picker.setInitColor(getResources().getColor(R.color.colorPrimary));
        picker.setCancelTextColor(getResources().getColor(R.color.colorPrimary));
        picker.setSubmitTextColor(getResources().getColor(R.color.colorPrimary));
        picker.setOnColorPickListener(new ColorPicker.OnColorPickListener() {
            @Override
            public void onColorPicked(int pickedColor) {
                color = ConvertUtils.toColorString(pickedColor);
                txtInput.setText(txtInput.getText() + "<" + COLOR + "=" + color + ">");
            }
        });
        picker.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_comments) {
            startActivity(new Intent(MainActivity.this, CommentsAll.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
