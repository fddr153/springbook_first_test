package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.Pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import jdk.jshell.spi.ExecutionControlProvider;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentsApplication implements CommandLineRunner {

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;

	Log LOGGER= LogFactory.getLog(FundamentsApplication.class);

	public FundamentsApplication(@Qualifier("componentTwoImplement") ComponentDependency myComponentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService){
		this.componentDependency=myComponentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentsApplication.class, args);
	}
	private void clasesAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail());
		LOGGER.error("Esto es un error");
	}
	private void getInformationJpqlFromUser(){
//		LOGGER.info("User found: "+userRepository.findByUserEmail("nolan.batman@gmail.com")
//				.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));
//		userRepository.findAndSort("nolan", Sort.by("name").descending())
//				.stream().forEach(user->LOGGER.info("User like mail: "+user));
//		userRepository.findByName("Christian").stream()
//				.forEach(user->LOGGER.info("User by name: "+user));
//		LOGGER.info("User by email and name: "+userRepository.findByEmailAndName("joker@gmail.com","Joaquin")
//				.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));
//		userRepository.findByNameLike("%e%").stream().forEach(user->LOGGER.info("User by nameLike"+user));
//
//		userRepository.findByNameOrEmail("Heath",null).stream().forEach(user->LOGGER.info("User by name OR mail"+user));
//
//		userRepository.findByBirthDateBetween(LocalDate.of(1970,1,1),LocalDate.of(1980,1,1)).stream().forEach(user->LOGGER.info("User by datebetween"+user));
//
//		userRepository.findByNameLikeOrderByNameDesc("%e%").stream().forEach(user->LOGGER.info("User by namelikeOrderby "+user));
		LOGGER.info("Users by getAllByBirthDateAndEmail "+userRepository.getAllByBirthDateAndEmail(LocalDate.of(1967,3,20),"joker@gmail.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));
	}
	private void saveUsersInDataBase(){
		User user= new User("Joaquin","joker@gmail.com", LocalDate.of(1967,3,20));
		User user2= new User("Heath","nolan.joker@gmail.com", LocalDate.of(1976,7,22));
		User user3= new User("Christian","nolan.batman@gmail.com", LocalDate.of(1970,4,15));
		User user4= new User("Ben","snyder.batman@gmail.com", LocalDate.of(1968,5,24));
		User user5= new User("Michael","burton.batman@gmail.com", LocalDate.of(1956,8,23));
		User user6= new User("Jack","burton.joker@gmail.com", LocalDate.of(1953,9,12));
		User user7= new User("Robert","reeves.batman@gmail.com", LocalDate.of(1978,10,17));

		List<User> list= Arrays.asList(user,user2,user3,user4,user5,user6,user7);

		userRepository.saveAll(list);
	}
	private void saveWithErrorTransactional(){
		User test1= new User("TestTransactional1","TestTransactional1@gmail.com",LocalDate.now());
		User test2= new User("TestTransactional2","TestTransactional2@gmail.com",LocalDate.now());
		User test3= new User("TestTransactional3","TestTransactional3@gmail.com",LocalDate.now());
		User test4= new User("TestTransactional4","TestTransactional4@gmail.com",LocalDate.now());
		try {
			List<User> users = Arrays.asList(test1, test2, test3, test4);
			userService.saveTransactional(users);
		}catch(Exception e){
			LOGGER.error("Error de tipo "+e.getMessage());
		}
		userService.getAllUsers().stream().forEach(user->LOGGER.info("Usuario: "+user));
	}
	@Override
	public void run(String... args) throws Exception {
		//clasesAnteriores();
		saveUsersInDataBase();
		//getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}
}
