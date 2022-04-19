package br.com.challange.videos.videos.form;

import javax.validation.constraints.NotNull;

public class CheckigForm {
    @NotNull
    private Long id;

    public CheckigForm(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
