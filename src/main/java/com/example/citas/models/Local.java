package com.example.citas.models;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_local")
public class Local {
    @Id
    @UuidGenerator
    private String localId;
    private String name;
    private String floor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "managerId")
    private Manager manager;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_local", referencedColumnName = "localId")
    private List<Orden> ordens;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_local_customer",

            joinColumns = @JoinColumn(name = "id_local", referencedColumnName = "localId"), inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "idCustomer")

    )
    private List<Customer> customerList;

}
