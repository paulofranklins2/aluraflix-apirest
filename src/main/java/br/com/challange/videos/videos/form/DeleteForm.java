package br.com.challange.videos.videos.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DeleteForm {
    @NotNull
    @Positive
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeleteForm(Long id){
        this.id = id;
    }
}
