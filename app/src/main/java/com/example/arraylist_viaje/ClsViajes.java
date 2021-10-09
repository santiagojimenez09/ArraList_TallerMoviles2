package com.example.arraylist_viaje;

public class ClsViajes {

    private String codigo;
    private String transporte;
    private String cantidad;
    private String alimentacion;
    private String iva;
    private String total;


    public ClsViajes(String codigo, String transporte, String cantidad, String alimentacion, String iva, String total) {
        this.codigo = codigo;
        this.transporte = transporte;
        this.cantidad = cantidad;
        this.alimentacion = alimentacion;
        this.iva = iva;
        this.total = total;
    }

    public ClsViajes() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(String alimentacion) {
        this.alimentacion = alimentacion;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
