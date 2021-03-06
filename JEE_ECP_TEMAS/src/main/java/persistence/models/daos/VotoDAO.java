package persistence.models.daos;

import java.util.List;

import persistence.models.daos.GenericDAO;
import persistence.models.entities.Tema;
import persistence.models.entities.Voto;

public interface VotoDAO extends GenericDAO<Voto, Integer>{
	
	List<Voto> findVotoByTema(Tema tema);
	void deleteVotoByTemaID(Tema tema);
	List<Voto> findVotoByNivelEstudios(Integer nivelEstudios);

}