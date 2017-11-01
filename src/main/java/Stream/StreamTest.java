package Stream;


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
	//	map1.forEach((k,v)-> System.out.println(k + " " + v));
		
		Map<String,List<Employee>> map2= list.stream().collect(Collectors.groupingBy(o->o.getSalary()>=1000?"Greater":"Less"));
		//map2.forEach((k,v)->System.out.println(k + " " + v));
		
		Map<String,List<Employee>> map3= list.stream().collect(Collectors.groupingBy(o->{
			 if(o.getSalary() > 100 && o.getSalary() < 1000){
	                return "Not a big deal";
	            }else if(o.getSalary() >= 1000 && o.getSalary() < 5000){
	                return "Is a big deal";
	            }else{
	                return "Real Man";
	            }
		}));
	//	map3.forEach((k,v)->System.out.println(k+" "+v));
	}
	
	 @Test
	    public void testFlatMap(){
	        Map<String, List<Employee>> bMap = list.stream().collect(Collectors.groupingBy(o -> o.getSalary()>=1000?"Greater than/equal to 1000":"Less than 1000"));
	        List<Employee> listOfEmp = bMap.values().stream().flatMap(employees -> {
	            //System.out.println(12345);
	            return employees.stream();}).collect(Collectors.toList());

	        //listOfEmp.forEach(System.out::println);
	        }

	 @Test
	 public void testChange(){
		 list.stream().forEach(e->e.setAge(e.getAge()+4));
		 //list.forEach(System.out::println);
	 }
	
	 @Test
	 public void testMatch(){
		 boolean allSalary=list.stream().allMatch(e->e.getSalary()>1000);
		 System.out.println(allSalary);
		 boolean any= list.stream().anyMatch(e->e.getSalary()>100);
		 System.out.println(any);
		 boolean none=list.stream().noneMatch(e->e.getAge()<40);
		 System.out.println(none);
	 }	
	 
	 @Test
	 public void testPeek(){
		// list.stream().peek(e->e.setAge(e.getAge()+10)).filter(e->e.getAge()>50).peek(e->e.setAge(10)).forEach(System.out::println);
	 }
	 
	 @Test
	 public void testDistinctCount(){
		 long count= list.stream().distinct().count();
		 System.out.println(count);
	 }
	 
	 @Test 
	 public void testSkipLimit(){
		 list.stream().skip(2).limit(2).forEach(System.out::println);
	 }
	 
	 @Test
	 public void testReduce(){
		 Employee emp=list.stream().reduce((e1,e2)->{
			 e1.setSalary(e1.getSalary()+e2.getSalary());
			 e1.setAge(e1.getAge()+e2.getAge());
			 e1.setName(e1.getName()+e2.getName());
			 return e1;
		 }).get();
		System.out.println(emp);
	 }
	 
	 @Test
	 public void test(){
		 LinkedList<Employee> newList = list.stream().collect(LinkedList::new, (list, employee) -> {list.add(employee);}, (list, objects2) -> {list.addAll(objects2);});
	 }
}

