package com.app.viajarsoft.ventatiquetesdomain.security;


import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public interface ISecurityRepository {

    UsuarioResponse login(UsuarioRequest usuarioRequest) throws RepositoryError;
}
