package com.app.viajarsoft.ventatiquetes.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.presenters.VenderPasajesPresenter;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.IVenderPasajesView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Ruta;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoBus;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class VenderPasajesActivity extends BaseActivity<VenderPasajesPresenter> implements IVenderPasajesView {

    private Spinner venderPasajes_spinnerTipoBus;
    private Spinner venderPasajes_spinnerRutas;

    private UsuarioResponse usuarioResponse;
    private BussesAndRoutes bussesAndRoutes;

    private String tipoBusSelected;
    private String codigoRutaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender_pasajes);
        this.usuarioResponse = (UsuarioResponse) getIntent().getSerializableExtra(IConstants.USUARIO);
        this.bussesAndRoutes = (BussesAndRoutes) getIntent().getSerializableExtra(IConstants.BUSSES_AND_ROUTES);
        loadToolbar();
        loadViews();
        loadBussesSpinner();
        loadRoutesSpinner();
    }

    private void loadViews() {
        venderPasajes_spinnerTipoBus = (Spinner) findViewById(R.id.venderPasajes_spinnerTipoBus);
        venderPasajes_spinnerRutas = (Spinner) findViewById(R.id.venderPasajes_spinnerRutas);
    }

    private void loadRoutesSpinner() {
        List<String> stringList = getStringListFromRoutesList(bussesAndRoutes.getRutas());
        stringList.add(0, IConstants.SELECT);
        venderPasajes_spinnerRutas.setAdapter(getArrayAdapter(stringList));
        venderPasajes_spinnerRutas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    codigoRutaSelected = IConstants.EMPTY_STRING;
                } else {
                    codigoRutaSelected = bussesAndRoutes.getRutas().get(position - 1).getCodigo();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void loadBussesSpinner() {
        List<String> stringList = getStringListFromBussesList(bussesAndRoutes.getTiposBuses());
        stringList.add(0, IConstants.SELECT);
        venderPasajes_spinnerTipoBus.setAdapter(getArrayAdapter(stringList));
        venderPasajes_spinnerTipoBus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    tipoBusSelected = IConstants.EMPTY_STRING;
                } else {
                    tipoBusSelected = bussesAndRoutes.getTiposBuses().get(position - 1).getTipo();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private ArrayAdapter<String> getArrayAdapter(List<String> stringList) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return arrayAdapter;
    }

    private List<String> getStringListFromRoutesList(ArrayList<Ruta> rutas) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < rutas.size(); i++) {
            stringList.add(rutas.get(i).getDescripcion());
        }
        return stringList;
    }

    private List<String> getStringListFromBussesList(ArrayList<TipoBus> tiposBuses) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < tiposBuses.size(); i++) {
            stringList.add(tiposBuses.get(i).getDescripcion());
        }
        return stringList;
    }

    private void loadToolbar() {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        toolbar.setTitle(usuarioResponse.getNombreOficina() + " - " + getString(R.string.title_vender_pasajes));
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void startVenderPasajesActivity(BussesAndRoutes bussesAndRoutes) {

    }
}
