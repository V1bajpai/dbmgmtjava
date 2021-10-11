package dbmgmt;
import java.sql.*;  

public class Databasetesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{  
			//1. Create connection
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/classicmodels","root","1234"); 
			//********************************************url-------------------------------------username--pwd
			
			//2. Create Statement
			Statement stmt=con.createStatement();  
			
			//3. Execute statement
			ResultSet rs=stmt.executeQuery("select * from customers");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3) + "    "+rs.getBigDecimal(13));  
			
			System.out.println("=====================================================================================");
			
			//4. First way to find-nth-highest-creditlimit
			String query1= "select * from customers A where 4 = ( select count(distinct creditLimit) from customers B where A.creditLimit<=B.creditLimit )";
			ResultSet rs1= stmt.executeQuery(query1);
			while(rs1.next()) {
				System.out.println(rs1.getInt(1)+"  "+rs1.getString(2)+"  "+rs1.getString(3) + "    "+rs1.getBigDecimal(13));
			}
			
			System.out.println("=====================================================================================");

			//5. Second way to find-nth-highest-creditlimit
			String query2= "SELECT * FROM customers ORDER BY creditLimit DESC LIMIT 2,1";
			ResultSet rs2= stmt.executeQuery(query2);
			while(rs2.next()) {
				System.out.println(rs2.getInt(1)+"  "+rs2.getString(2)+"  "+rs2.getString(3) + "    "+rs2.getBigDecimal(13));
			}
			
			System.out.println("=====================================================================================");
			
			//6. inner join
			String innerjoin= "select * from employees inner join offices on employees.officeCode = offices.officeCode";
			ResultSet rs3= stmt.executeQuery(innerjoin);
			while(rs3.next()) {
				System.out.println(rs3.getInt(1)+"  "+rs3.getString(2)+"  "+rs3.getString(3));
			}
			
			System.out.println("=====================================================================================");

			//7. Left join
			String leftjoin= "select * from employees left join offices on employees.officeCode = offices.officeCode";
			ResultSet rs4= stmt.executeQuery(leftjoin);
			while(rs4.next()) {
				System.out.println(rs4.getInt(1)+"  "+rs4.getString(2)+"  "+rs4.getString(3));
			}
			
			System.out.println("=====================================================================================");

			//8. Right join
			String rightjoin= "select * from employees right join offices on employees.officeCode = offices.officeCode";
			ResultSet rs5= stmt.executeQuery(rightjoin);
			while(rs5.next()) {
				System.out.println(rs5.getInt(1)+"  "+rs5.getString(2)+"  "+rs5.getString(3));
			}
			
			System.out.println("=====================================================================================");

			//9. Full join
			String fulljoin= "select employeeNumber, firstname, lastname, email, city, state, country from employees left join offices on employees.officeCode = offices.officeCode union select employeeNumber, firstname, lastname, email, city, state, country from employees right join offices on employees.officeCode = offices.officeCode";
			ResultSet rs6= stmt.executeQuery(fulljoin);
			while(rs6.next()) {
				System.out.println(rs6.getInt(1)+"  "+rs6.getString(2)+"  "+rs6.getString(3));
			}
			
			System.out.println("=====================================================================================");

			//10. inner join
			String selfjoin= "select * from employees A inner join employees B on A.reportsTo = B.employeeNumber";
			ResultSet rs7= stmt.executeQuery(selfjoin);
			while(rs7.next()) {
				System.out.println(rs7.getInt(1)+"  "+rs7.getString(2)+"  "+rs7.getString(3));
			}
			
			System.out.println("=====================================================================================");
			
			//11. group by
			String groupbyex= "select jobTitle, count(*) as \"Number of Employees\" from employees group by jobTitle";
			ResultSet rs8= stmt.executeQuery(groupbyex);
			while(rs8.next()) {
				System.out.println(rs8.getString(1)+"  "+rs8.getString(2));
			}
			
			System.out.println("=====================================================================================");
			
			//11. order by
			String orderbyex= "select * from customers order by creditlimit ASC";
			ResultSet rs9= stmt.executeQuery(orderbyex);
			while(rs9.next()) {
				System.out.println(rs9.getString(1)+"  "+rs9.getString(2)+"  "+rs9.getString(3) + "    "+rs9.getBigDecimal(13));
			}
			
			
			//12. primary key 
			
			String pk= "create table employee_pk(id int(5) primary key,emp_name varchar(20), passport_number varchar(20))";
			ResultSet rs10= stmt.executeQuery(pk);
			
			//13. composite key
			String composite_key= "create table employee_pk(id int(5),emp_name varchar(20),passport_number varchar(20),primary key (id, emp_name))";
			ResultSet rs11= stmt.executeQuery(composite_key);
			
			//14. Foreign key 
			String foreign_key= "create table emp_bonus (emp_id int(4), bonus int(5),constraint f_key foreign key(emp_id) reference employee_pk(id))";
			ResultSet rs12 = stmt.executeQuery(foreign_key);
			
			
			
			// Close connection
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
			}  

	}


