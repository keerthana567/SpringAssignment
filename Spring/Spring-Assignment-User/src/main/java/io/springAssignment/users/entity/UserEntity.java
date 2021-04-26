package io.springAssignment.users.entity;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String Name;
    private String email;
    private String location;

    public UserEntity() {    }

    public UserEntity(String id, String name, String email,String location) {
        this.id = id;
        Name = name;
        this.email = email;
        this.location =location;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {

        this.id = id;
    }

    public String getName()
    {

        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getEmail()
    {

        return email;
    }

    public void setEmail(String email)
    {

        this.email = email;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
}
