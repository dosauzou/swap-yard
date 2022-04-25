package com.example.Swapyard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscriptions {
//    {"endpoint":"https://fcm.googleapis.com/fcm/send/eqQWvqoTNW8:APA91bExxToDtlXo5WJfJ6vIgVgchHg-uLafrPoylog_bqc1_xwdKapwwcpKLVIGsDB-lsze0QZFcWn1iY89vOi4BjIVJPUoKdXExzucAXdRr0tvdm-juOzuYV-cKN8QPAqQuXoyJnQS","expirationTime":null,"keys":{"p256dh":"BH0FWYceIXg_F0_zINMOiYOEmvDAxZ7rRVpoY_YmObx4OUb-Hb-IoxcAz6MU4GtLF-aNQZV5vC1vr-m67EXcx9A","auth":"UnScqVExosC8YJlsIIghNA"}}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String endpoint;
    private String expirationTime;
    @OneToOne(cascade = CascadeType.ALL)
    SubKeys keys;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubKeys getKey() {
        return keys;
    }

    public void setKey(SubKeys keys) {
        this.keys = keys;
    }

    public String getEndpoint() {
        return endpoint;
    }


    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

}
