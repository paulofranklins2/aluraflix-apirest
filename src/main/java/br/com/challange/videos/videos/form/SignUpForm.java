package br.com.challange.videos.videos.form;

import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpForm {
    @NotEmpty
    @Size(min = 5)
    private String username;
    @NotEmpty
    @Size(min = 5)
    private String password;

    private byte enabled;

    public SignUpForm(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = 1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }





}
