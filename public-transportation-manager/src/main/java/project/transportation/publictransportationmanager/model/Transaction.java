package project.transportation.publictransportationmanager.model;

import org.joda.time.LocalDate;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private String bank;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Account account;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Useri user;
    @Column(name = "transactiondate")
    private LocalDate dateOfTransaction;

    public Transaction() {

    }

    public Transaction(String bank) {
        System.out.println("one arg");
        this.bank = bank;
    }

    public Transaction(String bank, Useri user) {
        this();
        System.out.println("2 arg");
        this.bank = bank;
        this.user = user;
    }

    public Transaction(String bank, Account account, Useri user) {
        this(bank, user);
        this.bank = bank;
        System.out.println("3 arg");
        this.account = account;
        this.user = user;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Useri getUser() {
        return user;
    }

    public void setUser(Useri user) {
        this.user = user;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(LocalDate dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + transactionId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transaction other = (Transaction) obj;
        if (transactionId != other.transactionId)
            return false;
        return true;
    }
}
