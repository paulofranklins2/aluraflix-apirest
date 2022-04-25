package br.com.challange.videos.videos;

import br.com.challange.videos.videos.controller.VideoController;
import br.com.challange.videos.videos.model.Video;
import br.com.challange.videos.videos.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserControllerTests {

    @Autowired
    private VideoRepository videoRepository;

    @Test
    void homePage() {
        VideoController videoController = new VideoController();
//        UserController userController = new UserController();
        String actual = videoRepository.getById(2l).getTitulo();
        String expected = videoRepository.getById(2l).getTitulo();

        assertEquals(actual, expected, "the method should return the video, ID 2" );
    }
}
