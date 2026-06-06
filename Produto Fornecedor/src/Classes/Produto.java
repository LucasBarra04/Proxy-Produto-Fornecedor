package Classes;

import java.util.List;

public class Produto implements IProduto {
    private Integer codigo;
    private String nome;
    private String categoria;
    private Double precoVenda;
    private Double custoFornecedor;
    private String nomeFornecedor;

    public Integer getCodigo()                      { return codigo; }
    public void setCodigo(Integer codigo)           { this.codigo = codigo; }
    public String getNome()                         { return nome; }
    public void setNome(String nome)                { this.nome = nome; }
    public String getCategoria()                    { return categoria; }
    public void setCategoria(String categoria)      { this.categoria = categoria; }
    public Double getPrecoVenda()                   { return precoVenda; }
    public void setPrecoVenda(Double precoVenda)    { this.precoVenda = precoVenda; }
    public Double getCustoFornecedor()              { return custoFornecedor; }
    public void setCustoFornecedor(Double c)        { this.custoFornecedor = c; }
    public String getNomeFornecedor()               { return nomeFornecedor; }
    public void setNomeFornecedor(String n)         { this.nomeFornecedor = n; }

    @Override
    public List<String> obterDadosProduto() {
        return List.of(
                "Código: "    + codigo,
                "Nome: "      + nome,
                "Categoria: " + categoria,
                "Preço: R$"   + precoVenda
        );
    }

    @Override
    public List<String> obterCustoFornecedor(Comprador comprador) {
        return List.of(
                "Fornecedor: "     + nomeFornecedor,
                "Custo: R$"        + custoFornecedor,
                "Margem: R$"       + (precoVenda - custoFornecedor)
        );
    }
}