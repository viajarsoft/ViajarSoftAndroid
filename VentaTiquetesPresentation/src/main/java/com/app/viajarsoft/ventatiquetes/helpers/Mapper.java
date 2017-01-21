package com.app.viajarsoft.ventatiquetes.helpers;


import android.support.annotation.Nullable;

import com.app.viajarsoft.ventatiquetes.dto.BussesAndRoutesDTO;
import com.app.viajarsoft.ventatiquetes.dto.DestinationPriceDTO;
import com.app.viajarsoft.ventatiquetes.dto.LiquidacionVentasDTO;
import com.app.viajarsoft.ventatiquetes.dto.ListDestinationPricesDTO;
import com.app.viajarsoft.ventatiquetes.dto.ListTipoTiquetesDTO;
import com.app.viajarsoft.ventatiquetes.dto.ResumenLiquidacionDTO;
import com.app.viajarsoft.ventatiquetes.dto.ResumenLiquidacionImpresionDTO;
import com.app.viajarsoft.ventatiquetes.dto.ResumenVentasPorLiquidarDTO;
import com.app.viajarsoft.ventatiquetes.dto.RutaDTO;
import com.app.viajarsoft.ventatiquetes.dto.TipoBusDTO;
import com.app.viajarsoft.ventatiquetes.dto.TipoTiqueteDTO;
import com.app.viajarsoft.ventatiquetes.dto.TiqueteDTO;
import com.app.viajarsoft.ventatiquetes.dto.UsuarioRequestDTO;
import com.app.viajarsoft.ventatiquetes.dto.UsuarioResponseDTO;
import com.app.viajarsoft.ventatiquetes.dto.VentaPorLiquidarDTO;
import com.app.viajarsoft.ventatiquetes.dto.ViajeDTO;
import com.app.viajarsoft.ventatiquetes.utilities.utils.IConstants;
import com.app.viajarsoft.ventatiquetesdomain.business_models.BussesAndRoutes;
import com.app.viajarsoft.ventatiquetesdomain.business_models.DTO.ErrorDTO;
import com.app.viajarsoft.ventatiquetesdomain.business_models.DestinationPrice;
import com.app.viajarsoft.ventatiquetesdomain.business_models.LiquidacionVentas;
import com.app.viajarsoft.ventatiquetesdomain.business_models.RepositoryError;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenLiquidacion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenLiquidacionImpresion;
import com.app.viajarsoft.ventatiquetesdomain.business_models.ResumenVentasPorLiquidar;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Ruta;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoBus;
import com.app.viajarsoft.ventatiquetesdomain.business_models.TipoTiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Tiquete;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioRequest;
import com.app.viajarsoft.ventatiquetesdomain.business_models.UsuarioResponse;
import com.app.viajarsoft.ventatiquetesdomain.business_models.VentaPorLiquidar;
import com.app.viajarsoft.ventatiquetesdomain.business_models.Viaje;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

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
        usuarioResponse.setNombreOficina(usuarioResponseDTO.getNombreOficina());
        return usuarioResponse;
    }

    public static BussesAndRoutes convertBussesAndRoutesDTOToDomain(BussesAndRoutesDTO bussesAndRoutesDTO) {
        BussesAndRoutes bussesAndRoutes = new BussesAndRoutes();
        bussesAndRoutes.setRutas(convertListRutasDTOToDomain(bussesAndRoutesDTO.getRutas()));
        bussesAndRoutes.setTiposBuses(convertListTipoBusesDTOToDomain(bussesAndRoutesDTO.getTiposBus()));
        return bussesAndRoutes;
    }

    private static ArrayList<TipoBus> convertListTipoBusesDTOToDomain(List<TipoBusDTO> tipoBusDTOs) {
        if (tipoBusDTOs != null) {
            return new ArrayList<>(Lists.transform(tipoBusDTOs, new Function<TipoBusDTO, TipoBus>() {
                @Nullable
                @Override
                public TipoBus apply(TipoBusDTO tipoBusDTO) {
                    TipoBus tipoBus = new TipoBus();
                    tipoBus.setTipo(tipoBusDTO.getTipo());
                    tipoBus.setDescripcion(tipoBusDTO.getDescripcion());
                    return tipoBus;
                }
            }));
        } else {
            return new ArrayList<>();
        }
    }

    private static ArrayList<Ruta> convertListRutasDTOToDomain(List<RutaDTO> rutaDTOs) {
        if (rutaDTOs != null) {
            return new ArrayList<>(Lists.transform(rutaDTOs, new Function<RutaDTO, Ruta>() {
                @Nullable
                @Override
                public Ruta apply(RutaDTO rutaDTO) {
                    Ruta ruta = new Ruta();
                    ruta.setCodigo(rutaDTO.getCodigo());
                    ruta.setDescripcion(rutaDTO.getDescripcion());
                    return ruta;
                }
            }));
        } else {
            return new ArrayList<>();
        }
    }

    public static ViajeDTO convertViajeDomainToDTO(Viaje viaje) {
        ViajeDTO viajeDTO = new ViajeDTO();
        viajeDTO.setCodigoTipoBus(viaje.getCodigoTipoBus());
        viajeDTO.setCodigoRuta(viaje.getCodigoRuta());
        viajeDTO.setTipoTiquete(viaje.getTipoTiquete());
        viajeDTO.setCodigoOficina(viaje.getCodigoOficina());
        viajeDTO.setCodigoTaquilla(viaje.getCodigoTaquilla());
        viajeDTO.setValorTiquete(viaje.getValorTiquete());
        viajeDTO.setValorSeguro(viaje.getValorSeguro());
        return viajeDTO;
    }

    public static List<TipoTiquete> convertListTipoTiquetesDTOToDomain(ListTipoTiquetesDTO listTipoTiquetesDTO) {
        if (listTipoTiquetesDTO != null && listTipoTiquetesDTO.getTiposTiquete() != null) {
            return Lists.transform(listTipoTiquetesDTO.getTiposTiquete(), new Function<TipoTiqueteDTO, TipoTiquete>() {
                @Nullable
                @Override
                public TipoTiquete apply(TipoTiqueteDTO tipoTiqueteDTO) {
                    TipoTiquete tipoTiquete = new TipoTiquete();
                    tipoTiquete.setDescripcion(tipoTiqueteDTO.getDescripcion());
                    tipoTiquete.setTipo(tipoTiqueteDTO.getTipo());
                    return tipoTiquete;
                }
            });
        } else {
            return new ArrayList<>();
        }
    }

    public static List<DestinationPrice> convertListDestinationPricesDTOToDomain(ListDestinationPricesDTO listDestinationPricesDTO) {
        if (listDestinationPricesDTO != null && listDestinationPricesDTO.getPreciosDestino() != null) {
            return Lists.transform(listDestinationPricesDTO.getPreciosDestino(), new Function<DestinationPriceDTO, DestinationPrice>() {
                @Nullable
                @Override
                public DestinationPrice apply(DestinationPriceDTO destinationPriceDTO) {
                    DestinationPrice destinationPrice = new DestinationPrice();
                    destinationPrice.setDestino(destinationPriceDTO.getDestino());
                    destinationPrice.setValorTiquete(destinationPriceDTO.getValorTiquete());
                    destinationPrice.setValorSeguro(destinationPriceDTO.getValorSeguro());
                    return destinationPrice;
                }
            });
        } else {
            return new ArrayList<>();
        }
    }

    public static Tiquete convertTiqueteDTOToDomain(TiqueteDTO tiqueteDTO) {
        Tiquete tiquete = new Tiquete();
        tiquete.setZplTiquete(tiqueteDTO.getZplTiquete());
        return tiquete;
    }

    public static ResumenLiquidacionDTO convertResumenLiquidacionDomainToDTO(ResumenLiquidacion resumenLiquidacion) {
        ResumenLiquidacionDTO resumenLiquidacionDTO = new ResumenLiquidacionDTO();
        resumenLiquidacionDTO.setCodigoTaquilla(resumenLiquidacion.getCodigoTaquilla());
        resumenLiquidacionDTO.setCodigoOficina(resumenLiquidacion.getCodigoOficina());
        return resumenLiquidacionDTO;
    }

    public static ResumenVentasPorLiquidar convertResumenVentasPorLiquidarDTOToDomain(ResumenVentasPorLiquidarDTO resumenVentasPorLiquidarDTO){
        ResumenVentasPorLiquidar resumenVentasPorLiquidar = new ResumenVentasPorLiquidar();
        resumenVentasPorLiquidar.setVentaPorLiquidar(convertVentaPorLiquidarDTOToDomain(resumenVentasPorLiquidarDTO.getVentaPorLiquidar()));
        return resumenVentasPorLiquidar;
    }

    public static VentaPorLiquidar convertVentaPorLiquidarDTOToDomain (VentaPorLiquidarDTO ventaPorLiquidarDTO){
        VentaPorLiquidar ventaPorLiquidar = new VentaPorLiquidar();
        ventaPorLiquidar.setCodigoOficina(ventaPorLiquidarDTO.getCodigoOficina());
        ventaPorLiquidar.setCodigoTaquilla(ventaPorLiquidarDTO.getCodigoTaquilla());
        ventaPorLiquidar.setCodigoTipoTiquete(ventaPorLiquidarDTO.getCodigoTipoTiquete());
        ventaPorLiquidar.setFechaVenta(ventaPorLiquidarDTO.getFechaVenta());
        ventaPorLiquidar.setCantidad(ventaPorLiquidarDTO.getCantidad());
        ventaPorLiquidar.setValorTiquete(ventaPorLiquidarDTO.getValorTiquete());
        ventaPorLiquidar.setValorSeguro(ventaPorLiquidar.getValorSeguro());
        ventaPorLiquidar.setNombreTaquilla(ventaPorLiquidarDTO.getNombreTaquilla());
        return ventaPorLiquidar;
    }

    public static LiquidacionVentasDTO convertLiquidationDomainToDTO(LiquidacionVentas liquidacionVentas){
        LiquidacionVentasDTO liquidacionVentasDTO = new LiquidacionVentasDTO();
        liquidacionVentasDTO.setCodigoOficina(liquidacionVentas.getCodigoOficina());
        liquidacionVentasDTO.setCodigoUsuario(liquidacionVentas.getCodigoUsuario());
        liquidacionVentasDTO.setFechaVenta(liquidacionVentas.getFechaVenta());
        liquidacionVentasDTO.setCodigoTaquilla(liquidacionVentas.getCodigoTaquilla());
        liquidacionVentasDTO.setTipoVenta(liquidacionVentas.getTipoVenta());

        return liquidacionVentasDTO;
    }

    public static ResumenLiquidacionImpresion convertResumenLiquidacionImpresionDTOToDomain(ResumenLiquidacionImpresionDTO resumenLiquidacionImpresionDTO){
        ResumenLiquidacionImpresion resumenLiquidacionImpresion = new ResumenLiquidacionImpresion();

         resumenLiquidacionImpresion.setZplResumen(resumenLiquidacionImpresionDTO.getZplResumen());
        return resumenLiquidacionImpresion;
    }
}
