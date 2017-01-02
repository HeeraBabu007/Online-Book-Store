package bs;

import java.util.Scanner;
import java.sql.*;
import bookstore.*;
import static java.lang.System.exit;


public class Main
{
   public static Scanner h = new Scanner(System.in);
   public static Main m = new Main();
   public  String ch,ch1,ch3,ch4,ch5;
   public  User u = new User();
   public  Book b = new Book();
   public Shop s = new Shop();
   public String arr[] = new String[50];
    int del_id,rem_id,car_id,i,num;
    String jj,kk;
   
   public static void main(String arg[]) throws ClassNotFoundException, SQLException
   {
       System.out.println("\t\t\t::::::::::: Welcome to the world of books :::::::::::::\t\t\t");
       
       System.out.println("\nYOU ARE:-\n1-user\n2-Admin\n3-exit\n ");
       int enter = h.nextInt();
       switch(enter)
       {
           case 1: m.user();
                    break;
           
           case 2: m.admin();
                    break;
           case 3: 
           {
               System.out.println("******************************** THANKS FOR VISITING..!!! **********************************");
               exit(0);
                break;
           }
       }
       
        
   } 
    
    void user() throws ClassNotFoundException, SQLException
    {
        System.out.println("\n\t\txxxxx WELCOME USER xxxxx\t\t");
        System.out.println("enter user name- ");
        u.uname = h.next();
        System.out.println("enter user id- ");
        u.uid = h.nextInt();
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
        Statement stmt1 = conn1.createStatement();
        
        String q2="insert into add_user(uid,uname) values ( "+u.uid+" , '"+u.uname+"');";
        stmt1.executeUpdate(q2);
        
        
         do
       {
          System.out.println("\nDo you want to continue? (y/n)");
          ch1 = h.next();
          System.out.println("\nPLEASE ENTER A CHOICE:- ");
          System.out.println("1-show books\n2-Shopping\n3-Search for a book\n4-exit\n");
          int n = h.nextInt();
          
          switch(n)
          {
              case 1: m.show_books();
                      break;
              case 4:  
              {
               System.out.println("******************************** THANKS FOR VISITING..!!! **********************************");
               exit(0);
                break;
              }
              case 2: m.shop();
                      break;
              case 3:m.search();
                      break;
                      
              default : System.out.println("\ninvalid choice..!! try again...\n");
      
          }
          
          
       }while("y".equalsIgnoreCase(ch1)== true);
         
         
       stmt1.close();
       conn1.close();
       
    
    }
        
        
    void admin() throws ClassNotFoundException, SQLException
    {
        Admin ad = new Admin();
        System.out.println("enter admin name- ");
        ad.name = h.next();
        System.out.println("enter admin id- ");
        ad.id = h.nextInt();
        System.out.println("enter admin pass- ");
        ad.pass = h.next();
        
        
        if((ad.pass.equalsIgnoreCase("9999")== true)&&(ad.id == 123)&&(ad.name.equalsIgnoreCase("hydv")== true))
       {
            System.out.println("\t\txxxxxxxxxxx CORRECT DATA..!! xxxxxxxxxxxx\t\t");
                do
               {
                  System.out.println("\nDo you want to continue? (y/n)");
                  ch = h.next();
                  
                  System.out.println("\n\t***WELCOME ADMIN***\t");
                  System.out.println("\nPLEASE ENTER YOUR CHOICE:-");
                  System.out.println("\n1-remove user\n2-show users\n3-show books\n4-add books\n5-remove books\n6-Search a book\n7-exit\n");
                  int n = h.nextInt();

                  switch(n)
                  {
                      case 1: m.remove_user();
                              break;
                      case 2: m.show_users();
                              break;
                      case 3: m.show_books();
                              break;
                      case 4: m.add_book();
                              break;
                      case 5: m.remove_book();
                              break;
                      case 6: m.search();
                              break;
                      case 7: 
                        {
                            System.out.println("******************************** THANKS FOR VISITING..!!! **********************************");
                            exit(0);
                             break;
                        }
                      default : System.out.println("\ninvalid choice..!! try again...\n");

                  }
                    
               }while("y".equalsIgnoreCase(ch) == true);
       }
        else
        {
                    System.out.println("\n\t\txxxxxxxxxxx DATA IS WRONG..!! xxxxxxxxxxxx\t\t\n\nWaana try again..??(y/n)");
                     String r = h.next();
                if("y".equalsIgnoreCase(r)== true)
                {
                    admin();
                }
                else
                {
                    System.out.println("******************************** THANKS FOR VISITING..!!! **********************************");
                    exit(0);
                }
            
        }
        
    }
    
   void remove_user() throws ClassNotFoundException, SQLException
   {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
        Statement stmt2 = conn2.createStatement();
        
        
         System.out.println("\nUSERS AT STORE ARE:-");
         
         System.out.println("\t[ ID ]\t\t[ NAME ]\t");
         String q8 = "select * from add_user;";
         ResultSet rs1 = stmt2.executeQuery(q8);
         while(rs1.next())
         {
             System.out.println("\t"+rs1.getInt(1)+"\t\t"+rs1.getString(2)+"\t");
         }
        
       System.out.println("enter the user id you wants to delete- ");
       m.del_id = h.nextInt();
       String q3 = "delete from add_user where uid="+m.del_id+";";
       stmt2.executeUpdate(q3);
       
       stmt2.close();
       conn2.close();
       
   }
   
   void add_book() throws ClassNotFoundException, SQLException
   {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
        Statement stmt3 = conn3.createStatement();
         System.out.println("enter book id- ");
         b.bid = h.nextInt();
         System.out.println("enter book name- ");
         b.bname = h.next();
         System.out.println("enter book cost- ");
         b.bcost = h.nextFloat();
        
        String q5 = "insert into add_books(bid,bname,bcost) values ( "+b.bid+",'"+b.bname+"',"+b.bcost+");";
        stmt3.executeUpdate(q5);
        stmt3.close();
        conn3.close();
   }
   
   void remove_book() throws ClassNotFoundException, SQLException
   {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
        Statement stmt4 = conn4.createStatement();
        Statement stmt12 = conn4.createStatement();
        
        System.out.println("\tBooks at store are:- \t\n");
         
         String q55 = "select * from add_books;";
         ResultSet rs = stmt12.executeQuery(q55);
         System.out.println("\t[ ID ]\t[ NAME ]\t[ PRICE ]\t");
         while(rs.next())
         {
             System.out.println("\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getFloat(3)+"\t");
         }
         System.out.println("enter the book id you wants to remove- ");
         m.rem_id = h.nextInt();
         
        String q6 = "delete from add_books where bid = "+m.rem_id+";";
        stmt4.executeUpdate(q6);
        stmt4.close();
        conn4.close();
       
   }
   
   void show_books() throws ClassNotFoundException, SQLException
   {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn5 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
        Statement stmt5 = conn5.createStatement();
         System.out.println("\nBOOKS AT STORE ARE:- ");
         
         String q7 = "select * from add_books;";
         ResultSet rs = stmt5.executeQuery(q7);
         System.out.println("\t[ ID ]\t[ NAME ]\t[ PRICE ]\t");
         while(rs.next())
         {
             System.out.println("\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getFloat(3)+"\t");
         }        
         stmt5.close();
         conn5.close();
         
   }
   
   
    void show_users() throws ClassNotFoundException, SQLException
   {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn6 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
        Statement stmt6 = conn6.createStatement();
         System.out.println("\nUSER ARE:-");
         
         System.out.println("\t[ ID ]\t\t[ NAME ]\t");
         String q8 = "select * from add_user;";
         ResultSet rs1 = stmt6.executeQuery(q8);
         while(rs1.next())
         {
             System.out.println("\t"+rs1.getInt(1)+"\t\t"+rs1.getString(2)+"\t");
         }        
       stmt6.close();
       conn6.close();
   }
    
    void add_cart() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn7 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
        Statement stmt7 = conn7.createStatement();
        
        System.out.println("\nBOOKS AT STORE ARE:- ");
         
         String q7 = "select * from add_books;";
         ResultSet rs = stmt7.executeQuery(q7);
         System.out.println("\t[ ID ]\t[ NAME ]\t[ PRICE ]\t");
         while(rs.next())
         {
             System.out.println("\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getFloat(3)+"\t");
         }
         
         do
         {
             System.out.println("please select the id of book you wants to add into your wishlist..!!");
            s.sid = h.nextInt();
         
            System.out.println("Do you want to enter more??(y/n)");
            ch4 = h.next();
         
            String q90 =" insert into cart(sid) values ("+s.sid+");";
            stmt7.executeUpdate(q90);
            
         
         }while("y".equalsIgnoreCase(ch4));
         
            System.out.println("\nYOUR WISHLIST IS:-");
            String q10 = "select cart.sid,add_books.bname,add_books.bcost from cart inner join add_books on cart.sid = add_books.bid;";
            System.out.println("\t[ ID ]\t[ NAME ]\t[ PRICE ]\t");
            ResultSet rs2 = stmt7.executeQuery(q10);
                  
            while(rs2.next())
            {
                System.out.println("\t"+rs2.getInt(1)+"\t"+rs2.getString(2)+"\t\t"+rs2.getFloat(3)+"\t");
            }
         
    }
    
    void remove_cart() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn8 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
        Statement stmt8 = conn8.createStatement();
        
        System.out.println("\nYOUR WISHLIST IS:-");
        String q10 = "select cart.sid,add_books.bname,add_books.bcost from cart inner join add_books on cart.sid = add_books.bid;";
       System.out.println("\t[ ID ]\t[ NAME ]\t[ PRICE ]\t");
        ResultSet rs2 = stmt8.executeQuery(q10);
         while(rs2.next())
         {
             System.out.println("\t"+rs2.getInt(1)+"\t"+rs2.getString(2)+"\t\t"+rs2.getFloat(3)+"\t");
         }
      
        
        System.out.println("enter book id you want to remove from list- ");
        m.car_id = h.nextInt();
        String q11="delete from cart where sid = "+m.car_id+";";
        stmt8.executeUpdate(q11);
        stmt8.close();
        conn8.close();
        
    }
    
    void shop() throws ClassNotFoundException, SQLException
    {
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn8 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
        Statement stmt5 = conn8.createStatement();
         System.out.println("\nBOOKS AT STORE ARE:-");
         
         String q7 = "select * from add_books;";
         ResultSet rs = stmt5.executeQuery(q7);
         System.out.println("\t[ ID ]\t[ NAME ]\t[ PRICE ]\t");
         while(rs.next())
         {
             System.out.println("\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getFloat(3)+"\t");
         } 
         
         do
         {
            System.out.println("\nPLEASE ENTER YOUR CHOICE:- \n1-Add to cart\n2-Remove from my cart\n3-Show my cart\n4-exit\n");
            int i = h.nextInt();

            System.out.println("Do you want to continue?? (y/n) ");
            ch3 = h.next();

            switch(i)
            {
                case 1: m.add_cart();
                        break;
                case 2: m.remove_cart();
                        break;
                case 3: m.show_cart();
                        break;
                case 4 : 
                       {
                         System.out.println("******************************** THANKS FOR VISITING..!!! **********************************");
                         exit(0);
                         break;
                       }
            }
         }while("y".equalsIgnoreCase(ch3));
         
    }
    void show_cart() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn8 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
        Statement stmt8 = conn8.createStatement();
        
        System.out.println("\nYOUR WISHLIST IS:-");
        String q10 = "select cart.sid,add_books.bname,add_books.bcost from cart inner join add_books on cart.sid = add_books.bid;";
       System.out.println("\t[ ID ]\t[ NAME ]\t[ PRICE ]\t");
        ResultSet rs2 = stmt8.executeQuery(q10);
         while(rs2.next())
         {
             System.out.println("\t"+rs2.getInt(1)+"\t"+rs2.getString(2)+"\t\t"+rs2.getFloat(3)+"\t");
         }
    }

    void search() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn8 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
        Statement stmt8 = conn8.createStatement();
        
        System.out.println("\nEnter the book name that you wants to search..");
        ch5 = h.next();
        
        String q81 =" select bname from add_books; ";
        ResultSet rs11 = stmt8.executeQuery(q81);
        int flag =0;
        while(rs11.next())
        {
            if(ch5.equalsIgnoreCase(rs11.getString(1)))
            {
                flag=1;
            }
        }
        
        
        
        if(flag==1)
        {
            System.out.println("\nBOOK IS AVAILABLE..!!\nWith the following details:- ");
            String q44 =" select * from add_books where bname = '"+m.ch5+"';";
            ResultSet rs = stmt8.executeQuery(q44);
            System.out.println("\n\t[ ID ]\t[ NAME ]\t[ PRICE ]\t");
            while(rs.next())
            {
                System.out.println("\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getFloat(3)+"\t");
            } 
        
        }
        else
        {
            System.out.println("\nSORRY..!! THE BOOK IS NOT AVAILABLE AT STORE...\n");
        }
    }
}