package com.app.viajarsoft.ventatiquetes.viaje;

import com.app.viajarsoft.ventatiquetes.presenters.LiquidarVentasPresenter;
import com.app.viajarsoft.ventatiquetes.presenters.VenderPasajesPresenter;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.IValidateInternet;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ILiquidarVentasView;
import com.app.viajarsoft.ventatiquetesdomain.viaje.IViajeRepository;
import com.app.viajarsoft.ventatiquetesdomain.viaje.ViajeBL;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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

}
