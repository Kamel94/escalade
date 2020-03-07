package fr.escalade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.escalade.dao.InfoSiteRepository;
import fr.escalade.dao.SiteRepository;
import fr.escalade.dao.TopoRepository;
import fr.escalade.entities.InfoSite;
import fr.escalade.entities.Site;
import fr.escalade.entities.Topo;

@SpringBootApplication
public class EscaladeApplication implements CommandLineRunner {
	
	@Autowired
	private TopoRepository topoRepository;
	
	@Autowired
	private SiteRepository siteRepository;
	
	@Autowired
	private InfoSiteRepository infoSiteRepository;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(EscaladeApplication.class, args);

		TopoRepository topoDao = ctx.getBean(TopoRepository.class);
		SiteRepository siteDao = ctx.getBean(SiteRepository.class);
		InfoSiteRepository infoSiteDao = ctx.getBean(InfoSiteRepository.class);
	}

	@Override
	public void run(String... args) throws Exception {
		
		topoRepository.save(new Topo("Gorges du Todgha", "Vous trouverez dans les gorges du Todgha de nombreux secteurs de couennes de tous les niveaux (4b à 8c), des grandes voies équipées (jusqu'à 350m) et des parcours de ...", "Maroc", "20/03/2019", "Disponible"));
		topoRepository.save(new Topo("Marocaz", "Voici un des sites phares de la région pour les voies dures. Idéal pour grimper en été. Les voies sont très belles et longues, d’un niveau relativement élevé.", "France (Auvergne-Rhône-Alpes)", "20/03/2019", "Indisponible"));
		topoRepository.save(new Topo("Picellu", "Cette falaise hyper technique est la quintessence de l'escalade verticale en granit !", "France (Corse)", "20/03/2019", "Disponible"));
		
		
		siteRepository.save(new Site("Gorges du Todgha (Todra)", "Maroc", "Province de Ouarzazate", "Tinghir", 300));
		
		infoSiteRepository.save(new InfoSite("Vous trouverez dans les gorges du Todgha de nombreux secteurs de couennes de tous les niveaux (4b à 8c), des grandes voies équipées (jusqu'à 350m) et des parcours de TA dans un calcaire rouge à l'adhérence irréprochable. Principalement des dalles à trous et à gouttes d'eau. On peut y grimper toute l'année. Même en juillet et en août il y a toujours des secteurs à l'ombre et à l'abri de l'affluence touristique. L'ensemble des voies des gorges du Todgha est en cours de rééquipement ; il n'y a pas encore de relais chaînés partout donc en attendant, prévoir maillons et sangles sur quelques itinéraires seulement, clairement identifiés sur notre topo.", "Pour se loger, la route entre Tineghir et les Gorges est bordée de nombreuses auberges. Les auberges proches des gorges permettent d'aller grimper sans voiture. Notre coup de coeur : Dar Tiwira, à 600 m à pied des gorges, est la seule structure familiale." , "Officiel Les amis de l’escalade"));
	}
	

}
