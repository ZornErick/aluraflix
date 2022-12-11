package br.com.alura.aluraflix.video.service;

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

    @Autowired
    public VideoServiceImpl(VideoRepo videoRepo) {
        this.videoRepo = videoRepo;
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
        return videoRepo.save(videoForm.convert());
    }

    @Override
    public Video update(Long id, UpdateForm<Video> updateForm) {
        VideoUpdateForm videoUpdateForm = (VideoUpdateForm) updateForm;
        Optional<Video> video = videoRepo.findById(id);
        if(video.isEmpty()) throw new NotFoundException("Vídeo não encontrado!");
        return videoUpdateForm.update(video.get());
    }

    @Override
    public void delete(Long id) {
        Optional<Video> video = videoRepo.findById(id);
        if(video.isEmpty()) throw new NotFoundException("Vídeo não encontrado!");
        videoRepo.deleteById(id);
    }
}
