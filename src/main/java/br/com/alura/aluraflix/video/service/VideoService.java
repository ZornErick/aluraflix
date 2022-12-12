package br.com.alura.aluraflix.video.service;

import br.com.alura.aluraflix.util.crud.CrudService;
import br.com.alura.aluraflix.video.Video;

import java.util.List;

public interface VideoService extends CrudService<Video> {

    List<Video> findVideosByTitulo(String titulo);
}
