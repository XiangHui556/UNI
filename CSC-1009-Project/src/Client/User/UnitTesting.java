/*
package Client.User;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class UnitTesting {
    ClientTransaction T;
    BalanceCheck B;

    @Before
    public final void setup(){
        T = new ClientTransaction("409000611074","TRF FROM  Indiaforensic SERVICES",123,0,0,1000.00); //random test values
        B = new BalanceCheck("409000611074",1000.00,2000.00);
    }

    @Test //test Transaction.deposit() 
    public final void testTransactiondeposit(){
        T.deposit(500.00);
        assertEquals(1500.00, T.getBalance(),0);     
    }

    @Test // test Transaction.withdraw()
    public final void testTransactionwithdraw(){
        try {
            T.withdraw(500.00);
            assertEquals(500.00, T.getBalance(),0); 
        } catch (InsufficientFundsException e) { //should throw the exception class
        }   
    }
    @Test //Test if the InsufficientWithdrawClass is working
    public final void testInsufficientWithdrawClass(){
        try {
            T.withdraw(5000.00);
            fail("InsufficientFundsException not thrown"); //fail if exception class is not thrown
        } catch (InsufficientFundsException expected) { //should throw the exception class
        }  
    }
    @Test //Test balancecheck.getaccountnumber
    public final void testAccountnumber(){
        assertEquals("409000611074",B.getAccountNumber());
        //to do:  test to check if format is right
    }

    @Test //Test balancecheck.getaccountnumber
    public final void testAvailableBalance(){
       assertEquals(1000.00,B.getAvaliableBalance(),0);
        assertTrue(B.getAvaliableBalance() > 0); //balance cannot be less than 0
       
    }

    @Test //Test balancecheck.gettotalbalance
    public final void testTotalBalance(){
        assertEquals(2000.00,B.getTotalBalance(),0);
        assertTrue(B.getTotalBalance() > 0); //balance cannot be less than 0     
    }

    @Test //test Authentication
    public final void testRandomString(){
        String testrandom = Authentication.getRandomNumberString();
        assertTrue(testrandom.length() == 6);
        
    }
    //will add more


}
*/
