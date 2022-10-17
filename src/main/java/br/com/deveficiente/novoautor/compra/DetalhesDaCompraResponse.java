package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.cupom.CupomResponse;

import java.math.BigDecimal;

public class DetalhesDaCompraResponse {
    private Long id;

    private String nome;

    private String sobrenome;

    private String email;

    private String documento;

    private String telefone;

    private String endereco;

    private String cidade;

    private String complemento;

    private String cep;

    private String pais;

    private String estado;

    private PedidoResponse pedido;

    private CupomResponse cupom;

    private boolean cupomAtivo;

 //   private BigDecimal valorComCupom;


    public DetalhesDaCompraResponse(Compra compra) {

        this.id = compra.getId();
        this.nome = compra.getNome();
        this.sobrenome = compra.getSobrenome();
        this.email = compra.getEmail();
        this.documento = compra.getDocumento();
        this.telefone = compra.getTelefone();
        this.endereco = compra.getEndereco();
        this.cidade = compra.getCidade();
        this.complemento = compra.getComplemento();
        this.cep = compra.getCep();
        this.pais = compra.getPais().getName();
        this.estado = compra.getEstado().getName();
        this.pedido = new PedidoResponse(compra.getPedido());
        if (cupom != null) {
            this.cupom = new CupomResponse(compra.getCupomAplicado().getValidadeAtual(),compra.getCupomAplicado().getPercentualAtual());
            this.cupomAtivo = true;
        //    this.valorComCupom = compra.descontoCupom();

        }


    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public PedidoResponse getPedido() {
        return pedido;
    }

    public CupomResponse getCupom() {
        return cupom;
    }

    public boolean isCupomAtivo() {
        return cupomAtivo;
    }
}
