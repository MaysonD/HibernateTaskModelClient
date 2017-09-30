package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int id;

    @Column(name = "client_name")
    public String name;

    @Column(name = "client_password")
    public String password;

    @OneToMany(mappedBy = "client", targetEntity = AccountEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<AccountEntity> accountEntities = new ArrayList<>();

    public void addAccount(AccountEntity accountEntity){
        accountEntity.setClient(this);
        accountEntities.add(accountEntity);
    }

    public List<AccountEntity> getAccountEntities() {
        return accountEntities;
    }

    public void setAccountEntities(List<AccountEntity> accountEntities) {
        this.accountEntities = accountEntities;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
