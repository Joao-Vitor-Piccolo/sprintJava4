package br.com.fiap.entities.sale;

import java.sql.Date;

public class Sale {
    private Integer id_sale;
    private Integer fk_salesman;
    private Integer fk_customer;
    private Integer is_started;
    private Integer is_finished;
    private Date created_at;

    public Sale() {
    }

    public Sale(Integer fk_salesman, Integer fk_customer, Integer is_started,
            Integer is_finished, Date created_at) {
        this(null, fk_salesman, fk_customer, is_started, is_finished, created_at);
    }

    public Sale(Integer id_sale, Integer fk_salesman, Integer fk_customer,
            Integer is_started, Integer is_finished, Date created_at) {
        this.id_sale = id_sale;
        this.fk_salesman = fk_salesman;
        this.fk_customer = fk_customer;
        this.is_started = is_started;
        this.is_finished = is_finished;
        this.created_at = created_at;
    }

    public Integer getId_sale() {
        return id_sale;
    }

    public void setId_sale(Integer id_sale) {
        this.id_sale = id_sale;
    }

    public Integer getFk_salesman() {
        return fk_salesman;
    }

    public void setFk_salesman(Integer fk_salesman) {
        this.fk_salesman = fk_salesman;
    }

    public Integer getFk_customer() {
        return fk_customer;
    }

    public void setFk_customer(Integer fk_customer) {
        this.fk_customer = fk_customer;
    }

    public boolean getIs_started() {
        return this.is_started != null && this.is_started == 1;
    }

    public void setIs_started(Integer is_started) {
        this.is_started = is_started;
    }

    public Integer getIs_started_value() {
        return is_started;
    }

    public boolean getIs_finished() {
        return this.is_finished != null && this.is_finished == 1;
    }

    public void setIs_finished(Integer is_finished) {
        this.is_finished = is_finished;
    }

    public Integer getIs_finished_value() {
        return is_finished;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
