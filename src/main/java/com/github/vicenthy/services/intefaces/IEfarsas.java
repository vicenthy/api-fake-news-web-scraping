package com.github.vicenthy.services.intefaces;

import java.util.List;

import com.github.vicenthy.dto.ArtigoDTO;

import io.smallrye.mutiny.Multi;
import com.github.vicenthy.services.IPesquisa;

public interface IEfarsas extends IPesquisa{
    Multi<List<ArtigoDTO>> verificarFakeNewsAsync(String parametro);

}
