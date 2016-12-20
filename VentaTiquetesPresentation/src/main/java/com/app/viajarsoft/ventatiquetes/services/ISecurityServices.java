package com.app.viajarsoft.ventatiquetes.services;

import com.app.viajarsoft.ventatiquetes.dto.UsuarioRequestDTO;
import com.app.viajarsoft.ventatiquetes.dto.UsuarioResponseDTO;

import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public interface ISecurityServices {

    @POST("/Seguridad/Login")
    UsuarioResponseDTO login(@Body UsuarioRequestDTO usuarioRequestDTO);
}
