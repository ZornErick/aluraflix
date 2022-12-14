package br.com.alura.aluraflix.domain.video.service;

import br.com.alura.aluraflix.domain.video.Video;
import br.com.alura.aluraflix.util.crud.CrudService;

import java.util.List;

public interface VideoService extends CrudService<Video> {

    List<Video> findVideosByTitulo(String titulo);
}
