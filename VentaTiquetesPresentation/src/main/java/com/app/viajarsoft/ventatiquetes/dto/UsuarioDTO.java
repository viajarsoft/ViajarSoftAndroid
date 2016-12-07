package com.app.viajarsoft.ventatiquetes.dto;

import java.util.Date;

/**
 * Created by josetabaresramirez on 18/11/16.
 */

public class UsuarioDTO {

    private String Apellidos;
    private String Celular;
    private String CorreoAlternativo;
    private String CorreoElectronico;
    private String Direccion;
    private boolean EnvioNotificacion;
    private Date FechaNacimiento;
    private int IdGenero;
    private String Nombres;
    private String NumeroIdentificacion;
    private String Pais;
    private String Telefono;
    private int IdTipoIdentificacion;
    private int IdTipoPersona;
    private int IdTipoVivienda;
    private String Token;
    private boolean Activo;
    private Date FechaRegistro;
    private boolean AceptoTerminosyCondiciones;
    private String IdUsuario;

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        this.Apellidos = apellidos;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        this.Celular = celular;
    }

    public String getCorreoAlternativo() {
        return CorreoAlternativo;
    }

    public void setCorreoAlternativo(String correoAlternativo) {
        this.CorreoAlternativo = correoAlternativo;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.CorreoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    public boolean isEnvioNotificacion() {
        return EnvioNotificacion;
    }

    public void setEnvioNotificacion(boolean envioNotificacion) {
        this.EnvioNotificacion = envioNotificacion;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.FechaNacimiento = fechaNacimiento;
    }

    public int getIdGenero() {
        return IdGenero;
    }

    public void setIdGenero(int idGenero) {
        this.IdGenero = idGenero;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        this.Nombres = nombres;
    }

    public String getNumeroIdentificacion() {
        return NumeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.NumeroIdentificacion = numeroIdentificacion;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        this.Pais = pais;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    public int getIdTipoIdentificacion() {
        return IdTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(int idTipoIdentificacion) {
        this.IdTipoIdentificacion = idTipoIdentificacion;
    }

    public int getIdTipoPersona() {
        return IdTipoPersona;
    }

    public void setIdTipoPersona(int idTipoPersona) {
        this.IdTipoPersona = idTipoPersona;
    }

    public int getIdTipoVivienda() {
        return IdTipoVivienda;
    }

    public void setIdTipoVivienda(int idTipoVivienda) {
        this.IdTipoVivienda = idTipoVivienda;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        this.Token = token;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean activo) {
        this.Activo = activo;
    }

    public Date getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.FechaRegistro = fechaRegistro;
    }

    public boolean isAceptoTerminosyCondiciones() {
        return AceptoTerminosyCondiciones;
    }

    public void setAceptoTerminosyCondiciones(boolean aceptoTerminosyCondiciones) {
        this.AceptoTerminosyCondiciones = aceptoTerminosyCondiciones;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.IdUsuario = idUsuario;
    }
}
