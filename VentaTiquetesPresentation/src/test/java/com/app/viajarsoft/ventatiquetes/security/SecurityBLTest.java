package com.app.viajarsoft.ventatiquetes.security;


import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Usuario;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;
import com.app.viajarsoft.ventatiquetesdomain.security.ISecurityRepository;
import com.app.viajarsoft.ventatiquetesdomain.security.SecurityBL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by josetabaresramirez on 17/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class SecurityBLTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    SecurityBL securityBL;

    @Mock
    ISecurityRepository securityRepository;

    @Before
    public void setUp() {
        securityBL = new SecurityBL(securityRepository);
    }

    private UsuarioRequest getUsuarioResponseInstance() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setCorreoElectronico("lcz97@live.com");
        usuarioRequest.setContrasenia("test");
        return usuarioRequest;
    }

    @Test
    public void methodLoginWithUsuarioResponseNullShouldReturnAnException() throws RepositoryError {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.NULL_PARAMETERS);
        securityBL.login(null);
    }

    @Test
    public void methodLoginWithEmailNullShouldReturnAnException() throws RepositoryError {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.NULL_PARAMETERS);
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        usuarioRequest.setCorreoElectronico(null);
        securityBL.login(usuarioRequest);
    }

    @Test
    public void methodLoginWithPasswordNullShouldReturnAnException() throws RepositoryError {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.NULL_PARAMETERS);
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        usuarioRequest.setContrasenia(null);
        securityBL.login(usuarioRequest);
    }

    @Test
    public void methodLoginWithEmptyEmailShouldReturnAnException() throws RepositoryError {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.EMPTY_PARAMETERS);
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        usuarioRequest.setCorreoElectronico(IConstants.EMPTY_STRING);
        securityBL.login(usuarioRequest);
    }

    @Test
    public void methodLoginWithEmptyPasswordShouldReturnAnException() throws RepositoryError {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(IConstants.EMPTY_PARAMETERS);
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        usuarioRequest.setContrasenia(IConstants.EMPTY_STRING);
        securityBL.login(usuarioRequest);
    }

    @Test
    public void methodLoginWithCorrectParametersShouldCallMethodLoginInRepository() throws RepositoryError {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        securityBL.login(usuarioRequest);
        verify(securityRepository).login(usuarioRequest);
    }

    @Test
    public void methodLoginShouldReturnAnUsuarioWhenCallLoginInRepository() throws RepositoryError {
        UsuarioRequest usuarioRequest = getUsuarioResponseInstance();
        Usuario usuario = new Usuario();
        usuario.setToken("sgg65456");
        when(securityRepository.login(usuarioRequest)).thenReturn(usuario);
        Usuario result = securityBL.login(usuarioRequest);
        Assert.assertEquals(usuario, result);
    }
}
