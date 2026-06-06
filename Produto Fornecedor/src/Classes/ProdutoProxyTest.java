package Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoProxyTest {

    private Comprador credenciado;
    private Comprador naoCredenciado;

    @BeforeEach
    void setUp() {
        Produto p = new Produto();
        p.setCodigo(2001);
        p.setNome("Monitor 4K");
        p.setCategoria("Eletrônicos");
        p.setPrecoVenda(2999.90);
        p.setCustoFornecedor(1800.00);
        p.setNomeFornecedor("DisplayTech");
        BDProduto.addProduto(p);

        credenciado = new Comprador();
        credenciado.setNome("Ana Silva");
        credenciado.setCredenciado(true);

        naoCredenciado = new Comprador();
        naoCredenciado.setNome("João Souza");
        naoCredenciado.setCredenciado(false);
    }

    @Test
    void obterDadosProdutoDeveRetornarListaNaoVazia() {
        IProduto proxy = new ProdutoProxy(2001);
        assertFalse(proxy.obterDadosProduto().isEmpty());
    }

    @Test
    void obterDadosProdutoDeveConterNome() {
        IProduto proxy = new ProdutoProxy(2001);
        assertTrue(proxy.obterDadosProduto().stream()
                .anyMatch(s -> s.contains("Monitor 4K")));
    }

    @Test
    void compradorCredenciadoDeveObterCustoFornecedor() {
        IProduto proxy = new ProdutoProxy(2001);
        var resultado = proxy.obterCustoFornecedor(credenciado);
        assertFalse(resultado.isEmpty());
        assertFalse(resultado.contains("Acesso negado."));
    }

    @Test
    void compradorCredenciadoDeveVerNomeFornecedor() {
        IProduto proxy = new ProdutoProxy(2001);
        var resultado = proxy.obterCustoFornecedor(credenciado);
        assertTrue(resultado.stream().anyMatch(s -> s.contains("DisplayTech")));
    }

    @Test
    void compradorNaoCredenciadoDeveReceberAcessoNegado() {
        IProduto proxy = new ProdutoProxy(2001);
        var resultado = proxy.obterCustoFornecedor(naoCredenciado);
        assertTrue(resultado.contains("Acesso negado."));
    }

    @Test
    void compradorNaoCredenciadoNaoDeveVerCusto() {
        IProduto proxy = new ProdutoProxy(2001);
        var resultado = proxy.obterCustoFornecedor(naoCredenciado);
        assertTrue(resultado.stream().noneMatch(s -> s.contains("1800")));
    }

    @Test
    void proxyEProdutoDevemImplementarMesmaInterface() {
        IProduto proxy   = new ProdutoProxy(2001);
        IProduto produto = new Produto();
        assertInstanceOf(IProduto.class, proxy);
        assertInstanceOf(IProduto.class, produto);
    }

    @Test
    void dadosProdutoDispensamAutenticacao() {
        IProduto proxy = new ProdutoProxy(2001);
        assertDoesNotThrow(() -> proxy.obterDadosProduto());
    }
}