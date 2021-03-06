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
	private IUserService userService;
	
	/**
	 * Method for get all users
	 * @return list of UserDto formated to JSON
	 */
	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<User> GetAllUser() {
		return userService.getAllUser();
	}
	
	/**
	 * Method for get users with firstname
	 * @param firstname
	 * @return list of UserDto formated to JSON
	 */
	@RequestMapping(value = "/getwithfirstname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<User> GetUserByFirstName(@HeaderParam("firstname") String firstname) {
		return userService.getUserByFirstName(firstname);
	}
	
	/**
	 *
	 * @param lastname
	 * @return
	 */
	@RequestMapping(value = "/getwithlastname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<User> GetUserByLastName(@HeaderParam("lastname") String lastname) {
		return userService.getUserByLastName(lastname);
	}
	
	/**
	 *
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	@RequestMapping(value = "/getwithuser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public User GetUserByUser(@HeaderParam("firstname") String firstname, @HeaderParam("lastname") String lastname) {
		return userService.getUserByUser(new User(firstname,lastname));
	}
	
	/**
	 *
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public String addUser(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
		return userService.addUser(new User(firstName, lastName));
	}
	
	/**
	 *
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public String deleteUser(@HeaderParam("firstname") String firstname, @HeaderParam("lastname") String lastname) {
		return userService.deleteUser(new User(firstname, lastname));
	}
	
	/**
	 *
	 * @param firstName
	 * @param lastName
	 * @param newFirstName
	 * @param newLastName
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName,
							 @RequestParam("newfirstname") String newFirstName, @RequestParam("newlastname") String newLastName) {
		return userService.updateUser(new User(firstName, lastName), new User(newFirstName,newLastName));
	}
	
}
