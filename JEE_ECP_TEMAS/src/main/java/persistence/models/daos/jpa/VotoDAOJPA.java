package persistence.models.daos.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import persistence.models.daos.VotoDAO;
import persistence.models.entities.Voto;

public class VotoDAOJPA extends GenericDAOJPA<Voto,Integer> implements VotoDAO {
	
    public VotoDAOJPA() {
        super(Voto.class);
    }
    
  
    public List<Voto> findVotoByTema(Integer tema_id) {
    	
    	 
        EntityManager entityManager = DAOJPAFactory.getEntityManagerFactory().createEntityManager();
        // Se crea un criterio de consulta
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Voto> criteriaQuery = criteriaBuilder.createQuery(Voto.class);

        // Se establece la clausula FROM
        Root<Voto> root = criteriaQuery.from(Voto.class);

        // Se establece la clausula SELECT
        criteriaQuery.select(root); // criteriaQuery.multiselect(root.get(atr))
        Predicate p1 = criteriaBuilder.equal(root.get("tema").get("Id").as(Integer.class),tema_id);
        
        criteriaQuery.where(p1);

        // Se realiza la query
        TypedQuery<Voto> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(0); // El primero es 0
        typedQuery.setMaxResults(0); // Se realiza la query, se buscan todos
        List<Voto> result = typedQuery.getResultList();
        entityManager.close();
        return result;
    }
    
    public List<Voto> findVotoByNivelEstudios(Integer nivelEstudios) {
    	
   	 
        EntityManager entityManager = DAOJPAFactory.getEntityManagerFactory().createEntityManager();
        // Se crea un criterio de consulta
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Voto> criteriaQuery = criteriaBuilder.createQuery(Voto.class);

        // Se establece la clausula FROM
        Root<Voto> root = criteriaQuery.from(Voto.class);

        // Se establece la clausula SELECT
        criteriaQuery.select(root); // criteriaQuery.multiselect(root.get(atr))
        Predicate p1 = criteriaBuilder.equal(root.get("nivelEstudios").as(Integer.class),nivelEstudios);
        
        criteriaQuery.where(p1);

        // Se realiza la query
        TypedQuery<Voto> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(0); // El primero es 0
        typedQuery.setMaxResults(0); // Se realiza la query, se buscan todos
        List<Voto> result = typedQuery.getResultList();
        entityManager.close();
        return result;
    }

 }