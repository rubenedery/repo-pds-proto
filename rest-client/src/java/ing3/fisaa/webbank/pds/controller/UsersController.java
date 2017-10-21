package ing3.fisaa.webbank.pds.controller;

import ing3.fisaa.webbank.pds.models.User;
import ing3.fisaa.webbank.pds.repository.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author RubenEdery on 21/10/2017.
 */

@RestController
@RequestMapping(value = "/users")
public class UsersController {
	
	@Autowired
	private IUserService userDtoService;
	
	/**
	 * Method for get all users
	 * @return list of UserDto formated to JSON
	 */
	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<User> GetAllUser() {
		return userDtoService.getAllUser();
	}
	
	/**
	 * Method for get users with firstname
	 * @param firstname
	 * @return list of UserDto formated to JSON
	 */
	@RequestMapping(value = "/getbyfirstname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<User> GetUserByFirstName(@HeaderParam("firstname") String firstname) {
		return userDtoService.getUserByFirstName(firstname);
	}
	
	/**
	 * Method for get users with lastname
	 * @param lastname
	 * @return list of UserDto formated to JSON
	 */
	@RequestMapping(value = "/getbylastname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<User> GetUserByLastName(@HeaderParam("lastname") String lastname) {
		return userDtoService.getUserByLastName(lastname);
	}
	
	/**
	 * Method for get users with firstname and lastname
	 * @param firstname
	 * @param lastname
	 * @return list of UserDto formated to JSON
	 */
	@RequestMapping(value = "/getbyuser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public User GetUserByUser(@HeaderParam("firstname") String firstname, @HeaderParam("lastname") String lastname) {
		return userDtoService.getUserByUser(new User(firstname,lastname));
	}
	
	/**
	 * Method for PUT (add) user in database (list)
	 * @param firstName
	 * @param lastName
	 * @return success or error
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public String addUser(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
		return userDtoService.addUser(new User(firstName, lastName));
	}
	
	/**
	 * Method for DELETE user from database (list)
	 * @param firstname
	 * @param lastname
	 * @return success or error
	 */
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public String deleteUser(@HeaderParam("firstname") String firstname, @HeaderParam("lastname") String lastname) {
		return userDtoService.deleteUser(new User(firstname, lastname));
	}
	
	/**
	 * Method for POST (modify) user from database (list)
	 * @param firstName
	 * @param lastName
	 * @param newFirstName
	 * @param newLastName
	 * @return success or error
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName,
							 @RequestParam("newfirstname") String newFirstName, @RequestParam("newlastname") String newLastName) {
		return userDtoService.updateUser(new User(firstName, lastName), new User(newFirstName,newLastName));
	}
	
}
