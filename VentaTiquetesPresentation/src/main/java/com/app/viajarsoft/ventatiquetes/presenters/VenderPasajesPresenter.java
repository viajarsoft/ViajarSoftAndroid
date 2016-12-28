package com.app.viajarsoft.ventatiquetes.presenters;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.view.views_activities.IVenderPasajesView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Viaje;
import com.app.viajarsoft.ventatiquetesdomain.viaje.ViajeBL;

/**
 * Created by jose on 20/12/16.
 */

public class VenderPasajesPresenter extends BasePresenter<IVenderPasajesView> {

    private ViajeBL viajeBL;

    public VenderPasajesPresenter(ViajeBL viajeBL) {
        this.viajeBL = viajeBL;
    }

    public void validateFieldsToGetTickets(Viaje viaje) {
        if (!viaje.getCodigoTipoBus().isEmpty() && !viaje.getCodigoRuta().isEmpty()) {
            validateInternetToGetTickets(viaje);
        }
    }

    public void validateInternetToGetTickets(Viaje viaje) {
        if (getValidateInternet().isConnected()) {
            createThreadToGetTickets(viaje);
        } else {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        }
    }

    public void createThreadToGetTickets(final Viaje viaje) {
        getView().showProgressDialog(R.string.text_please_wait);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getTickets(viaje);
            }
        });
        thread.start();
    }

    public void getTickets(Viaje viaje) {
        try {
            getView().loadTicketsSpinnerOnUiThread(viajeBL.getTickets(viaje));
        } catch (RepositoryError repositoryError) {
            getView().showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
        } finally {
            getView().dismissProgressDialog();
        }
    }
}
