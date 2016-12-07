package com.app.viajarsoft.ventatiquetesdomain.security;


import com.app.viajarsoft.ventatiquetes.utilities.helpers.Validations;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Usuario;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public class SecurityBL {

    private ISecurityRepository securityRepository;

    public SecurityBL(ISecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    public Usuario login(UsuarioRequest usuarioRequest) throws RepositoryError {
        Validations.validateNullParameter(usuarioRequest);
        Validations.validateNullParameter(usuarioRequest.getCorreoElectronico(), usuarioRequest.getContrasenia());
        Validations.validateEmptyParameter(usuarioRequest.getCorreoElectronico(), usuarioRequest.getContrasenia());
        return securityRepository.login(usuarioRequest);
    }
}
