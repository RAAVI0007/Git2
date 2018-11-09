package com.myexpertmedics.restservices;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myexpertmedics.beans.UserPOJO;
import com.myexpertmedics.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
//@RequestMapping("/users")
public class UserRestService {

	@Autowired
	private UserRepository userRepository;
	
	List<UserPOJO> usersList = new ArrayList<>();

	@RequestMapping("/users/all")
	private List<UserPOJO> getAllUsers() {
		usersList = new ArrayList<>();
		UserPOJO p1 = new UserPOJO();
		p1.setId(1);
		p1.setFirstname("Ravi");
		UserPOJO p2 = new UserPOJO();
		p2.setId(2);
		p2.setFirstname("Ravi-2");
		usersList.add(p1);
		usersList.add(p2);
		return usersList;
	}
	//@RequestMapping(method = RequestMethod.GET , value = "/users/{id}" )
	@GetMapping("/users/{id}" )
	private  UserPOJO  getUserDetails(@PathVariable String id) {
		System.out.println("inside getUserDetails with ID" + usersList);
		return usersList.stream().filter(pojo -> pojo.getId().equals(id)).collect(Collectors.toList()).get(0) ;
	}
	
	//@RequestMapping(method = RequestMethod.PUT , value = "/users/{id}"  )
	@PutMapping("/users/{id}"  )
	private  void  updateUserDetails(@RequestBody UserPOJO pojo,@PathVariable String id) {
		System.out.println("inside updateUserDetails with ID" + usersList);
		for(UserPOJO p1 :  usersList) {
			if(p1.getId().equals(pojo.getId())) {
				p1.setFirstname(pojo.getFirstname());
			}
		}
		System.out.println("Post Put method::" + usersList);
	}
	
	@RequestMapping(value = "/users/{id}" ,method = RequestMethod.POST)
	private  void  postUserDetails(@RequestBody UserPOJO pojo,@PathVariable String id) {
	 
		System.out.println("inside PostUserDetails with ID===" + pojo.getId());
		System.out.println("inside PostUserDetails with FirstName===" + pojo.getFirstname());
		System.out.println("inside PostUserDetails with LastName ===" + pojo.getLastname());
		System.out.println("inside PostUserDetails with mailID===" + pojo.getEmail());
		System.out.println("inside PostUserDetails with PhNum===" + pojo.getPhnum()); 
		System.out.println("inside PostUserDetails with Reason===" + pojo.getReason());
		System.out.println("inside PostUserDetails with DOB===" + pojo.getDob());
		System.out.println("inside PostUserDetails with Diagnosi===" + pojo.getDiagnosis());
		userRepository.save(pojo) ;
		  //return "Request Processed!"Ø ;
	}
	
	//@RequestMapping(method = RequestMethod.DELETE , value = "/users/{id}")
	@DeleteMapping("/users/{id}")
	private  void  deleteUserDetails(@PathVariable String id) {
		System.out.println("inside deleteUserDetails with ID" + id);
		 usersList.removeIf(pojo -> pojo.getId().equals(id)) ;
		//return usersList.stream().filter(pojo -> !pojo.getId().equalsIgnoreCase(id)).collect(Collectors.toList()).get(0) ;
	} 
}
