package br.com.alura.jpa.testes;

import br.com.alura.jpa.modelo.Categoria;
import br.com.alura.jpa.modelo.Movimentacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TesteJPQLMovimentacaoDeUmaCategoria {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        Categoria categoria = new Categoria();
        categoria.setId(2L);

        String jpql = "select m from Movimentacao m join m.categoria c where c = :pCategoria order by m.valor asc";

        TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
        query.setParameter("pCategoria", categoria);

        List<Movimentacao> movimentacoes = query.getResultList();

        for (Movimentacao movimentacao : movimentacoes) {
            System.out.println("Categorias = " + movimentacao.getCategoria());
            System.out.println("Descrição = " + movimentacao.getDescricao());
            System.out.println("Tipo = " + movimentacao.getTipoMovimentacao());
            System.out.println("Valor = " + movimentacao.getValor());
        }
    }


}
