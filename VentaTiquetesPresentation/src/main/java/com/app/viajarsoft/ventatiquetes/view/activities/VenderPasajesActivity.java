package com.app.viajarsoft.ventatiquetes.view.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.presenters.VenderPasajesPresenter;

public class VenderPasajesActivity extends BaseActivity<VenderPasajesPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender_pasajes);
        loadToolbar();
    }

    private void loadToolbar() {
        Toolbar toolbar = (Toolbar)this.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_vender_pasajes);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
