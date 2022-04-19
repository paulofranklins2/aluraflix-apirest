package br.com.challange.videos.videos.controller;


import br.com.challange.videos.videos.form.AtualizacaoVideoForm;
import br.com.challange.videos.videos.form.CheckigForm;
import br.com.challange.videos.videos.form.DeleteForm;
import br.com.challange.videos.videos.form.VideoForm;
import br.com.challange.videos.videos.model.Video;
import br.com.challange.videos.videos.repository.CategoriaRepository;
import br.com.challange.videos.videos.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/home")
    public String homePage(Model model) {
        List<Video> video = videoRepository.findAll();
        model.addAttribute("video", video);
        return "/user/home";
    }

    @GetMapping("/new")
    public String newVideo(VideoForm videoForm) {
        return "/user/new";
    }

    @PostMapping("/novo")
    public String novo(@Valid VideoForm videoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/new";
        }
        Video video = videoForm.converter(categoriaRepository);
        video = videoRepository.save(video);
        return "redirect:/home";
    }

    @GetMapping("/delete")
    public String deletePage(DeleteForm deleteForm) {
        return "/user/delete";
    }

    @PostMapping("/delete")
    public String deleteVideo(@Valid DeleteForm deleteForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/delete";
        }
        videoRepository.deleteById(deleteForm.getId());
        return "redirect:/home";
    }


    @GetMapping("/update")
    public String newVideo(CheckigForm checkigForm) {
        return "/user/update";
    }

    @PostMapping("/update")
    public String updateVideo(@Valid CheckigForm checkigForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/user/update";
        }
        model.addAttribute("id", checkigForm.getId());
        return "/user/updateVideo/";
    }

    @GetMapping("/updatevideo")
    public String updateVideo(@Valid AtualizacaoVideoForm atualizacaoVideoForm) {
        return "/user/updatevideo";
    }

//    @PutMapping("/updateVideo/")
//    public String updateSaving(@Valid AtualizacaoVideoForm atualizacaoVideoForm, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "/user/update";
//        }
////        Video video = atualizacaoVideoForm.atualizar(id, videoRepository);
//        return "redirect:/home";
//    }

}
