import java.util.*;
import java.lang.*;
public class Task3{
    public static int balance=1000000;
    public static int transactions=0;
    public static int deposit[]=new int[500];
    public static int withdrawl[]=new int[500];
    public static String transfer[][]=new String[500][2];
    public static void main(String []args)
    {
        boolean isLogin=false;
        String u_id="arpit530";
        int u_pin=2626;
        Scanner sc=new Scanner(System.in);
        int i=0;
        while(!isLogin && i<3)
        {
            System.out.println("Enter user id");
            String id=sc.next();
            try 
            {
                System.out.println("Enter your 4 digit pin");
                Integer pin=sc.nextInt();
                if(id.equals(u_id)==true && u_pin==pin)
                {
                isLogin=true;
                System.out.println("***********Welcome to ATM***********");
                }
                else{
                    System.out.println("Invalid username or password");
                    i++;
                    System.out.println(3-i+" chance left");
                }
            } 
            catch (InputMismatchException e)
            {
                System.out.println("Invalid username or password");
                i++;
                System.out.println(3-i+" chance left");
                id=sc.next(); 
            }
        }
        while(isLogin)
        {
            try
            {
                System.out.println("1. Transaction History");
                System.out.println("2. Withdrawl");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                Integer choice = sc.nextInt();
                switch(choice)    
                {
                    case 1:
                    Tr_History person=new Tr_History();
                    person.history();
                    break;
                    case 2:
                    Withdrawl person1=new Withdrawl();
                    person1.Withdrawl_amt();
                    break;
                    case 3:
                    Deposit person2=new Deposit();
                    person2.deposit_amt();
                    break;
                    case 4:
                    Transfer person3=new Transfer();
                    person3.transfer_amt();
                    break;
                    case 5:
                    System.out.println("please visit again");
                    isLogin=false;
                    break;
                    default:
                    System.out.println("wrong choice");
                }
            }
            catch(InputMismatchException x)
            {
                System.out.println("choice is not an integer please rechoose");
                sc.next();
            }
        } 
    }
    
}
class Tr_History extends Task3{
    void history()
    {
        int bal= 1000000;
        System.out.println("*********History************");
        System.out.println("Current Balance--"+super.balance);
        System.out.println("Sr.No.  Wthdrawl    Deposit    transfer_person-transfer_amt   Remain_Balance"); //check for remain balance
        for(int i=0;i<super.transactions;i++)
        {

            try{
            bal=bal+super.deposit[i]-super.withdrawl[i]-Integer.parseInt(super.transfer[i][1]);
            }
            catch(NumberFormatException e)
		    {
			System.out.println(e.getMessage());
		    }
            System.out.println(i+1+"    "+super.withdrawl[i]+"      "+super.deposit[i]+"        "+super.transfer[i][0]+"-"+super.transfer[i][1]+"   "+bal);
        }
    }

}
class Withdrawl extends Task3{
    void Withdrawl_amt()
    {
        int ammount=0;
        int i=0;
        boolean amt=true;
        System.out.println("Enter ammount you want to withdrawl");
        Scanner sc= new Scanner(System.in);
        do
        {
            i++;
            amt=true;
            try
            {
                ammount=sc.nextInt();
            }
            catch(InputMismatchException x)
            {
                System.out.println("please enter a valid ammount");
                amt=false;
                sc.next();
            }
        }while(amt==false && i<3);
        if(amt==true && ammount<=super.balance)
        {
            super.balance=super.balance-ammount;
            super.deposit[transactions]=0;
            super.withdrawl[transactions]=ammount;
            super.transfer[transactions][0]= "None";
            super.transfer[transactions][1]= "0";
            super.transactions++;
            System .out.println("Withdrawl succesful");
        }
        else if(amt==false)
        {
            System.out.println("Withdrawl failed");
        }
        else
        {
            System.out.println("Insufficient balance");
        }
        
    }
}
class Deposit extends Task3{
    void deposit_amt()
    {
        int ammount=0;
        int i=0;
        boolean amt=true;
        System.out.println("Enter ammount you want to deposit");
        Scanner sc= new Scanner(System.in);
        do
        {
            i++;
            amt=true;
            try
            {
                ammount=sc.nextInt();
            }
            catch(InputMismatchException x)
            {
                System.out.println("please enter a valid ammount");
                amt=false;
                sc.next();
            }
        }while(amt==false && i<3);
        if(amt==true && ammount<=1000000 && ammount>=1)
        {
            super.balance=super.balance+ammount;
            super.deposit[transactions]=ammount;
            super.withdrawl[transactions]=0;
            super.transfer[transactions][0]= "None";
            super.transfer[transactions][1]= "0";
            super.transactions++;
            System .out.println("Deposit succesful");
        }
        else if(amt==false)
        {
            System.out.println("Deposit failed");
        }
        else{
            System.out.println("Not a valid ammount or limit exceed");
        }

    }
}
class Transfer extends Task3{
    void transfer_amt()
    {
        int ammount=0;
        boolean amt=true;
        int i=0;
        Scanner sc= new Scanner(System.in);
        System.out.println("Name of the person to whom you want to transfer");
        String transfer_person_name=sc.next();
        System.out.println("Enter ammount you want to transfer");
        do
        {
            i++;
            amt=true;
            try
            {
                ammount=sc.nextInt();
            }
            catch(InputMismatchException x)
            {
                System.out.println("please enter a valid ammount");
                amt=false;
                sc.next();
            }
        }while(amt==false && i<3);
        if(amt==true && ammount<=super.balance)
        {
            super.balance=super.balance-ammount;
            super.deposit[transactions]=0;
            super.withdrawl[transactions]=0;
            super.transfer[transactions][0]= transfer_person_name;
            super.transfer[transactions][1]= ammount+"";
            super.transactions++;
            System.out.println("Transfer succesful");
        }
        else if(amt==false)
        {
            System.out.println("Transfer failed");
        }
        else
        {
            System.out.println("Insufficient balance");
        }
        
    }

}
