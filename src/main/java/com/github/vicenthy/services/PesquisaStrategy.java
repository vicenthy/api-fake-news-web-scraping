package com.github.vicenthy.services;

import java.util.List;

import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.dto.FakeNewsCheckProvider;
public interface PesquisaStrategy{
    List<ArtigoDTO> verificarFakeNews(String parametro);
    FakeNewsCheckProvider provider();

}