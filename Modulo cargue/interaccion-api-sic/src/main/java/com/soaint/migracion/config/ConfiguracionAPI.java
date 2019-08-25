package com.soaint.migracion.config;

import org.springframework.stereotype.Component;


@Component
public class ConfiguracionAPI {



    private interface endpoints{
        String ENPOINT_BASEPATH = EndpointUtil.getMessage("endpoint.basepath");
        String ENDPOINT_MODIFICAR = EndpointUtil.getMessage("api.modificar");
        Boolean ENDPOINT_ESTADO = Boolean.parseBoolean(EndpointUtil.getMessage("endpoint.habilitado"));
        String ENDPOINT_REGISTRAR = EndpointUtil.getMessage("api.registrar");

    }

    private boolean habilitado;
    private String url;
    private Api api;

    public ConfiguracionAPI() {
        this.habilitado = endpoints.ENDPOINT_ESTADO;
        this.url = endpoints.ENPOINT_BASEPATH;
        this.api = new Api();
        this.api.setRegistrar(endpoints.ENDPOINT_REGISTRAR);
        this.api.setModificar(endpoints.ENDPOINT_MODIFICAR);
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public static class Api {

        private String registrar;
        private String modificar;

        public String getModificar() {
            return modificar;
        }

        public void setModificar(String modificar) {
            this.modificar = modificar;
        }

        public String getRegistrar() {
            return registrar;
        }

        public void setRegistrar(String registrar) {
            this.registrar = registrar;
        }

    }

}
