package com.example.vesselcheck.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * SINGLE TABLE 전략 사용시 오류 -> 대책 방안은 ?
 */
@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter@Setter
public abstract class Client  {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="client_id")
    private Long id;

    private String name;
    private String belongs;
    private String email;
    private String duty;
    private Long kakaoId;

    public static Client createClient(String name,String belongs,String email,ClientType clientType){
        if(clientType == ClientType.INSPECTOR){
            Client client = new Inspector();
            client.setName(name);
            client.setBelongs(belongs);
            client.setEmail(email);
            return client;
        }
        else{
            Client client = new Manufacturer();
            client.setName(name);
            client.setBelongs(belongs);
            client.setEmail(email);
            return client;
        }
    }

    public static Client createClient(String name,String belongs,String email,String duty,ClientType clientType){
        if(clientType == ClientType.INSPECTOR){
            Client client = new Inspector();
            client.setName(name);
            client.setBelongs(belongs);
            client.setEmail(email);
            client.setDuty(duty);
            return client;
        }
        else{
            Client client = new Manufacturer();
            client.setName(name);
            client.setBelongs(belongs);
            client.setEmail(email);
            client.setDuty(duty);
            return client;
        }
    }

}
