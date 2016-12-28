package com.app.viajarsoft.ventatiquetes.viaje;

import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.viaje.IViajeRepository;
import com.app.viajarsoft.ventatiquetesdomain.viaje.ViajeBL;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by jose on 27/12/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ViajeBLTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    ViajeBL viajeBL;

    @Mock
    IViajeRepository viajeRepository;

    @Before
    public void setUp() {
        viajeBL = new ViajeBL(viajeRepository);
    }

    @Test
    public void getBussesAndRoutesWithCodigoOficinaNullShouldReturnAnException() throws RepositoryError {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.NULL_PARAMETERS);

        viajeBL.getBussesAndRoutes(null);
    }

    @Test
    public void getBussesAndRoutesWithCodigoOficinaEmptyShouldReturnAnException() throws RepositoryError {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.EMPTY_PARAMETERS);

        viajeBL.getBussesAndRoutes("");
    }

    @Test
    public void getBussesAndRoutesWithCorrectParameterShouldCallGetBussesAndRoutesInRepository() throws RepositoryError {
        viajeBL.getBussesAndRoutes("123");
        verify(viajeRepository).getBussesAndRoutes("123");
    }
}
