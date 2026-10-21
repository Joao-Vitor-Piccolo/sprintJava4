package br.com.fiap.entities.users;

public class Manager extends BaseUser {

    public Manager() {
    }

    public Manager(String name, String cpf, String phone) {
        super(name, cpf, phone);
    }

    public Manager(int id, String name, String cpf, String phone) {
        super(id, name, cpf, phone);
    }
}
