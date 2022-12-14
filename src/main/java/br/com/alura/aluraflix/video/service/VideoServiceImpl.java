package br.com.alura.aluraflix.video.service;

import br.com.alura.aluraflix.categoria.Categoria;
import br.com.alura.aluraflix.categoria.CategoriaRepo;
import br.com.alura.aluraflix.util.crud.Form;
import br.com.alura.aluraflix.util.crud.UpdateForm;
import br.com.alura.aluraflix.util.exception.NotFoundException;
import br.com.alura.aluraflix.video.Video;
import br.com.alura.aluraflix.video.VideoForm;
import br.com.alura.aluraflix.video.VideoRepo;
import br.com.alura.aluraflix.video.VideoUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    private final VideoRepo videoRepo;
    private final CategoriaRepo categoriaRepo;

    @Autowired
    public VideoServiceImpl(VideoRepo videoRepo, CategoriaRepo categoriaRepo) {
        this.videoRepo = videoRepo;
        this.categoriaRepo = categoriaRepo;
    }

    @Override
    public List<Video> findAll() {
        return videoRepo.findAll();
    }

    @Override
    public Video findById(Long id) {
        Optional<Video> video = videoRepo.findById(id);
        if(video.isEmpty()) throw new NotFoundException("Vídeo não encontrado!");
        return video.get();
    }

    @Override
    public Video create(Form<Video> form) {
        VideoForm videoForm = (VideoForm) form;
        Optional<Categoria> categoria = categoriaRepo.findById(videoForm.getCategoriaId());
        if(categoria.isEmpty()) throw new NotFoundException("Categoria não encontrada!");
        return videoRepo.save(videoForm.convert(categoria.get()));
    }

    @Override
    public Video update(Long id, UpdateForm<Video> updateForm) {
        VideoUpdateForm videoUpdateForm = (VideoUpdateForm) updateForm;
        Optional<Video> video = videoRepo.findById(id);
        Optional<Categoria> categoria = categoriaRepo.findById(videoUpdateForm.getCategoriaId());
        if(video.isEmpty()) throw new NotFoundException("Vídeo não encontrado!");
        if(categoria.isEmpty()) throw new NotFoundException("Categoria não encontrada!");
        return videoUpdateForm.update(video.get(), categoria.get());
    }

    @Override
    public void delete(Long id) {
        if(videoRepo.findById(id).isEmpty()) throw new NotFoundException("Vídeo não encontrado!");
        videoRepo.deleteById(id);
    }

    @Override
    public List<Video> findVideosByTitulo(String titulo) {
        if(titulo == null) return findAll();
        return videoRepo.findAllByTitulo(titulo);
    }
}
