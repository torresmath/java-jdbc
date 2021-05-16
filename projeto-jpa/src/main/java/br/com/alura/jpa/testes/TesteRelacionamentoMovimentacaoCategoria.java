package br.com.alura.jpa.testes;

import br.com.alura.jpa.modelo.Categoria;
import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;
import br.com.alura.jpa.modelo.TipoMovimentacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TesteRelacionamentoMovimentacaoCategoria {

    public static void main(String[] args) {
        Categoria categoria = new Categoria("Viagem");
        Categoria categoria2 = new Categoria("Negócios");

        Conta conta = new Conta();
        conta.setId(2L);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setDescricao("Viagem à SP");
        movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao.setData(LocalDateTime.now());
        movimentacao.setValor(new BigDecimal("300.0"));
        List<Categoria> categorias = Arrays.asList(categoria, categoria2);
        movimentacao.setConta(conta);

        movimentacao.setCategoria(categorias);

        Movimentacao movimentacao2 = new Movimentacao();
        movimentacao2.setDescricao("Viagem à RJ");
        movimentacao2.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao2.setData(LocalDateTime.now());
        movimentacao2.setValor(new BigDecimal("400.0"));
        movimentacao2.setCategoria(categorias);
        movimentacao2.setConta(conta);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(categoria);
        em.persist(categoria2);
        em.persist(movimentacao);
        em.persist(movimentacao2);

        em.getTransaction().commit();
        em.close();
    }
}
