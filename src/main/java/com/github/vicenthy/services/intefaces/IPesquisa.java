package com.github.vicenthy.services.intefaces;

import java.util.List;

import com.github.vicenthy.dto.ArtigoDTO;

public interface IPesquisa {
    List<ArtigoDTO> verificarFakeNews(String parametro);
}