package com.app.viajarsoft.ventatiquetes.services;

import com.app.viajarsoft.ventatiquetes.dto.UsuarioResponseDTO;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public interface ISecurityServices {

    @GET("/AdministracionUsuario/Autenticar")
    UsuarioResponseDTO login(@Query("CorreoElectronico") String correoElectronico, @Query("Contrasenia") String contrasenia);
}
