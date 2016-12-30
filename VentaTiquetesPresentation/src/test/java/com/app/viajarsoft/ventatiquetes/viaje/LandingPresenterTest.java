package com.app.viajarsoft.ventatiquetes.viaje;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.presenters.LandingPresenter;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.IValidateInternet;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILandingView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenLiquidacion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;
import com.app.viajarsoft.ventatiquetesdomain.viaje.IViajeRepository;
import com.app.viajarsoft.ventatiquetesdomain.viaje.ViajeBL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jose on 27/12/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class LandingPresenterTest {

    @Mock
    ILandingView landingView;

    @Mock
    IValidateInternet validateInternet;

    @Mock
    IViajeRepository viajeRepository;

    @Mock
    ICustomSharedPreferences customSharedPreferences;

    LandingPresenter landingPresenter;

    ViajeBL viajeBL;

    @Before
    public void setUp() throws Exception {
        viajeBL = Mockito.spy(new ViajeBL(viajeRepository));
        landingPresenter = Mockito.spy(new LandingPresenter(viajeBL));
        landingPresenter.inject(landingView, validateInternet);
    }

    @Test
    public void methodValidateInternetWithoutConnectionShouldShowAlertDialog() {
        when(validateInternet.isConnected()).thenReturn(false);

        landingPresenter.validateInternetToGetBussesAndRoutes("001");

        verify(landingView).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        verify(landingPresenter, never()).createThreadToGetBussesAndRoutes("001");
    }

    @Test
    public void methodValidateInternetWithConnectionShouldCallCreateThreadToGetBussesAndRoutes() {
        when(validateInternet.isConnected()).thenReturn(true);

        landingPresenter.validateInternetToGetBussesAndRoutes("001");

        verify(landingPresenter).createThreadToGetBussesAndRoutes("001");
        verify(landingView, never()).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
    }

    @Test
    public void methodGetBussesAndRoutesShouldCallGetBussesAndRoutesInBL() throws RepositoryError {

        landingPresenter.getBussesAndRoutes("001");

        verify(viajeBL).getBussesAndRoutes("001");
        verify(landingView).dismissProgressDialog();
    }

    @Test
    public void methodGetBussesAndRoutesShouldCallStartVenderPasajesActivity() throws RepositoryError {
        BussesAndRoutes bussesAndRoutes = new BussesAndRoutes();
        when(viajeRepository.getBussesAndRoutes("001")).thenReturn(bussesAndRoutes);

        landingPresenter.getBussesAndRoutes("001");

        verify(landingView).startVenderPasajesActivity(bussesAndRoutes);
        verify(landingView).dismissProgressDialog();
    }

    @Test
    public void methodGetBussesAndRoutesShouldShowAlertDialogIfAnErrorOcurred() throws RepositoryError {
        RepositoryError repositoryError = new RepositoryError(IConstants.DEFAUL_ERROR);
        repositoryError.setIdError(0);
        when(viajeRepository.getBussesAndRoutes("001")).thenThrow(repositoryError);

        landingPresenter.getBussesAndRoutes("001");

        verify(landingView).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
        verify(landingView).dismissProgressDialog();

    }

    @Test
    public void methodValidateInternetSummaryWithoutConnectionShouldShowAlertDialog() {
        ResumenLiquidacion resumenLiquidacion = new ResumenLiquidacion();
        resumenLiquidacion.setCodigoTaquilla("1213");
        resumenLiquidacion.setCodigoOficina("34356");
        when(validateInternet.isConnected()).thenReturn(false);

        landingPresenter.validateInternetToGetSummaryLiquidation(resumenLiquidacion);

        verify(landingView).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        verify(landingPresenter, never()).createThreadToGetSummaryLiquidation(resumenLiquidacion);
    }

    @Test
    public void methodValidateInternetSummaryWithConnectionShouldCallCreateThreadTGetSummaryLiquidation(){
        ResumenLiquidacion resumenLiquidacion = new ResumenLiquidacion();
        resumenLiquidacion.setCodigoTaquilla("1213");
        resumenLiquidacion.setCodigoOficina("34356");
        when(validateInternet.isConnected()).thenReturn(true);

        landingPresenter.validateInternetToGetSummaryLiquidation(resumenLiquidacion);

        verify(landingPresenter).createThreadToGetSummaryLiquidation(resumenLiquidacion);
        verify(landingView, never()).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
    }

    @Test
    public void methodcreateThreadToGetSummaryLiquidationShouldCallGetSummaryLiquidationInBL() throws RepositoryError {
        ResumenLiquidacion resumenLiquidacion = new ResumenLiquidacion();
        resumenLiquidacion.setCodigoTaquilla("1213");
        resumenLiquidacion.setCodigoOficina("34356");
        landingPresenter.createThreadToGetSummaryLiquidation(resumenLiquidacion);

        verify(viajeBL).getSummaryLiquidation(resumenLiquidacion);
    }
}
