package eu.iris.ms.banking;

/*
 Classe BankAccount - Exemple d'objet métier avec règles de gestion.
*/

public class BankAccount {
    
    protected String accountNumber;
    protected String holderName;
    protected double balance;
    protected boolean isActive;
    protected double overdraftLimit; // Découvert autorisé
    
    /**
     * Constructeur principal
     * @param accountNumber numéro de compte
     * @param holderName nom du titulaire
     * @param initialBalance solde initial
     * @throws IllegalArgumentException si les paramètres sont invalides
     */
    public BankAccount(String accountNumber, String holderName, double initialBalance) throws IllegalArgumentException {
        // TODO: Implémenter les validations
        // Se référer aux règles métiers pour déterminer les validations&throws à effectuer.
        // Lever IllegalArgumentException avec message explicite si invalide
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Holder name cannot be null or empty");
        }
        if (initialBalance < 0.0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        // TODO: Implémenter la logique du constructor et init la classe
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.isActive = true;
        this.overdraftLimit = 0.0;
    }
    
    /**
     * Effectue un dépôt sur le compte
     * @param amount montant à déposer
     * @throws IllegalStateException si le compte n'est pas actif
     * @throws IllegalArgumentException si le montant est invalide
     */
    public void deposit(double amount) throws IllegalStateException, IllegalArgumentException {
        // TODO: Implémenter
        // 1. Se référer aux règles métiers pour déterminer les validations&throws à effectuer.
        //    Lever les exceptions appropriées avec messages clairs
        // 2. Ajouter amount à balance

        if (amount <= 0.0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        if (!this.isActive) {
            throw new IllegalStateException("Account is not active");
        }
        this.balance += amount;
    }
    
    /**
     * Effectue un retrait du compte
     * @param amount montant à retirer
     * @throws IllegalStateException si le compte n'est pas actif
     * @throws IllegalArgumentException si le montant est invalide ou insuffisant
     */
    public void withdraw(double amount) throws IllegalStateException, IllegalArgumentException {
        // TODO: Implémenter
        // 1. Se référer aux règles métiers pour déterminer les validations&throws à effectuer.
        //    Lever les exceptions appropriées avec messages clairs
        // 2. Déduire amount du balance

        if (amount <= 0.0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (!this.isActive) {
            throw new IllegalStateException("Account is not active");
        }
        if (amount > this.balance + this.overdraftLimit) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        this.balance -= amount;

    }
    
    /**
     * Effectue un transfert vers un autre compte
     * @param targetAccount compte de destination
     * @param amount montant à transférer
     * @throws IllegalArgumentException si les paramètres sont invalides
     * @throws IllegalStateException si l'opération ne peut être effectuée
     */
        public void transfer(BankAccount targetAccount, double amount) throws IllegalStateException, IllegalArgumentException {
            // TODO: Implémenter
            // 1. Se référer aux règles métiers pour déterminer les validations&throws à effectuer.
            //    Lever les exceptions appropriées avec messages clairs
            // 2. Utiliser withdraw() pour retirer de ce compte
            // 3. Utiliser targetAccount.deposit() pour déposer
            // Note : si withdraw() échoue, le transfert échoue (cohérence)

            if (targetAccount == null) {
                throw new IllegalArgumentException("Target account cannot be null");
            }

            this.withdraw(amount);

            targetAccount.deposit(amount);
        }
    
    /**
     * Désactive le compte (aucune opération possible)
     */
    public void deactivate() {
        // TODO : Implémenter
        this.setActive(false);

    }
    
    /**
     * Réactive le compte
     */
    public void activate() {
        // TODO: Implémenter
        this.setActive(true);
    }
    
    /**
     * Définit la limite de découvert autorisé
     * @param limit montant du découvert autorisé
     * @throws IllegalArgumentException si la limite est négative
     */
    public void setOverdraftLimit(double limit) throws IllegalArgumentException {
        // TODO: Implémenter
        // 1. Se référer aux règles métiers pour déterminer les validations&throws à effectuer.
        //    Lever les exceptions appropriées avec messages clairs
        // 2. Modifier la limite de découvert

        if (limit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }

        this.overdraftLimit = limit;
    }
    
    /**
     * Vérifie si le compte peut effectuer un retrait d'un montant donné
     * @param amount montant à vérifier
     * @return true si le retrait est possible
     */
    public boolean canWithdraw(double amount) {
        return (this.balance + this.overdraftLimit > amount && this.isActive());
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * Retourne le solde disponible (solde + découvert)
     */
    public double getMaxAvailableBalance() {
        return this.balance = this.balance + this.overdraftLimit;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return String.format("BankAccount{number='%s', holder='%s', balance=%.2f, active=%s, overdraft=%.2f}", 
                           this.accountNumber, this.holderName, this.balance, this.isActive, this.overdraftLimit);
    }
}