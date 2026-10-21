package br.com.fiap.entities.users;

public class BaseUser {
    private int id;
    private String name;
    private String cpf;
    private String phone;

    public BaseUser() {
    }

    public BaseUser(String name, String cpf, String phone) {
        this(0, name, cpf, phone);
    }

    public BaseUser(int id, String name, String cpf, String phone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
