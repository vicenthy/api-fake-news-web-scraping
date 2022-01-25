package com.github.vicenthy.dto;

public class ArtigoDTO {

    private String titulo;
    private String urlImg;
    private String urlDetalhes;

        public ArtigoDTO(){

        }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getUrlDetalhes() {
        return urlDetalhes;
    }

    public void setUrlDetalhes(String urlDetalhes) {
        this.urlDetalhes = urlDetalhes;
    }
}