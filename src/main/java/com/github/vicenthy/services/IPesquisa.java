package com.github.vicenthy.services;

import java.util.List;

import com.github.vicenthy.dto.ArtigoDTO;
public interface IPesquisa {
    List<ArtigoDTO> verificarFakeNews(String parametro);

}