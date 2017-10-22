package ing3.fisaa.webbank.pds.repository;

import ing3.fisaa.webbank.pds.models.User;

import java.util.List;

/**
 * @author RubenEdery on 21/10/2017.
 */
public interface IUserService {
	
	/**
	 * Get all users
	 * @return list
	 */
	public List<User> getAllUser();
	
	/**
	 * Search with firstname and return result
	 * @param fistName
	 * @return
	 */
	public List<User> getUserByFirstName(String fistName);
	
	/**
	 * Search with lastname and return result
	 * @param lastName
	 * @return
	 */
	public List<User> getUserByLastName(String lastName);
	
	/**
	 * Search with firstname ans lastname and return result
	 * @param user
	 * @return
	 */
	public User getUserByUser(User user);
	
	/**
	 * Add in list if not existing
	 * @param user
	 * @return result
	 */
	public String addUser(User user);
		
	/**
	 * Delete from list if exist
	 * @param user
	 * @return reuslt
	 */
	public String deleteUser(User user);
	
	public boolean contains(User user);
}

