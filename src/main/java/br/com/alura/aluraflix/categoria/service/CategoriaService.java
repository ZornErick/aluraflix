package br.com.alura.aluraflix.categoria.service;

import br.com.alura.aluraflix.categoria.Categoria;
import br.com.alura.aluraflix.util.crud.CrudService;
import br.com.alura.aluraflix.video.Video;

import java.util.List;

public interface CategoriaService extends CrudService<Categoria> {

    List<Video> findVideosByCategoriaId(Long categoriaId);
}
