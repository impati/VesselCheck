package com.example.vesselcheck.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter@Setter
public abstract class Client extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="client_id")
    private Long id;
    private String name;
    private String belongs;
    private String email;


    public static Client createClient(String name,String belongs,String email,ClientType clientType){
        Client client = null;
        if(clientType == ClientType.INSPECTOR){
            client = new Inspector();
            client.setName(name);
            client.setBelongs(belongs);
            client.setEmail(email);
        }
        else{
            client = new Manufacturer();
            client.setName(name);
            client.setBelongs(belongs);
            client.setEmail(email);
        }
        return client;
    }

}
