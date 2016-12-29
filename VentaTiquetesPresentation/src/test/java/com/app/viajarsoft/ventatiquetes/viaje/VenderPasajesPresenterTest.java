package com.app.viajarsoft.ventatiquetes.viaje;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.presenters.VenderPasajesPresenter;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.IValidateInternet;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.IVenderPasajesView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.DestinationPrice;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoTiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Viaje;
import com.app.viajarsoft.ventatiquetesdomain.viaje.IViajeRepository;
import com.app.viajarsoft.ventatiquetesdomain.viaje.ViajeBL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by josetabaresramirez on 28/12/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class VenderPasajesPresenterTest {

    @Mock
    IVenderPasajesView venderPasajesView;

    @Mock
    IValidateInternet validateInternet;

    @Mock
    IViajeRepository viajeRepository;

    VenderPasajesPresenter venderPasajesPresenter;

    ViajeBL viajeBL;

    @Before
    public void setUp() throws Exception {
        viajeBL = Mockito.spy(new ViajeBL(viajeRepository));
        venderPasajesPresenter = Mockito.spy(new VenderPasajesPresenter(viajeBL));
        venderPasajesPresenter.inject(venderPasajesView, validateInternet);
    }

    @Test
    public void methosValidateFieldsToGetTicketsWithCodigoBusEmptyNotShouldCallValidateInternet() {
        Viaje viaje = getViaje();
        viaje.setCodigoTipoBus("");

        venderPasajesPresenter.validateFieldsToGetTickets(viaje);

        verify(venderPasajesPresenter, never()).validateInternetToGetTickets(viaje);
    }

    @Test
    public void methosValidateFieldsToGetTicketsWithCodigoRutaEmptyNotShouldCallValidateInternet() {
        Viaje viaje = getViaje();
        viaje.setCodigoRuta("");

        venderPasajesPresenter.validateFieldsToGetTickets(viaje);

        verify(venderPasajesPresenter, never()).validateInternetToGetTickets(viaje);
    }

    @Test
    public void methosValidateFieldsToGetTicketsWithCorrectParametersShouldCallValidateInternet() {

        Viaje viaje = getViaje();

        venderPasajesPresenter.validateFieldsToGetTickets(viaje);

        verify(venderPasajesPresenter).validateInternetToGetTickets(viaje);
    }

    @Test
    public void methodValdiateInternetToGetTicketsWithoutConnectionShouldShowAlertDialog() {
        when(validateInternet.isConnected()).thenReturn(false);
        Viaje viaje = getViaje();

        venderPasajesPresenter.validateInternetToGetTickets(viaje);

        verify(venderPasajesView).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        verify(venderPasajesPresenter, never()).createThreadToExecuteAnAction((Runnable) anyObject());
    }

    @Test
    public void methodValdiateInternetToGetTicketsWithConnectionShouldCallCreateThreadToGetTickets() {
        when(validateInternet.isConnected()).thenReturn(true);
        Viaje viaje = getViaje();

        venderPasajesPresenter.validateInternetToGetTickets(viaje);

        verify(venderPasajesPresenter).createThreadToExecuteAnAction((Runnable) anyObject());
        verify(venderPasajesPresenter).getTickets(viaje);
        verify(venderPasajesView, never()).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
    }

    @Test
    public void methodCreateThreadToGetTicketsShouldCallShowProgressDialog() {

        venderPasajesPresenter.createThreadToExecuteAnAction((Runnable) anyObject());

        verify(venderPasajesView).showProgressDialog(R.string.text_please_wait);
    }

    @Test
    public void methodGetTicketsShouldCallGetTicketsInBL() throws RepositoryError {
        Viaje viaje = getViaje();

        venderPasajesPresenter.getTickets(viaje);

        verify(viajeBL).getTickets(viaje);
        verify(venderPasajesView).dismissProgressDialog();
    }

    @Test
    public void methodGetTicketsShouldCallLoadTicketsSpinner() throws RepositoryError {
        Viaje viaje = getViaje();
        List<TipoTiquete> tipoTiquetes = new ArrayList<>();
        when(viajeRepository.getTickets(viaje)).thenReturn(tipoTiquetes);

        venderPasajesPresenter.getTickets(viaje);

        verify(venderPasajesView).loadTicketsSpinnerOnUiThread(tipoTiquetes);
        verify(venderPasajesView).dismissProgressDialog();

    }

    @Test
    public void methodGetTicketsShouldShowAlertDialogIfAnErrorOcurred() throws RepositoryError {

        RepositoryError repositoryError = new RepositoryError(IConstants.DEFAUL_ERROR);
        repositoryError.setIdError(0);
        Viaje viaje = getViaje();
        when(viajeRepository.getTickets(viaje)).thenThrow(repositoryError);

        venderPasajesPresenter.getTickets(viaje);

        verify(venderPasajesView).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
    }

    @Test
    public void methodValidateInternetToGetDestinationPricesWithoutConnectionShouldShow() {
        when(validateInternet.isConnected()).thenReturn(false);
        Viaje viaje = getViaje();

        venderPasajesPresenter.validateInternetToGetDestinationsPrices(viaje);

        verify(venderPasajesView).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        verify(venderPasajesPresenter, never()).createThreadToExecuteAnAction((Runnable) anyObject());
    }

    @Test
    public void methodValidateInternetToGetDestinationPricesWithConnectionShouldShow() {
        when(validateInternet.isConnected()).thenReturn(true);
        Viaje viaje = getViaje();

        venderPasajesPresenter.validateInternetToGetDestinationsPrices(viaje);

        verify(venderPasajesPresenter).createThreadToExecuteAnAction((Runnable) anyObject());
        verify(venderPasajesPresenter).getDestinationPrices(viaje);
        verify(venderPasajesView, never()).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
    }

    @Test
    public void methodGetDestinationPricesShouldCallGetDestinationPricesInBL() throws RepositoryError {
        Viaje viaje = getViaje();

        venderPasajesPresenter.getDestinationPrices(viaje);

        verify(viajeBL).getDestinationPrices(viaje);
    }

    @Test
    public void methodGetDestinationPricesShouldCallLoadDestinationPrices() throws RepositoryError {
        Viaje viaje = getViaje();
        List<DestinationPrice> destinationPriceList = new ArrayList<>();
        when(viajeRepository.getDestinationPrices(viaje)).thenReturn(destinationPriceList);

        venderPasajesPresenter.getDestinationPrices(viaje);

        verify(venderPasajesView).loadDestinationPricesOnUiThread(destinationPriceList);
        verify(venderPasajesView).dismissProgressDialog();
    }

    @Test
    public void methodGetDestinationPricesShouldShowAlertDalogIfAnErrorOcurred() throws RepositoryError {

        RepositoryError repositoryError = new RepositoryError(IConstants.DEFAUL_ERROR);
        repositoryError.setIdError(0);
        Viaje viaje = getViaje();
        when(viajeRepository.getDestinationPrices(viaje)).thenThrow(repositoryError);

        venderPasajesPresenter.getDestinationPrices(viaje);

        verify(venderPasajesView).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
    }

    private Viaje getViaje() {
        Viaje viaje = new Viaje();
        viaje.setCodigoTipoBus("123");
        viaje.setCodigoRuta("123");
        viaje.setCodigoTipoPasaje("123");

        return viaje;
    }
}
