package br.com.fiap.entities.users;

public class Salesman extends BaseUser {

    public Salesman() {
    }

    public Salesman(String name, String cpf, String phone) {
        super(name, cpf, phone);
    }

    public Salesman(int id, String name, String cpf, String phone) {
        super(id, name, cpf, phone);
    }
}
