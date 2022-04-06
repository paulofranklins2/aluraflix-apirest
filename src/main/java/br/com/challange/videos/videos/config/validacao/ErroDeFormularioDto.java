package br.com.challange.videos.videos.config.validacao;

public class ErroDeFormularioDto {

    private String campo;
    private String erro;

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }

    public ErroDeFormularioDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }
}
