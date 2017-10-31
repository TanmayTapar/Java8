package Stream;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import Java8_Features.test.java8.practice.Employee;

public class StreamTest {
	List<Employee> list =new ArrayList<Employee>();
	
	@Before
	public void init(){
		Employee e1= new Employee(20, "n1", 800);
		list.add(e1);
		Employee e2= new Employee(30, "n2", 2000);
		list.add(e2);
		Employee e3= new Employee(40, "n3", 3000);
		list.add(e3);
		Employee e4= new Employee(50, "n4", 3000);
		list.add(e4);
		Employee e5= new Employee(50, "n5", 5000);
		list.add(e5);	
		
	}
	
	@Test
	public void testMinMax(){
		Employee youngestEmp= list.stream().min(Comparator.comparing(Employee::getAge)).get();
		//System.out.println(youngestEmp);
		Employee eldestEmp= list.stream().max(Comparator.comparing(Employee::getAge)).get();
		//System.out.println(eldestEmp);
		//System.out.println("/////////////////////////");
	}
	
	@Test
	public void testSorting(){
	//	System.out.println("/////////////////////////");
	/*	list.stream().sorted(
				(o1,o2)->{
					return o2.getAge() - o1.getAge();
					}).forEach(System.out::println);
	//	list.stream().sorted(Comparator.comparing(Employee::getSalary).reversed().thenComparing(Comparator.comparing(Employee::getAge))).forEach(System.out::println);
		System.out.println("/////////////////////////");*/
	}
	
	@Test
	public void testFilter(){
	//	list.stream().filter(i->i.getAge()>30).forEach(System.out::println);
	}
	
	@Test
	public void testMap(){
		int i= list.stream().mapToInt(e->e.getAge()).sum();
	//	System.out.println("Total Sum is: "+ i);
		
		String name =list.stream().map(e->e.getName()).collect(Collectors.joining(" "));
	//	System.out.println(name);
	}
	
	@Test
	public void testGroupBy(){
		Map<Boolean,List<Employee>> map1= list.stream().collect(Collectors.partitioningBy(o->o.getSalary()>1000));
		map1.forEach((k,v)-> System.out.println(k + " " + v));
		
		Map<String,List<Employee>> map2= list.stream().collect(Collectors.groupingBy(o->o.getSalary()>=1000?"Greater":"Less"));
		map2.forEach((k,v)->System.out.println(k + " " + v));
	}
	
	
	
}
