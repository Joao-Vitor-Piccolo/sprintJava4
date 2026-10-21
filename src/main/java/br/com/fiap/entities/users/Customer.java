package br.com.fiap.entities.users;

public class Customer extends BaseUser {

    public Customer() {
    }

    public Customer(String name, String cpf, String phone) {
        super(name, cpf, phone);
    }

    public Customer(int id, String name, String cpf, String phone) {
        super(id, name, cpf, phone);
    }
}
