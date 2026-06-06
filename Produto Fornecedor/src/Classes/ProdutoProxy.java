package Classes;

import java.util.List;

public class ProdutoProxy implements IProduto {
    private Produto produto;
    private Integer codigo;

    public ProdutoProxy(Integer codigo) {
        this.codigo = codigo;
    }

    private void carregarProduto() {
        if (produto == null) {
            produto = BDProduto.getProduto(codigo);
            System.out.println("Produto carregado do BD: " + produto.getNome());
        }
    }

    @Override
    public List<String> obterDadosProduto() {
        carregarProduto();
        return produto.obterDadosProduto();
    }

    @Override
    public List<String> obterCustoFornecedor(Comprador comprador) {
        carregarProduto();
        if (comprador.isCredenciado()) {
            return produto.obterCustoFornecedor(comprador);
        }
        System.out.println("Acesso negado: "
                + comprador.getNome() + " não é credenciado.");
        return List.of("Acesso negado.");
    }
}