package com.app.viajarsoft.ventatiquetes.repositories.security;


import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;
import com.app.viajarsoft.ventatiquetesdomain.security.ISecurityRepository;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public class SecurityRepositoryTest implements ISecurityRepository {

    @Override
    public UsuarioResponse login(UsuarioRequest usuarioRequest) throws RepositoryError {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setToken("sfgsfgsfg46465");
        return usuarioResponse;
    }
}
