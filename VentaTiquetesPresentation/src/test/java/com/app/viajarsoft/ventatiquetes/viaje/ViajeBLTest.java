package com.app.viajarsoft.ventatiquetes.viaje;

import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Viaje;
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

    @Test
    public void getTicketsWithViajeNullShouldReturnAnException() throws RepositoryError {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.NULL_PARAMETERS);

        viajeBL.getTickets(null);

    }

    @Test
    public void getTicketsWithCodigoRutaeNullShouldReturnAnException() throws RepositoryError {

        Viaje viaje = getViaje();
        viaje.setCodigoRuta(null);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.NULL_PARAMETERS);

        viajeBL.getTickets(viaje);

    }

    @Test
    public void getTicketsWithCodigoBusNullShouldReturnAnException() throws RepositoryError {

        Viaje viaje = getViaje();
        viaje.setCodigoTipoBus(null);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.NULL_PARAMETERS);

        viajeBL.getTickets(viaje);

    }

    @Test
    public void getTicketsWithCodigoRutaEmptyShouldReturnAnException() throws RepositoryError {

        Viaje viaje = getViaje();
        viaje.setCodigoRuta("");

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.EMPTY_PARAMETERS);

        viajeBL.getTickets(viaje);

    }

    @Test
    public void getTicketsWithCodigoBusEmptyShouldReturnAnException() throws RepositoryError {

        Viaje viaje = getViaje();
        viaje.setCodigoTipoBus("");

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.EMPTY_PARAMETERS);

        viajeBL.getTickets(viaje);

    }

    @Test
    public void getTicketsWithCorrectParametersShouldCallGetTicketsInRepository() throws RepositoryError {

        Viaje viaje = getViaje();

        viajeBL.getTickets(viaje);

        verify(viajeRepository).getTickets(viaje);

    }

    @Test
    public void getDestinationPricesWithViajeNullShouldReturnAnException() throws RepositoryError {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.NULL_PARAMETERS);

        viajeBL.getDestinationPrices(null);

    }

    @Test
    public void getDestinationPricesWithTipoPasajeNullShouldReturnAnException() throws RepositoryError {

        Viaje viaje = getViaje();
        viaje.setTipoTiquete(null);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.NULL_PARAMETERS);

        viajeBL.getDestinationPrices(viaje);

    }

    @Test
    public void getDestinationPricesWithTipoPasajeEmptyShouldReturnAnException() throws RepositoryError {

        Viaje viaje = getViaje();
        viaje.setTipoTiquete("");

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.EMPTY_PARAMETERS);

        viajeBL.getDestinationPrices(viaje);

    }

    @Test
    public void getDestinationPricesWithCorrectParametersShouldCalletDestinationPricesInRepository() throws RepositoryError {
        Viaje viaje = getViaje();

        viajeBL.getDestinationPrices(viaje);

        verify(viajeRepository).getDestinationPrices(viaje);
    }

    @Test
    public void sellTicketShouldCallSellTicketInRepository() throws RepositoryError {
        Viaje viaje = getViaje();

        viajeBL.sellTicket(viaje);

        verify(viajeRepository).sellTicket(viaje);
    }

    private Viaje getViaje() {
        Viaje viaje = new Viaje();
        viaje.setCodigoTipoBus("123");
        viaje.setCodigoRuta("123");
        viaje.setTipoTiquete("123");
        viaje.setCodigoOficina("123");
        viaje.setCodigoTaquilla("123");
        viaje.setValorTiquete(1000);
        viaje.setValorSeguro(200);

        return viaje;
    }
}
