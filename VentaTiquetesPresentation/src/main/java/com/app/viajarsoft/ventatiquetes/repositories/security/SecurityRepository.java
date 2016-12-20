package com.app.viajarsoft.ventatiquetes.repositories.security;

import com.app.viajarsoft.ventatiquetes.dto.UsuarioRequestDTO;
import com.app.viajarsoft.ventatiquetes.dto.UsuarioResponseDTO;
import com.app.viajarsoft.ventatiquetes.helpers.Mapper;
import com.app.viajarsoft.ventatiquetes.services.ISecurityServices;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.ICustomSharedPreferences;
import com.app.viajarsoft.ventatiquetes.utilities.services.ServicesFactory;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;
import com.app.viajarsoft.ventatiquetesdomain.security.ISecurityRepository;

import retrofit.RetrofitError;

/**
 * Created by josetabaresramirez on 15/11/16.
 */

public class SecurityRepository implements ISecurityRepository {

    private ISecurityServices securityServices;

    public SecurityRepository(ICustomSharedPreferences customSharedPreferences) {
        ServicesFactory servicesFactory = new ServicesFactory(customSharedPreferences);
        securityServices = (ISecurityServices) servicesFactory.getInstance(ISecurityServices.class);
    }

    @Override
    public UsuarioResponse login(UsuarioRequest usuarioRequest) throws RepositoryError {
        try {
            UsuarioRequestDTO usuarioRequestDTO = Mapper.convertUsuarioRequestDomainToDTO(usuarioRequest);
            UsuarioResponseDTO usuarioResponseDTO = securityServices.login(usuarioRequestDTO);
            return Mapper.convertUsuarioResponseDTOToDomain(usuarioResponseDTO);
        } catch (RetrofitError retrofitError) {
            throw Mapper.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }
}
