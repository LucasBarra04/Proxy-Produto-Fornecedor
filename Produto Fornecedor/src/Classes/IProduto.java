package Classes;

import java.util.List;

public interface IProduto {
    List<String> obterDadosProduto();
    List<String> obterCustoFornecedor(Comprador comprador);
}