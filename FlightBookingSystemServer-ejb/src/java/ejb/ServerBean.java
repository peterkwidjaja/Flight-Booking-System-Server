/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Peter
 */
@Stateless
public class ServerBean implements ServerBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addUser(String username, String password, int contactNo, String email) {
        UserEntity temp = em.find(UserEntity.class, username);
        if(temp==null){
            UserEntity newUser = new UserEntity();
            newUser.create(username, password, contactNo, email);
            em.persist(newUser);
        }
    }

    @Override
    public boolean delUser(String username) {
        UserEntity temp = em.find(UserEntity.class, username);
        if(temp!=null){
            em.remove(temp);
            return true;
        }
        return false;
    }

}
