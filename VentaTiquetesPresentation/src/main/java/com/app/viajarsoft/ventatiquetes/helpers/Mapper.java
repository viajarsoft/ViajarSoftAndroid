package com.app.viajarsoft.ventatiquetes.helpers;


import com.app.viajarsoft.ventatiquetes.dto.MensajeDTO;
import com.app.viajarsoft.ventatiquetes.dto.UsuarioDTO;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetesdomain.business_models.DTO.ErrorDTO;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Usuario;

import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by josetabaresramirez on 18/11/16.
 */

public class Mapper {
    public static Usuario convertUsuarioDTOToDomain(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setApellido(usuarioDTO.getApellidos());
        usuario.setCelular(usuarioDTO.getCelular());
        usuario.setCorreoAlternativo(usuarioDTO.getCorreoAlternativo());
        usuario.setCorreoElectronico(usuarioDTO.getCorreoElectronico());
        usuario.setDireccion(usuarioDTO.getDireccion());
        usuario.setEnvioNotificacion(usuarioDTO.isEnvioNotificacion());
        usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        usuario.setIdGenero(usuarioDTO.getIdGenero());
        usuario.setNombres(usuarioDTO.getNombres());
        usuario.setNumeroIdentificacion(usuarioDTO.getNumeroIdentificacion());
        usuario.setPais(usuarioDTO.getPais());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setIdTipoIdentificacion(usuarioDTO.getIdTipoIdentificacion());
        usuario.setIdTipoPersona(usuarioDTO.getIdTipoPersona());
        usuario.setIdTipoVivienda(usuarioDTO.getIdTipoVivienda());
        usuario.setToken(usuarioDTO.getToken());
        usuario.setActivo(usuarioDTO.isActivo());
        usuario.setFechaRegistro(usuarioDTO.getFechaRegistro());
        usuario.setAceptoTerminosyCondiciones(usuarioDTO.isAceptoTerminosyCondiciones());
        usuario.setIdUsuario(usuarioDTO.getIdUsuario());
        return usuario;
    }

    public static RepositoryError convertMensajeDTOToRepositoryError(MensajeDTO mensaje) {
        RepositoryError repositoryError = new RepositoryError(mensaje.getTexto());
        repositoryError.setIdError(mensaje.getCodigo());
        return repositoryError;
    }

    public static RepositoryError convertRetrofitErrorToRepositoryError(RetrofitError retrofitError) {

        RepositoryError repositoryError;

        repositoryError = valdiateTimeOutToGetRepositoryError(retrofitError);
        if (repositoryError != null) {
            return repositoryError;
        }

        repositoryError = validateTheBodyToGetRepositoryError(retrofitError);
        if (repositoryError != null) {
            return repositoryError;
        }

        return getDefaulError();
    }

    private static RepositoryError getDefaulError() {
        RepositoryError repositoryError = new RepositoryError(IConstants.DEFAUL_ERROR);
        repositoryError.setIdError(IConstants.DEFAUL_ERROR_CODE);
        return repositoryError;
    }

    private static RepositoryError validateTheBodyToGetRepositoryError(RetrofitError retrofitError) {
        RepositoryError repositoryError = null;
        Response response = retrofitError.getResponse();
        if (response != null) {
            int errorId = response.getStatus();
            if (errorId == IConstants.UNAUTHORIZED_ERROR_CODE || errorId == IConstants.NOT_FOUND_ERROR_CODE) {
                ErrorDTO errorDTO = (ErrorDTO) retrofitError.getBodyAs(ErrorDTO.class);
                if (errorDTO != null) {
                    repositoryError = new RepositoryError(errorDTO.getMessage());
                } else {
                    repositoryError = new RepositoryError(IConstants.DEFAUL_ERROR);
                }
            } else {
                repositoryError = new RepositoryError(IConstants.DEFAUL_ERROR);
            }
            repositoryError.setIdError(errorId);
        }
        return repositoryError;
    }

    private static RepositoryError valdiateTimeOutToGetRepositoryError(RetrofitError retrofitError) {
        if (retrofitError.getCause() != null && retrofitError.getCause() instanceof SocketTimeoutException
                || retrofitError.getCause() instanceof InterruptedIOException) {
            RepositoryError repositoryError = new RepositoryError(IConstants.REQUEST_TIMEOUT_ERROR_MESSAGE);
            repositoryError.setIdError(IConstants.DEFAUL_ERROR_CODE);
            return repositoryError;
        }
        return null;
    }
}
