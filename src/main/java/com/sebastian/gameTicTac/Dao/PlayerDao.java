package com.sebastian.gameTicTac.Dao;

import com.sebastian.gameTicTac.Model.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.io.Console;
import java.security.Principal;
import java.util.List;

@Repository
public class PlayerDao {
    SessionFactory sessionFactory;

    @Autowired
    public PlayerDao(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    /**
     * This method is used to put new Player record into database
     * @param player is a POJO object that will be put into database
     */
    public void addPlayer(Player player){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(player);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    /**
     * This method is used to get player by id
     * @param id is am id of the Player object
     * @return
     */
    public Player getPlayerById(int id){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Player player = null;
        try {
            tx = session.beginTransaction();
            player =  (Player) session.get(Player.class, id);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return player;
    }

    public Player getPlayerByName(String name){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Player player = null;
        try {
            tx = session.beginTransaction();

            String hql = "FROM Player P WHERE P.name = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name",name);
            List<Player> results = query.list();
            if(results.size()>0){
                player = results.get(0);
            }
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return player;
    }

    public List<Player> getAllPlayers(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List players = null;
        try {
            tx = session.beginTransaction();
            players = session.createQuery("FROM Player").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return players;
    }

    public void updatePlayer(Player player){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(player);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void deletePlayer(int id){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Player player = (Player) session.get(Player.class, id);
            session.delete(player);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
