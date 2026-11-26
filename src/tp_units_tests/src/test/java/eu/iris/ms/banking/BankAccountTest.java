package eu.iris.ms.banking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe BankAccount.
 * 
 * Cette classe de test illustre :
 * - Tests exhaustifs d'un objet métier
 * - Validation des règles de gestion
 * - Tests de construction avec différents paramètres
 * - Tests d'exceptions avec messages explicites
 * - Tests d'état et de comportement
 */
class BankAccountTest {
    
    private BankAccount account;
    private final String ACCOUNT_NUMBER = "ACC001";
    private final String HOLDER_NAME = "Alice Dupont";
    private final double INITIAL_BALANCE = 100.0;
    
    /**
     * Prépare un compte valide avant chaque test
     */
    @BeforeEach
    void setUp() {
        account = new BankAccount(ACCOUNT_NUMBER, HOLDER_NAME, INITIAL_BALANCE);
    }
    
    // === TESTS DE CONSTRUCTION ===
    
    @Test
    void shouldCreateAccountWithValidParameters() {
        // ARRANGE & ACT - Création d'un nouveau compte
        BankAccount newAccount = new BankAccount("ACC002", "Bob Martin", 50.0);
        
        // ASSERT - Vérification de l'état initial
        assertEquals("ACC002", newAccount.getAccountNumber());
        assertEquals("Bob Martin", newAccount.getHolderName());
        assertEquals(50.0, newAccount.getBalance());
        assertTrue(newAccount.isActive());
        assertEquals(0.0, newAccount.getOverdraftLimit());
    }
    
    @Test
    void shouldRejectNullAccountNumber() {
        // TODO: Implémenter ce test
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new BankAccount(null, HOLDER_NAME, INITIAL_BALANCE)
        );
        assertEquals("Account number cannot be null or empty", exception.getMessage());
    }
    
    @Test
    void shouldRejectEmptyAccountNumber() {
        // TODO: Implémenter ce test
        // Tester avec une chaîne vide ""
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new BankAccount(" ", HOLDER_NAME, INITIAL_BALANCE)
        );
        assertEquals("Account number cannot be null or empty", exception.getMessage());
    }
    
    @Test
    void shouldRejectNullHolderName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new BankAccount(ACCOUNT_NUMBER, null, INITIAL_BALANCE)
        );
        assertEquals("Holder name cannot be null or empty", exception.getMessage());
    }
    
    @Test
    void shouldRejectEmptyHolderName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new BankAccount(ACCOUNT_NUMBER, " ", INITIAL_BALANCE)
        );
        assertEquals("Holder name cannot be null or empty", exception.getMessage());
    }
    
    @Test
    void shouldRejectNegativeInitialBalance() {
        // TODO: Implémenter ce test
        // Tester avec -10.0 par exemple
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new BankAccount(ACCOUNT_NUMBER, HOLDER_NAME, -10.0)
        );
        assertEquals("Initial balance cannot be negative", exception.getMessage());
    }
    
    @Test
    void shouldAcceptZeroInitialBalance() {
        // ARRANGE & ACT
        BankAccount zeroAccount = new BankAccount("ACC003", "Charlie", 0.0);
        
        // ASSERT
        assertEquals(0.0, zeroAccount.getBalance());
    }
    
    // === TESTS DE DÉPÔT ===
    
    @Test
    void shouldIncreaseBalanceOnValidDeposit() {
        // ARRANGE
        double initialBalance = account.getBalance();
        double depositAmount = 50.0;
        
        // ACT
        account.deposit(depositAmount);
        
        // ASSERT
        assertEquals(initialBalance + depositAmount, account.getBalance());
    }
    
    @Test
    void shouldRejectZeroDeposit() {
        // TODO: Implémenter ce test
        // Le dépôt de 0.0 doit intercepter IllegalArgumentException
        // ARRANGE
        double initialBalance = account.getBalance();
        double depositAmount = 0.0;

        // ACT & ASSSERT
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> this.account.deposit(depositAmount)
        );

        assertEquals("Deposit amount must be positive", exception.getMessage());
    }
    
    @Test
    void shouldRejectNegativeDeposit() {
        // TODO: Implémenter ce test
        // Le dépôt de -50.0 doit intercepter IllegalArgumentException
        // ARRANGE
        double initialBalance = account.getBalance();
        double depositAmount = -50.0;

        // ACT & ASSSERT
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> this.account.deposit(depositAmount)
        );

        assertEquals("Deposit amount must be positive", exception.getMessage());
    }
    
    @Test
    void shouldRejectDepositOnInactiveAccount() {
        // ARRANGE
        account.deactivate();
        
        // ACT & ASSERT
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> account.deposit(50.0)
        );
        
        // Vérification optionnelle du message
        assertTrue(exception.getMessage().contains("not active") || 
                  exception.getMessage().contains("inactive"));
    }
    
    // === TESTS DE RETRAIT ===
    
    @Test
    void shouldDecreaseBalanceOnValidWithdrawal() {
        // TODO: Implémenter ce test
        // Retirer 30.0 d'un compte avec 100.0
        // Vérifier que le solde devient 70.0
        double amount = 30.0;
        account.withdraw(amount);

        // ACT & ASSERT
        assertEquals(70.0, account.getBalance());

    }
    
    @Test
    void shouldRejectWithdrawalExceedingBalance() {
        // TODO: Implémenter ce test
        // Tentative de retrait de 150.0 d'un compte avec 100.0
        // Doit intercepter IllegalArgumentException
        double amount = 150.0;

        // ACT & ASSERT
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(amount)
        );

        assertEquals("Insufficient funds", exception.getMessage());
    }
    
    @Test
    void shouldAllowWithdrawalWithinOverdraftLimit() {
        // ARRANGE
        account.setOverdraftLimit(50.0);
        double withdrawalAmount = 130.0; // Plus que le solde mais dans la limite
        
        // ACT
        account.withdraw(withdrawalAmount);
        
        // ASSERT
        assertEquals(-30.0, account.getBalance(), 0.001); // 100 - 130 = -30
    }
    
    @Test
    void shouldRejectWithdrawalExceedingOverdraftLimit() {
        // TODO: Implémenter ce test
        // Doit intercepter IllegalArgumentException
        account.setOverdraftLimit(50.0);
        double amount = 200.0;

        // ACT & ASSERT
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(amount)
        );

        assertEquals("Insufficient funds", exception.getMessage());
    }
    
    @Test
    void shouldRejectWithdrawalOnInactiveAccount() {
        // TODO: Implémenter ce test
        // Désactiver le compte puis tenter un retrait
        double amount = 50.0;
        account.setActive(false);

        // ACT & ASSERT
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> account.withdraw(amount)
        );

        assertEquals("Account is not active", exception.getMessage());
    }
    
    @Test
    void shouldRejectNegativeWithdrawal() {
        // TODO: Implémenter ce test
        // Tentative de retrait de -50.0
        double amount = -50.0;

        // ACT & ASSERT
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(amount)
        );

        assertEquals("Withdrawal amount must be positive", exception.getMessage());
    }
    
    // === TESTS DE TRANSFERT ===
    
    @Test
    void shouldTransferMoneyBetweenAccounts() {
        // ARRANGE
        BankAccount targetAccount = new BankAccount("ACC002", "Bob Martin", 0.0);
        double transferAmount = 30.0;
        double initialSourceBalance = account.getBalance();
        double initialTargetBalance = targetAccount.getBalance();
        
        // ACT
        account.transfer(targetAccount, transferAmount);
        
        // ASSERT
        assertEquals(initialSourceBalance - transferAmount, account.getBalance());
        assertEquals(initialTargetBalance + transferAmount, targetAccount.getBalance());
    }
    
    @Test
    void shouldRejectTransferWithInsufficientFunds() {
        // TODO: Implémenter ce test
        // Tentative de transfert de 150.0 d'un compte avec 100.0
        BankAccount targetAccount = new BankAccount("ACC002", "Bob Martin", 0.0);
        double transferAmount = 150.0;
        double initialSourceBalance = account.getBalance();
        double initialTargetBalance = targetAccount.getBalance();
        // ACT & ASSERT
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.transfer(targetAccount, transferAmount)
        );

        assertEquals("Insufficient funds", exception.getMessage());
        assertEquals(initialSourceBalance, account.getBalance());
        assertEquals(initialTargetBalance, targetAccount.getBalance());
    }
    
    @Test
    void shouldRejectTransferToNullAccount() {
        // TODO: Implémenter ce test
        // Tentative de transfert vers null
        BankAccount targetAccount = new BankAccount("ACC002", "Bob Martin", 0.0);
        double transferAmount = 150.0;
        double initialSourceBalance = account.getBalance();
        double initialTargetBalance = targetAccount.getBalance();
        // ACT & ASSERT
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.transfer(null, transferAmount)
        );

        assertEquals("Target account cannot be null", exception.getMessage());
        assertEquals(initialSourceBalance, account.getBalance());
        assertEquals(initialTargetBalance, targetAccount.getBalance());
    }
    
    @Test
    void shouldRejectTransferToInactiveAccount() {
        // TODO: Implémenter ce test
        // Créer un compte cible, le désactiver, tenter le transfert
        BankAccount targetAccount = new BankAccount("ACC002", "Bob Martin", 0.0);
        double transferAmount = 80.0;
        targetAccount.deactivate();
        // ACT & ASSERT
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> account.transfer(targetAccount, transferAmount)
        );

        assertEquals("Account is not active", exception.getMessage());
    }
    
    @Test
    void shouldRejectTransferFromInactiveAccount() {
        // TODO: Implémenter ce test
        // Désactiver le compte source, tenter le transfert
        BankAccount targetAccount = new BankAccount("ACC002", "Bob Martin", 0.0);
        double transferAmount = 80.0;
        account.deactivate();
        // ACT & ASSERT
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> account.transfer(targetAccount, transferAmount)
        );

        assertEquals("Account is not active", exception.getMessage());
    }
    
    // === TESTS D'ACTIVATION/DÉSACTIVATION ===
    
    @Test
    void shouldDeactivateAccount() {
        // ARRANGE
        assertTrue(account.isActive());
        
        // ACT
        account.deactivate();
        
        // ASSERT
        assertFalse(account.isActive());
    }
    
    @Test
    void shouldActivateAccount() {
        // ARRANGE
        account.deactivate();
        assertFalse(account.isActive());
        
        // ACT
        account.activate();
        
        // ASSERT
        assertTrue(account.isActive());
    }
    
    // === TESTS LIMITE DE DÉCOUVERT ===
    
    @Test
    void shouldSetValidOverdraftLimit() {
        // ARRANGE
        double newLimit = 100.0;
        
        // ACT
        account.setOverdraftLimit(newLimit);
        
        // ASSERT
        assertEquals(newLimit, account.getOverdraftLimit());
    }
    
    @Test
    void shouldRejectNegativeOverdraftLimit() {
        // TODO: Implémenter ce test
        // Tentative de définir une limite de -50.0
        double newLimit = -50.0;

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.setOverdraftLimit(newLimit)
        );

        assertEquals("Overdraft limit cannot be negative", exception.getMessage());
    }
    
    // === TESTS DES MÉTHODES UTILITAIRES ===
    
    @Test
    void shouldCalculateAvailableBalanceCorrectly() {
        // ARRANGE
        account.setOverdraftLimit(50.0);
        
        // ACT & ASSERT
        assertEquals(150.0, account.getMaxAvailableBalance()); // 100 + 50
    }
    
    @Test
    void shouldReturnTrueWhenCanWithdraw() {
        // TODO: Implémenter ce test
        // Vérifier que canWithdraw(50.0) retourne true pour un compte avec 100.0
        double amount = 50.0;
        assertTrue(account.canWithdraw(amount));
    }
    
    @Test
    void shouldReturnFalseWhenCannotWithdraw() {
        // TODO: Implémenter ce test
        // Vérifier que canWithdraw(150.0) retourne false pour un compte avec 100.0 sans découvert
        double amount = 150.0;
        assertFalse(account.canWithdraw(amount));
    }
    
    @Test
    void shouldReturnFalseWhenCanWithdrawOnInactiveAccount() {
        // ARRANGE
        account.deactivate();
        
        // ACT & ASSERT
        assertFalse(account.canWithdraw(50.0));
    }
    
    // === TESTS DE SCÉNARIOS COMPLEXES ===
    
    @Test
    void shouldHandleMultipleOperationsCorrectly() {
        // ARRANGE
        assertEquals(100.0, account.getBalance());
        
        // ACT - Série d'opérations
        account.deposit(50.0);    // Solde : 150.0
        account.withdraw(30.0);   // Solde : 120.0
        account.deposit(20.0);    // Solde : 140.0
        
        // ASSERT
        assertEquals(140.0, account.getBalance());
        assertTrue(account.isActive());
    }
    
    @Test
    void shouldMaintainConsistencyAfterFailedOperation() {
        // ARRANGE
        double initialBalance = account.getBalance();
        
        // ACT - Tentative d'opération invalide
        try {
            account.withdraw(1000.0); // Doit échouer
        } catch (IllegalArgumentException e) {
            // Attendu
        }
        
        // ASSERT - L'état ne doit pas avoir changé
        assertEquals(initialBalance, account.getBalance());
        assertTrue(account.isActive());
    }

    @Test
    void shouldTestToString() {
        account.setAccountNumber("ACC002");
        account.setHolderName("Bob Dupond");
        account.setBalance(150.0);
        String result = "BankAccount{number='ACC002', holder='Bob Dupond', balance=150,00, active=true, overdraft=0,00}";
        assertEquals(result, account.toString());
    }
}