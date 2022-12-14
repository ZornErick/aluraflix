package br.com.alura.aluraflix.domain.categoria.service;

import br.com.alura.aluraflix.domain.categoria.Categoria;
import br.com.alura.aluraflix.util.crud.CrudService;
import br.com.alura.aluraflix.domain.video.Video;

import java.util.List;

public interface CategoriaService extends CrudService<Categoria> {

    List<Video> findVideosByCategoriaId(Long categoriaId);
}
