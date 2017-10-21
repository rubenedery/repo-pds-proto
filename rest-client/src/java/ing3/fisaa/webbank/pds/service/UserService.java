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
	
	private List<User> myMockListUser = new ArrayList<User>();
	
	public List<User> initializeList(List<User> myMockListUserer){
		for(int i =0; i<10 ; i++){
			myMockListUserer.add(new User("nom"+i,"prenom"+i));
		}
		return myMockListUserer;
	}
	
	/**
	 * Get all users
	 * @return list
	 */
	@Override
	public List<User> getAllUser() {
		if(myMockListUser!=null){myMockListUser.removeAll(myMockListUser);}
		initializeList(myMockListUser);
		return myMockListUser;
	}
	
	/**
	 * Search with firstname and return result
	 * @param firstName
	 * @return
	 */
	@Override
	public List<User> getUserByFirstName(String firstName) {
		initializeList(myMockListUser);
		List<User> returnList = new ArrayList<User>();
		for(int i=0; i<myMockListUser.size(); i++) {
			if(myMockListUser.get(i).getFirstName().equals(firstName)) {
				returnList.add(myMockListUser.get(i));
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
		List<User> returnList = new ArrayList<User>();
		for(int i=0; i<myMockListUser.size(); i++) {
			if(myMockListUser.get(i).getLastName().equals(lastName)) {
				returnList.add(myMockListUser.get(i));
			}
		}
		return returnList;
	}
	
	/**
	 * Search with firstname ans lastname and return result
	 * @param userDto
	 * @return
	 */
	@Override
	public User getUserByUser(User userDto) {
		for(int i=0; i<myMockListUser.size(); i++) {
			if(myMockListUser.get(i).getFirstName().equals(userDto.getFirstName()) &&
					   myMockListUser.get(i).getLastName().equals(userDto.getLastName())) {
				return myMockListUser.get(i);
			}
		}
		return new User(null,null);
	}
	
	/**
	 * Add in list if not existing
	 * @param userDto
	 * @return result
	 */
	@Override
	public String addUser(User userDto) {
		if(contains(userDto)) {
			return "ERROR : " + userDto.toString()+ " already exist";
		} else {
			myMockListUser.add(userDto);
			return "PUT : " + userDto.toString();
		}
	}
	
	/**
	 * Change in list in exist and if nex not existing
	 * @param userDto
	 * @param newUserDto
	 * @return result
	 */
	@Override
	public String updateUser(User userDto,User newUserDto) {
		if(!contains(userDto)) {
			return "ERROR : " + userDto.toString()+ " not exist";
		} else {
			if(contains(newUserDto)) {
				return "ERROR : " + newUserDto.toString()+  " already exist";
			} else {
				for(int i=0; i<myMockListUser.size(); i++) {
					if(myMockListUser.get(i).getFirstName().equals(userDto.getFirstName()) &&
							   myMockListUser.get(i).getLastName().equals(userDto.getLastName())) {
						myMockListUser.get(i).setFirstName(newUserDto.getFirstName());
						myMockListUser.get(i).setLastName(newUserDto.getLastName());
					}
				}
				return "POST : " + userDto.toString() + " TO " + newUserDto.toString();
			}
		}
	}
	
	/**
	 * Delete from list if exist
	 * @param userDto
	 * @return reuslt
	 */
	@Override
	public String deleteUser(User userDto) {
		if(!contains(userDto)) {
			return "ERROR : " + userDto.toString() + " not exist";
		} else {
			for(int i=0; i<myMockListUser.size(); i++) {
				if(myMockListUser.get(i).getFirstName().equals(userDto.getFirstName()) &&
						   myMockListUser.get(i).getLastName().equals(userDto.getLastName())) {
					myMockListUser.remove(i);
				}
			}
			return "DELETE : " + userDto.toString();
		}
	}
	
	/**
	 * Looking if exist in list
	 * @param userDto
	 * @return true/false
	 */
	@Override
	public boolean contains(User userDto) {
		for(int i=0; i<myMockListUser.size(); i++) {
			if(myMockListUser.get(i).getFirstName().equals(userDto.getFirstName()) &&
					   myMockListUser.get(i).getLastName().equals(userDto.getLastName())) {
				return true;
			}
		}
		return false;
	}
	
	
}
