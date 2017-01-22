package com.app.viajarsoft.ventatiquetes.view.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.dependency_injection.DomainModule;
import com.app.viajarsoft.ventatiquetes.helpers.IImpresionZpl;
import com.app.viajarsoft.ventatiquetes.helpers.ImpresionZpl;
import com.app.viajarsoft.ventatiquetes.presenters.VenderPasajesPresenter;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.CustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.adapters.PrecioDestinoRecyclerViewAdapter;
import com.app.viajarsoft.ventatiquetes.view.views_activities.IVenderPasajesView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.DestinationPrice;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Ruta;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoBus;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoTiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Tiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Viaje;
import com.zebra.sdk.comm.BluetoothConnectionInsecure;
import com.zebra.sdk.comm.Connection;

import java.util.ArrayList;
import java.util.List;

public class VenderPasajesActivity extends BaseActivity<VenderPasajesPresenter> implements IVenderPasajesView {

    private Spinner venderPasajes_spinnerTipoBus;
    private Spinner venderPasajes_spinnerRutas;
    private Spinner venderPasajes_spinnerTiposTiquetes;
    private RecyclerView venderPasajes_recyclerPreciosDestino;

    private UsuarioResponse usuarioResponse;
    private BussesAndRoutes bussesAndRoutes;

    private String tipoBusSelected = IConstants.EMPTY_STRING;
    private String codigoRutaSelected = IConstants.EMPTY_STRING;
    private String tipoTiqueteSelected = IConstants.EMPTY_STRING;

    private ICustomSharedPreferences customSharedPreferences;
    private IImpresionZpl impresionZpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender_pasajes);
        setPresenter(new VenderPasajesPresenter(DomainModule.getViajeBLInstance(new CustomSharedPreferences(this))));
        getPresenter().inject(this, getValidateInternet());
        createProgressDialog();
        this.customSharedPreferences = new CustomSharedPreferences(this);
        this.impresionZpl = new ImpresionZpl();
        this.usuarioResponse = (UsuarioResponse) getIntent().getSerializableExtra(IConstants.USUARIO);
        this.bussesAndRoutes = (BussesAndRoutes) getIntent().getSerializableExtra(IConstants.BUSSES_AND_ROUTES);
        loadToolbar();
        loadViews();
        loadBussesSpinner();
        loadRoutesSpinner();
        validateMacAddress();
    }


    private void validateMacAddress() {
        String addressMac = customSharedPreferences.getString(IConstants.ADDRESSMAC);
        if (addressMac == null || addressMac.isEmpty()) {
            showAlertDialog(R.string.title_user, R.string.text_user);
        }
    }

    public void showAlertDialog(int title, int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.text_aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.title_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }



    private void loadViews() {
        venderPasajes_spinnerTipoBus = (Spinner) findViewById(R.id.venderPasajes_spinnerTipoBus);
        venderPasajes_spinnerRutas = (Spinner) findViewById(R.id.venderPasajes_spinnerRutas);
        venderPasajes_spinnerTiposTiquetes = (Spinner) findViewById(R.id.venderPasajes_spinnerTiposTiquetes);
        venderPasajes_recyclerPreciosDestino = (RecyclerView) findViewById(R.id.venderPasajes_recyclerPreciosDestino);
    }

    private void loadRoutesSpinner() {
        List<String> stringList = getStringListFromRoutesList(bussesAndRoutes.getRutas());
        stringList.add(0, IConstants.SELECT);
        venderPasajes_spinnerRutas.setAdapter(getArrayAdapter(stringList));
        venderPasajes_spinnerRutas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                venderPasajes_spinnerTiposTiquetes.setAdapter(null);
                venderPasajes_recyclerPreciosDestino.setAdapter(null);
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
                venderPasajes_recyclerPreciosDestino.setAdapter(null);
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

    @Override
    public void loadDestinationPricesOnUiThread(final List<DestinationPrice> destinationPriceList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadDestinationPrices(destinationPriceList);
            }
        });
    }

    @Override
    public void prepareDataToSellTicket(DestinationPrice destinationPrice) {
        Viaje viaje = new Viaje();
        viaje.setCodigoTipoBus(tipoBusSelected);
        viaje.setCodigoRuta(codigoRutaSelected);
        viaje.setTipoTiquete(tipoTiqueteSelected);
        viaje.setCodigoOficina(usuarioResponse.getCodigoOficina());
        viaje.setCodigoTaquilla(usuarioResponse.getCodigoTaquilla());
        viaje.setValorTiquete(destinationPrice.getValorTiquete());
        viaje.setValorSeguro(destinationPrice.getValorSeguro());

        getPresenter().validateInternetToSellTicket(viaje);
    }

    @Override
    public void printTicketOnUiThread(final Tiquete tiquete) {
        String addressMac = customSharedPreferences.getString(IConstants.ADDRESSMAC);
        if (addressMac != null && !addressMac.isEmpty()) {
            impresionZpl.printZpl(tiquete.getZplTiquete(), addressMac);
        }
    }

    public void intentToImpresionActivity(String zplResumen) {
        Intent intent = new Intent(VenderPasajesActivity.this, ImpresionActivity.class);
        intent.putExtra(IConstants.IMPRESION, zplResumen);
        startActivity(intent);
    }


    private void loadDestinationPrices(List<DestinationPrice> destinationPriceList) {
        PrecioDestinoRecyclerViewAdapter precioDestinoRecyclerViewAdapter = new PrecioDestinoRecyclerViewAdapter(VenderPasajesActivity.this, destinationPriceList, VenderPasajesActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VenderPasajesActivity.this);
        venderPasajes_recyclerPreciosDestino.setLayoutManager(linearLayoutManager);
        venderPasajes_recyclerPreciosDestino.setAdapter(precioDestinoRecyclerViewAdapter);
    }

    private void loadTicketsSpinner(final List<TipoTiquete> tipoTiquetes) {
        List<String> stringList = getStringListFromTicketList(tipoTiquetes);
        stringList.add(0, IConstants.SELECT);
        venderPasajes_spinnerTiposTiquetes.setAdapter(getArrayAdapter(stringList));
        venderPasajes_spinnerTiposTiquetes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                venderPasajes_recyclerPreciosDestino.setAdapter(null);
                if (position == 0) {
                    tipoTiqueteSelected = IConstants.EMPTY_STRING;
                } else {
                    tipoTiqueteSelected = tipoTiquetes.get(position - 1).getTipo();
                    prepareDataToGetDestinationPrices(tipoBusSelected, codigoRutaSelected, tipoTiqueteSelected);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void prepareDataToGetDestinationPrices(String tipoBusSelected, String codigoRutaSelected, String tipoTiqueteSelected) {
        Viaje viaje = new Viaje();
        viaje.setTipoTiquete(tipoTiqueteSelected);
        viaje.setCodigoRuta(codigoRutaSelected);
        viaje.setCodigoTipoBus(tipoBusSelected);
        getPresenter().validateInternetToGetDestinationsPrices(viaje);
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
