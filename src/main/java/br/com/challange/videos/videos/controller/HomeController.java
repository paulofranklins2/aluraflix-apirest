package br.com.challange.videos.videos.controller;

import br.com.challange.videos.videos.model.Video;
import br.com.challange.videos.videos.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping
    public String home(Model model) {
        Sort sort = Sort.by("id").ascending();
        PageRequest pageRequest = PageRequest.of(2, 3, sort);

        Page<Video> video = videoRepository.findAll(pageRequest);
        model.addAttribute("video", video);
        return "home";
    }



}
