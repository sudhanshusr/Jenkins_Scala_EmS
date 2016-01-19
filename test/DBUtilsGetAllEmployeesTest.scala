import models.DBUtils
import org.scalatestplus.play._
import models.PersonModel

class DBUtilsGetAllEmployeesTest extends PlaySpec {

 "getAllEmployees method" must {
     "Return all employees" in {
           
           //data from db
	   var ListEmp:List[PersonModel] = DBUtils.getallperson;
     
           //hard-coded data to check test-condition
           val empAll = Map( 13534->"Sud");

     
           val matchedResult = for (emp <- ListEmp if (emp.first_name.equals(empAll(emp.id.toInt)))) yield emp
      
      //displaying fetched data
      //ListEmp.foreach(e => println(e.first_name))
      //println("------------")
      //matchedResult.foreach(m => println(m.))
     

      //pass or fail test
      assertResult(ListEmp.length)(matchedResult.length)
      
     
     } 
     
   }  
  
  
}
		 
		 
		 
		 
		 
		 
		 
		 
		 
       
	   
	   
	   