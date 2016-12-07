package com.app.viajarsoft.ventatiquetesdomain.security;


import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Usuario;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public interface ISecurityRepository {

    Usuario login(UsuarioRequest usuarioRequest) throws RepositoryError;
}
