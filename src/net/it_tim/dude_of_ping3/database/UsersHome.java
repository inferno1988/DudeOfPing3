package net.it_tim.dude_of_ping3.database;

// Generated 20 квіт 2011 10:24:31 by Hibernate Tools 3.3.0.GA

import net.it_tim.dude_of_ping3.Md5Hash;
import org.hibernate.criterion.Restrictions;

/**
 * Home object for domain model class Users.
 * 
 * @see net.it_tim.dude_of_dude.database.Users
 * @author Hibernate Tools
 */
public class UsersHome extends DatabaseHome {

	public <T> boolean checkPassword(Class<T> for_class, String login, String password) {
		begin();
		Users user = (Users) getCurrentSession().createCriteria(for_class).add(
				Restrictions.eq("login", login)).uniqueResult();
		commit();
		String currHash = new Md5Hash(password).toString();
		try {
			if (currHash.equals(user.getPassword())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}
}
