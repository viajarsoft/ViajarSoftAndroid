package com.app.viajarsoft.ventatiquetesdomain.security;


import com.app.viajarsoft.ventatiquetes.utilities.helpers.Validations;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public class SecurityBL {

    private ISecurityRepository securityRepository;

    public SecurityBL(ISecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    public UsuarioResponse login(UsuarioRequest usuarioRequest) throws RepositoryError {
        Validations.validateNullParameter(usuarioRequest);
        Validations.validateNullParameter(usuarioRequest.getUsuario(), usuarioRequest.getContrasenia());
        Validations.validateEmptyParameter(usuarioRequest.getUsuario(), usuarioRequest.getContrasenia());
        return securityRepository.login(usuarioRequest);
    }
}
