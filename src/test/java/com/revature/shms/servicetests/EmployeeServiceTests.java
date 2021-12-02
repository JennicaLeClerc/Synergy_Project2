package com.revature.shms.servicetests;

import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.enums.EmployeeType;
import com.revature.shms.exceptions.EntityNotFound;
import com.revature.shms.models.Cleaning;
import com.revature.shms.models.Employee;
import com.revature.shms.models.Room;
import com.revature.shms.repositories.EmployeeRepository;
import com.revature.shms.repositories.RoomRepository;
import com.revature.shms.services.CleaningService;
import com.revature.shms.services.EmployeeService;
import com.revature.shms.services.RoomService;
import com.revature.shms.services.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

	@Mock EmployeeRepository employeeRepository;
	@Mock CleaningService cleaningService;
	@Mock UserService userService;
	@Mock RoomService roomService;
	@Mock RoomRepository roomRepository;
	@InjectMocks EmployeeService employeeService;


	@Test
	public void createEmployeeTest(){
		Employee employee = new Employee();
		when(employeeRepository.save(any())).thenReturn(employee);
		Assertions.assertEquals(employee,employeeService.createEmployee(employee));
	}

	@Test
	public void loginEmployeeTests() {
		Employee employee = new Employee();
		employee.setUsername("RPH");
		employee.setPassword("RPH123");
		when(employeeRepository.findByUsername(any())).thenReturn(java.util.Optional.of(employee));
		Assertions.assertEquals(employee,employeeService.loginEmployee("RPH","RPH123"));
		try {
			Exception exception = Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, (Executable) employeeService.loginEmployee("RPH","123"));
			Assertions.assertTrue(exception.getMessage().contains("Incorrect username/password"));
		} catch (Exception ignored){}

	}

	@Test
	public void addRoomTest(){
		Room room = new Room();
		when(roomRepository.save(any())).thenReturn(room);
		Assertions.assertEquals(room,employeeService.addRoom(room));
	}
	@Test
	public void addRoomsTest(){
		List<Room> rooms = new ArrayList<>();
		rooms.add(new Room());
		rooms.add(new Room());
		rooms.add(new Room());
		rooms.add(new Room());
		rooms.add(new Room());
		rooms.add(new Room());
		when(roomRepository.saveAll(any())).thenReturn(rooms);
		Assertions.assertEquals(rooms,employeeService.addRooms(rooms));
	}

	@Test
	public void getAllEmployeesTest(){
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee());
		employeeList.add(new Employee());
		when(employeeRepository.findAllByOrderByEmployeeType()).thenReturn(employeeList);
		Assertions.assertEquals(employeeList, employeeService.getAllEmployees());
	}

	@Test
	public void getAllEmployeesByTypeTest(){
		EmployeeType employeeType = EmployeeType.RECEPTIONIST;
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee());
		employeeList.add(new Employee());
		when(employeeRepository.findByEmployeeType(any())).thenReturn(employeeList);
		Assertions.assertEquals(employeeList, employeeService.getAllEmployeesByType(employeeType));
	}

	@Test
	public void getEmployeeByIDTest() throws EntityNotFound {
		Employee employee = new Employee();
		when(employeeRepository.findByEmployeeID(anyInt())).thenReturn(java.util.Optional.of(employee));
		Assertions.assertEquals(employee, employeeService.getEmployeeByID(0));
	}

	@Test
	public void getEmployeeByUserNameTest() throws EntityNotFound {
		String username = "username";
		Employee employee = new Employee();
		when(employeeRepository.findByUsername(any())).thenReturn(java.util.Optional.of(employee));
		Assertions.assertEquals(employee, employeeService.getEmployeeByUserName(username));
	}

	@Test
	public void employeeCleaningToDoTest(){
		Employee employee = new Employee();
		List<Cleaning> cleaningList = new ArrayList<>();
		cleaningList.add(new Cleaning());
		cleaningList.add(new Cleaning());
		when(cleaningService.GetAllCleaningsByEmployee(any())).thenReturn(cleaningList);
		Assertions.assertEquals(cleaningList, employeeService.employeeCleaningToDo(employee));
	}

	@Test
	public void settersGettersTest(){
		EmployeeService employeeService = new EmployeeService();

		EmployeeRepository employeeRepository = null;
		RoomRepository roomRepository = null;
		CleaningService cleaningService = new CleaningService();
		RoomService roomService = new RoomService();
		UserService userService = new UserService();

		employeeService.setCleaningService(cleaningService);
		employeeService.setRoomService(roomService);
		employeeService.setUserService(userService);
		employeeService.setEmployeeRepository(employeeRepository);
		employeeService.setRoomRepository(roomRepository);

		Assertions.assertEquals(cleaningService,employeeService.getCleaningService());
		Assertions.assertEquals(roomService,employeeService.getRoomService());
		Assertions.assertEquals(userService,employeeService.getUserService());
		Assertions.assertNull(employeeService.getEmployeeRepository());
		Assertions.assertNull(employeeService.getRoomRepository());
	}
}
