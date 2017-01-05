package com.app.viajarsoft.ventatiquetes.viaje;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.presenters.LiquidarVentasPresenter;
import com.app.viajarsoft.ventatiquetes.presenters.VenderPasajesPresenter;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.IValidateInternet;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILiquidarVentasView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Liquidacion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
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
 * Created by USUARIO on 28/12/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class LiquidarVentasPresenterTest {

    @Mock
    ILiquidarVentasView liquidarVentasView;

    @Mock
    IValidateInternet validateInternet;

    @Mock
    IViajeRepository viajeRepository;

    LiquidarVentasPresenter liquidarVentasPresenter;

    ViajeBL viajeBL;

    @Before
    public void setUp() throws Exception {
        viajeBL = Mockito.spy(new ViajeBL(viajeRepository));
        liquidarVentasPresenter = Mockito.spy(new LiquidarVentasPresenter(viajeBL));
        liquidarVentasPresenter.inject(liquidarVentasView, validateInternet);
    }

    private Liquidacion getLiquidacion() {
        Liquidacion liquidacion = new Liquidacion();
        liquidacion.setCodigoOficina("1122");
        liquidacion.setFechaVenta("02-10-2016");
        liquidacion.setCodigoTaquilla("002");
        liquidacion.setCodigoUsuario("0378");

        return liquidacion;
    }

    @Test
    public void methodValidateInternetWithoutConnectionShouldShowAlertDialog() {
        Liquidacion liquidacion = getLiquidacion();

        when(validateInternet.isConnected()).thenReturn(false);

        liquidarVentasPresenter.validateInternetToGetLiquidation(liquidacion);

        verify(liquidarVentasView).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
        verify(liquidarVentasPresenter, never()).createThreadToGetLiquidation(liquidacion);
    }

    @Test
    public void methodValidateInternetSummaryWithConnectionShouldCallCreateThreadTGetSummaryLiquidation(){
        Liquidacion liquidacion = getLiquidacion();
        when(validateInternet.isConnected()).thenReturn(true);

        liquidarVentasPresenter.validateInternetToGetLiquidation(liquidacion);

        verify(liquidarVentasPresenter).createThreadToGetLiquidation(liquidacion);
        verify(liquidarVentasView, never()).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, R.string.text_validate_internet);
    }

    @Test
    public void methodcreateThreadToGetLiquidationShouldCallGetLiquidationInBL() throws RepositoryError {
        Liquidacion liquidacion = getLiquidacion();
        liquidarVentasPresenter.createThreadToGetLiquidation(liquidacion);

        verify(viajeBL).getLiquidation(liquidacion);

    }

    @Test
    public void methodoGetSummaryLiquidationShouldShowAlertDialogIfAnErrorOcurred() throws RepositoryError {
        RepositoryError repositoryError = new RepositoryError(IConstants.DEFAUL_ERROR);
        repositoryError.setIdError(0);
        Liquidacion liquidacion = getLiquidacion();

        when(viajeRepository.getLiquidation(liquidacion)).thenThrow(repositoryError);

        liquidarVentasPresenter.getLiquidation(liquidacion);

        verify(liquidarVentasView).showAlertDialogGeneralInformationOnUiThread(R.string.title_appreciated_user, repositoryError.getMessage());
        verify(liquidarVentasView).dismissProgressDialog();

    }


}
