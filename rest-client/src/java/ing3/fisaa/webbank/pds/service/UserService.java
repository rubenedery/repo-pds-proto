package ing3.fisaa.webbank.pds.service;

import ing3.fisaa.webbank.pds.models.User;
import ing3.fisaa.webbank.pds.repository.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RubenEdery on 21/10/2017.
 */
@Service
public class UserService implements IUserService  {
	
	private List<User> mockList = new ArrayList<User>();
	
	public List<User> initializeList(List<User> mockListeInitializer){
		if(mockList!=null){mockList.removeAll(mockList);}
		for(int i =0; i<10 ; i++){
			mockListeInitializer.add(
					new User("nom"+i,"prenom"+i)
			);
		}
		return mockListeInitializer;
	}
	
	/**
	 * Get all users
	 * @return list
	 */
	@Override
	public List<User> getAllUser() {
		initializeList(mockList);
		return mockList;
	}
	
	/**
	 * Search with firstname and return result
	 * @param firstName
	 * @return
	 */
	@Override
	public List<User> getUserByFirstName(String firstName) {
		initializeList(mockList);
		List<User> returnList = new ArrayList<User>();
		for(int i=0; i<mockList.size(); i++) {
			if(mockList.get(i).getFirstName().equals(firstName)) {
				returnList.add(mockList.get(i));
			}
		}
		return returnList;
	}
	
	/**
	 * Search with lastname and return result
	 * @param lastName
	 * @return
	 */
	@Override
	public List<User> getUserByLastName(String lastName) {
		initializeList(mockList);
		List<User> returnList = new ArrayList<User>();
		for(int i=0; i<mockList.size(); i++) {
			if(mockList.get(i).getLastName().equals(lastName)) {
				returnList.add(mockList.get(i));
			}
		}
		return returnList;
	}
	
	/**
	 * Search with firstname ans lastname and return result
	 * @param user
	 * @return
	 */
	@Override
	public User getUserByUser(User user) {
		initializeList(mockList);
		for(int i=0; i<mockList.size(); i++) {
			if(mockList.get(i).getFirstName().equals(user.getFirstName()) &&
					   mockList.get(i).getLastName().equals(user.getLastName())) {
				return mockList.get(i);
			}
		}
		return new User(null,null);
	}
	
	/**
	 * Add in list if not existing
	 * @param user
	 * @return result
	 */
	@Override
	public String addUser(User user) {
		initializeList(mockList);
		if(contains(user)) {
			return "ERROR : " + user.toString()+ " already exist";
		} else {
			mockList.add(user);
			return "PUT : " + user.toString();
		}
	}
	
	
	/**
	 * Delete from list if exist
	 * @param user
	 * @return reuslt
	 */
	@Override
	public String deleteUser(User user) {
		if(!contains(user)) {
			return "ERROR : " + user.toString() + " not exist";
		} else {
			for(int i=0; i<mockList.size(); i++) {
				if(mockList.get(i).getFirstName().equals(user.getFirstName()) &&
						   mockList.get(i).getLastName().equals(user.getLastName())) {
					mockList.remove(i);
				}
			}
			return "DELETE : " + user.toString();
		}
	}
	
	/**
	 * Looking if exist in list
	 * @param user
	 * @return true/false
	 */
	@Override
	public boolean contains(User user) {
		initializeList(mockList);
		for(int i=0; i<mockList.size(); i++) {
			if(mockList.get(i).getFirstName().equals(user.getFirstName()) &&
					   mockList.get(i).getLastName().equals(user.getLastName())) {
				return true;
			}
		}
		return false;
	}
	
	
}
