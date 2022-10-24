package br.com.deveficiente.novoautor.compra;

public class EnderecoCompraResponse {

    private String cidade;
    private String endereco;
    private String complemento;
    private String cep;
    public EnderecoCompraResponse(Compra novoCompra) {
        this.cidade = novoCompra.getCidade();
        this.endereco = novoCompra.getEndereco();
        this.complemento = novoCompra.getComplemento();
        this.cep = novoCompra.getCep();
    }
    public String getCidade() {
        return cidade;
    }


    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }
}
