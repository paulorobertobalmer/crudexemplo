/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.manualdaprogramacao.crudexemplo.repositorios;

import br.com.manualdaprogramacao.aula08crud.modelo.Conta;
import br.com.manualdaprogramacao.aula08crud.modelo.Pessoa;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author balmersistemas
 */
@Repository
public interface ContaRepository extends CrudRepository<Conta, Long> {
    
    @Query("select c from Conta c where c.pessoa = :pessoa")
    public List<Conta> listarPorPessoa(@Param("pessoa") Pessoa pessoa);
    
    public List<Conta> findByDescricaoContainingIgnoreCase(String descricao);
    
    
    
}
