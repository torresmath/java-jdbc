package br.com.alura.jpa.testes;

import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;

import javax.persistence.*;
import java.util.List;

public class TesteJPQL {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        Conta conta = new Conta();
        conta.setId(2L);

        String jpql = "select m from Movimentacao m where m.conta = :pConta order by m.valor asc";

        TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
        query.setParameter("pConta", conta);

        List<Movimentacao> movimentacoes = query.getResultList();

        for (Movimentacao movimentacao : movimentacoes) {
            System.out.println("Descrição = " + movimentacao.getDescricao());
            System.out.println("Tipo = " + movimentacao.getTipoMovimentacao());
        }
    }
}
