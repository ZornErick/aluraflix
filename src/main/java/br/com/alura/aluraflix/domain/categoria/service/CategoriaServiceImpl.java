package br.com.alura.aluraflix.domain.categoria.service;

import br.com.alura.aluraflix.domain.categoria.Categoria;
import br.com.alura.aluraflix.domain.categoria.CategoriaForm;
import br.com.alura.aluraflix.domain.categoria.CategoriaRepo;
import br.com.alura.aluraflix.domain.categoria.CategoriaUpdateForm;
import br.com.alura.aluraflix.util.crud.Form;
import br.com.alura.aluraflix.util.crud.UpdateForm;
import br.com.alura.aluraflix.util.exception.NotFoundException;
import br.com.alura.aluraflix.domain.video.Video;
import br.com.alura.aluraflix.domain.video.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepo categoriaRepo;
    private final VideoRepo videoRepo;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepo categoriaRepo, VideoRepo videoRepo) {
        this.categoriaRepo = categoriaRepo;
        this.videoRepo = videoRepo;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria findById(Long id) {
        Optional<Categoria> categoria = categoriaRepo.findById(id);
        if(categoria.isEmpty()) throw new NotFoundException("Categoria não encontrada!");
        return categoria.get();
    }

    @Override
    public Categoria create(Form<Categoria> form) {
        CategoriaForm categoriaForm = (CategoriaForm) form;
        return categoriaRepo.save(categoriaForm.convert());
    }

    @Override
    public Categoria update(Long id, UpdateForm<Categoria> updateForm) {
        CategoriaUpdateForm categoriaUpdateForm = (CategoriaUpdateForm) updateForm;
        Optional<Categoria> categoria = categoriaRepo.findById(id);
        if(categoria.isEmpty()) throw new NotFoundException("Categoria não encontrada!");
        return categoriaUpdateForm.update(categoria.get());
    }

    @Override
    public void delete(Long id) {
        if(categoriaRepo.findById(id).isEmpty()) throw new NotFoundException("Categoria não encontrada!");
        categoriaRepo.deleteById(id);
    }

    @Override
    public List<Video> findVideosByCategoriaId(Long categoriaId) {
        return videoRepo.findAllByCategoria_Id(categoriaId);
    }
}
