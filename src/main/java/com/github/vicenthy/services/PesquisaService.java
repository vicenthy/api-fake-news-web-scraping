package com.github.vicenthy.services;

import java.util.List;

import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.dto.FakeNewsCheckProvider;

public interface PesquisaService {
    
    List<ArtigoDTO> search(String term, FakeNewsCheckProvider provider);
}
