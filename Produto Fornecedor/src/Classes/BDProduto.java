package Classes;

import java.util.HashMap;
import java.util.Map;

public class BDProduto {
    private static Map<Integer, Produto> produtos = new HashMap<>();

    public static Produto getProduto(Integer codigo) {
        return produtos.get(codigo);
    }

    public static void addProduto(Produto produto) {
        produtos.put(produto.getCodigo(), produto);
    }
}