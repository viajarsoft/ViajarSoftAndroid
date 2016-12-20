package com.app.viajarsoft.ventatiquetes.helpers;


import com.app.viajarsoft.ventatiquetes.dto.UsuarioRequestDTO;
import com.app.viajarsoft.ventatiquetes.dto.UsuarioResponseDTO;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetesdomain.business_models.DTO.ErrorDTO;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;

import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by josetabaresramirez on 18/11/16.
 */

public class Mapper {

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
                    repositoryError = new RepositoryError(errorDTO.getMensaje());
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

    public static UsuarioRequestDTO convertUsuarioRequestDomainToDTO(UsuarioRequest usuarioRequest) {
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        usuarioRequestDTO.setClave(usuarioRequest.getContrasenia());
        usuarioRequestDTO.setUsuario(usuarioRequest.getUsuario());
        usuarioRequestDTO.setIpUsuario("10.1.1.1");
        return usuarioRequestDTO;
    }

    public static UsuarioResponse convertUsuarioResponseDTOToDomain(UsuarioResponseDTO usuarioResponseDTO) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setCodigoOficina(usuarioResponseDTO.getCodigoOficina());
        usuarioResponse.setCodigoTaquilla(usuarioResponseDTO.getCodigoTaquilla());
        usuarioResponse.setIdentificadorEmpresa(usuarioResponseDTO.getIdentificadorEmpresa());
        usuarioResponse.setToken(usuarioResponseDTO.getToken());
        return usuarioResponse;
    }
}
