/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.helper;

import dao.pojo.Joueur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jcebollado
 */
public class ManagerJoueur extends ManagerBase {
    public ManagerJoueur(EntityManager em){
        super(em);
    }
    
    public void update(Joueur joueur) {
        this.getManager().merge(joueur);
    }
    
    public void delete(Joueur joueur) {
        this.getManager().remove(joueur);
    }
    
    public Joueur find(Integer joueurId) {
        Query query = this.getManager().createQuery("select j from Joueur j where j.joueurId=:id");
        query.setParameter("id", joueurId);
        
        return (Joueur)query.getSingleResult();
    }
    
    public List<Joueur> findByPseudo(String pseudo) {
        Query query = this.getManager().createQuery("select j from Joueur j where j.pseudo=:pseudo");
        query.setParameter("pseudo", pseudo);
        
        return query.getResultList();
    }
    
    public List<Joueur> findByClassment(int nb) {
        Query query = this.getManager().createQuery("select j from Joueur j order by j.nbVictoire / j.nbPartie limit :top");
        query.setParameter("top", nb);
        
        return query.getResultList();
    }
}
