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
import com.app.viajarsoft.ventatiquetes.dependency_injection.DomainModule;
import com.app.viajarsoft.ventatiquetes.presenters.VenderPasajesPresenter;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.CustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.IVenderPasajesView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Ruta;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoBus;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoTiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Viaje;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class VenderPasajesActivity extends BaseActivity<VenderPasajesPresenter> implements IVenderPasajesView {

    private Spinner venderPasajes_spinnerTipoBus;
    private Spinner venderPasajes_spinnerRutas;
    private Spinner venderPasajes_spinnerTiposTiquetes;

    private UsuarioResponse usuarioResponse;
    private BussesAndRoutes bussesAndRoutes;

    private String tipoBusSelected = IConstants.EMPTY_STRING;
    private String codigoRutaSelected = IConstants.EMPTY_STRING;
    private String tipoTiqueteSelected = IConstants.EMPTY_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender_pasajes);
        setPresenter(new VenderPasajesPresenter(DomainModule.getViajeBLInstance(new CustomSharedPreferences(this))));
        getPresenter().inject(this, getValidateInternet());
        createProgressDialog();
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
        venderPasajes_spinnerTiposTiquetes = (Spinner) findViewById(R.id.venderPasajes_spinnerTiposTiquetes);
    }

    private void loadRoutesSpinner() {
        List<String> stringList = getStringListFromRoutesList(bussesAndRoutes.getRutas());
        stringList.add(0, IConstants.SELECT);
        venderPasajes_spinnerRutas.setAdapter(getArrayAdapter(stringList));
        venderPasajes_spinnerRutas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                venderPasajes_spinnerTiposTiquetes.setAdapter(null);
                if (position == 0) {
                    codigoRutaSelected = IConstants.EMPTY_STRING;
                } else {
                    codigoRutaSelected = bussesAndRoutes.getRutas().get(position - 1).getCodigo();
                    prepareDataToGetTickets(tipoBusSelected, codigoRutaSelected);
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
                venderPasajes_spinnerTiposTiquetes.setAdapter(null);
                if (position == 0) {
                    tipoBusSelected = IConstants.EMPTY_STRING;
                } else {
                    tipoBusSelected = bussesAndRoutes.getTiposBuses().get(position - 1).getTipo();
                    prepareDataToGetTickets(tipoBusSelected, codigoRutaSelected);
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
    public void loadTicketsSpinnerOnUiThread(final List<TipoTiquete> tipoTiquetes) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadTicketsSpinner(tipoTiquetes);
            }
        });
    }

    private void loadTicketsSpinner(final List<TipoTiquete> tipoTiquetes) {
        List<String> stringList = getStringListFromTicketList(tipoTiquetes);
        stringList.add(0, IConstants.SELECT);
        venderPasajes_spinnerTiposTiquetes.setAdapter(getArrayAdapter(stringList));
        venderPasajes_spinnerTiposTiquetes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    tipoTiqueteSelected = IConstants.EMPTY_STRING;
                } else {
                    tipoTiqueteSelected = tipoTiquetes.get(position - 1).getTipo();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private List<String> getStringListFromTicketList(List<TipoTiquete> tipoTiquetes) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < tipoTiquetes.size(); i++) {
            stringList.add(tipoTiquetes.get(i).getDescripcion());
        }
        return stringList;
    }

    private void prepareDataToGetTickets(String tipoBusSelected, String codigoRutaSelected) {

        Viaje viaje = new Viaje();
        viaje.setCodigoTipoBus(tipoBusSelected);
        viaje.setCodigoRuta(codigoRutaSelected);

        getPresenter().validateFieldsToGetTickets(viaje);
    }
}
