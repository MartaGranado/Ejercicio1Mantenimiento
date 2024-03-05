package bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    public void testWithdrawSufficientFunds() {

        int startingBalance = 100;
        int withdrawalAmount = 50;
        BankAccount account = new BankAccount(startingBalance);

        boolean result = account.withdraw(withdrawalAmount);

        assertTrue(result);
        assertEquals(startingBalance - withdrawalAmount, account.getBalance());
    }

    @Test
    public void testWithdrawInsufficientFunds() {

        int startingBalance = 50;
        int withdrawalAmount = 100;
        BankAccount account = new BankAccount(startingBalance);

        boolean result = account.withdraw(withdrawalAmount);

        assertFalse(result);
        assertEquals(startingBalance, account.getBalance());
    }

    @Test
    public void testDeposit() {

        int startingBalance = 100;
        int depositAmount = 50;
        BankAccount account = new BankAccount(startingBalance);

        int newBalance = account.deposit(depositAmount);

        assertEquals(startingBalance + depositAmount, newBalance);
    }

    @Test
    public void testDepositNegativeAmount() {

        BankAccount account = new BankAccount(100);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-50);
        });

        assertEquals("Amount cannot be negative", exception.getMessage());
        assertEquals(100, account.getBalance());
    }

    @Test
    public void testPayment() {
        // Arrange
        double totalAmount = 1000;
        double interest = 0.05;
        int numPayments = 12;
        BankAccount account = new BankAccount(0);

        // Act
        double monthlyPayment = account.payment(totalAmount, interest, numPayments);

        // Assert
        assertEquals(112.83, monthlyPayment, 0.01); // Adjusted expected value
    }

    @Test
    public void testPendingLaterMonth() {
        // Arrange
        double totalAmount = 1000;
        double interest = 0.05;
        int numPayments = 12;
        int month = 5;
        BankAccount account = new BankAccount(0);

        // Act
        double pendingAmount = account.pending(totalAmount, interest, numPayments, month);

        // Assert
        assertEquals(652.85, pendingAmount, 0.01); // Adjusted expected value
    }
}
