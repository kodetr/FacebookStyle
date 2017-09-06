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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanwir on 06/09/2017.
 */
public class CommentsAll extends AppCompatActivity {

    private AdView mAdView;
    private GridView gridview;
    private AdapterComments adapterComments;
    private Comments mComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentsall);

        MobileAds.initialize(getApplicationContext(), getString(R.string.AppQuizTR));
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Comments Other");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        gridview = (GridView) findViewById(R.id.gridview);
        showCommentsAll();
    }

    public void showCommentsAll() {
        configGridView();
        List<Comments> commentsList = getCommentsAll();

        for (int i = 0; i < commentsList.size(); i++) {
            mComments = commentsList.get(i);
            adapterComments.addComments(mComments);
        }
    }

    public void configGridView() {
        adapterComments = new AdapterComments(this);
        gridview.setAdapter(adapterComments);
    }


    public static List<Comments> getCommentsAll() {
        List<Comments> wordList = new ArrayList<>();
        String[][] data = {{"Selamat Pagi", "@[125406167619573:]"},
                {"Komentar Panjang", "@[347283465352931:]"},
                {"Komentar Panjang 2","@[312301218885168:]"},
                {"Kata Gombal",      "@[413484778707007:]"},
                {"Puisi Cinta",      "@[198627400271515:]"},
                {"Cerita Lucu",      "@[446435198740215:]"},
                {"Gitar",            "@[173009899510754:]"},
                {"Pot Love",         "@[142080565939003:]"},
                {"Puisi Keraguanku", "@[568525236507738:]"},
                {"Puisi Kepergianmu","@[308039315964393:]"},
                {"Jempoler Hadir",   "@[307438076032959:]"},
                {"Jempoler Salkomsel","@[395752243833522:]"},
                {"Terus gw harus bilang WOW gitu??","@[449763618416208:]"},
                {"Lirik Lagu Cakra Khan","@[298947036883605:]"},
                {"Dalam Diamku Ada Ketulusan Ku","@[492860104070761:]"},
                {"Tak Mampu Tanpamu","@[298799600231957:]"},
                {"Tak Ingin Lupakamu","@[239824816147628:]"},
                {"Like","@[222114781256465:]"},
                {"Add Me","@[573186196030183:]"},
                {"Ulang Tahun","@[277233245642912:]"},
                {"Status Cinta","@[474704252540326:]"},
                {"Status Cinta 2","@[565896976757519:]"},
                {"Status Kasih","@[486563144690327:]"},
                {"Status Kasih 2","@[14233965923656:]"},
                {"Status Rindu","@[193452200784660:]"},
                {"Status Love","@[350643515013566:]"},
                {"Status Miss","@[155121134611981:]"},
                {"Status Ngancem","@[251620911614956:]"},

        };

        for (int i = 0; i < data.length; i++) {
            Comments setComnet = new Comments(String.valueOf(data[i][0]), String.valueOf(data[i][1]));
            wordList.add(setComnet);
        }
        return wordList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_POWER) {
            startActivity(new Intent(CommentsAll.this, MainActivity.class));
            CommentsAll.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
