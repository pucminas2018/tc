package br.com.timecontrol.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;

import br.com.timecontrol.model.TempoInvestido;

@Repository
public class TempoInvestidoRepository{
 
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager manager;
 
	public void salvar(TempoInvestido tempoInvestido){
 
		manager.persist(tempoInvestido);		
	}
 
	@javax.transaction.Transactional
	public void alterar(TempoInvestido tempoInvestido){
 
		manager.merge(tempoInvestido);		
	}

	public TempoInvestido consultarPorCodigo(int codigo){
 
		return manager.find(TempoInvestido.class, codigo);		
	} 
 
	@javax.transaction.Transactional
	public void excluir(int codigo){
 
		TempoInvestido tempoInvestido = this.consultarPorCodigo(codigo);
 
		manager.remove(tempoInvestido);
 
	}
 
	public List<TempoInvestido> listarTodos(){
 
		return manager.createQuery("SELECT ti FROM TempoInvestido ti ", TempoInvestido.class).getResultList();	
	}
 
}