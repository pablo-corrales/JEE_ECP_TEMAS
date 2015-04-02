package views.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import persistence.models.entities.Tema;

import persistence.models.utils.NivelEstudios;
import controllers.VotarController;

public class VotarBean extends ViewBean {
	
	private static final long serialVersionUID = 1L;
	private VotarController votarController = this.getControllerFactory().getVotarController();
	
	private List<Tema> temas;
	private Integer idTema;
	
	private NivelEstudios[] nivelEstudios = {NivelEstudios.Basico, NivelEstudios.ESO, NivelEstudios.Master};
	private Integer[] puntuacion = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
	private Integer length = nivelEstudios.length;
	
	private String pregunta;
	private String estudio;
	private Integer puntaje;
	private String ip;
	
		
	public VotarBean() {
		super();
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}
	
	public String getEstudio() {
		return this.estudio;
	}

	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}
	
	public Integer getPuntaje() {
		return this.puntaje;
	}

	public List<String> getNivelEstudios() {
		List<String> nivelEstudios = new ArrayList<String>();
		for(NivelEstudios estudios : NivelEstudios.values()){
			nivelEstudios.add(estudios.toString());
		}
		return nivelEstudios;
	}

	public Integer[] getPuntuacion() {
		return puntuacion;
	}

	public Integer getLength() {
		return length;
	}
	
	public List<Tema> getTemas() {
		return temas;
	}
	
	public Integer getIdTema() {
		return idTema;
	}

	public void setIdTema(Integer idTema) {
		this.idTema = idTema;
		this.updateVote();
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		if(this.ip == null){
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			this.ip = req.getHeader("X-FORWARDED-FOR");
			if( this.ip == null ) {
			    this.ip = req.getRemoteAddr();
			}
		}
		return this.ip;
	}

	@PostConstruct
    public void update() {
		this.temas = votarController.listarTemas();
        this.temas.add(0, new Tema(-1, "Temas...", ""));
        this.idTema = -1;
        this.updateVote();
    }

	private void updateVote() {
        pregunta = getPregunta(idTema); 
        puntaje = puntuacion[0];
        
    }
	
	private String getPregunta(int idTema){
		for(Tema tema: temas){
			if(idTema==tema.getId())
				return tema.getPregunta();
		}
		return "";
	}
	
	public String process(){
		ip = this.getIp();
		List<String> estudiosString = this.getNivelEstudios();
		Integer nivelEstudios = estudiosString.indexOf(this.estudio);
		this.votarController.votar(idTema, nivelEstudios, puntaje, ip);
		return "home";
	}
   
}