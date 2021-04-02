package com.myProject.sport.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "myUsers")
@Data
public class User extends AbstractEntity {

    @Column
    @NotNull
    private String username;
    
    @Column
    @JsonIgnore
    @NotNull
    private String password;
        
    @Column
    @NonNull
    private float height;
    
    @Column
    @NotNull
    private float weight;
    
    @Column
    @NotNull
    private String goal;

    @Column
	@NotNull
	@OneToMany(mappedBy = "user")
	List<TrainingPerDay> trainings = new ArrayList<>();
   
    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    private List<Measurement> measurements = new ArrayList<Measurement>();

    @JsonIgnore
    @Fetch(value = FetchMode.SELECT)
    @ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"),
        uniqueConstraints = @UniqueConstraint(
                name="users_roles",
                columnNames = {"user_id", "role_id"})
    )
    private Collection<Role> roles = new ArrayList<Role>();

   
    public User() {
        super();
    }
    
    
    
    public List<String> getRoleListNames() {
        List<String> roleNames = new ArrayList<>();
        for (Role currRole : getRoles()) {
            roleNames.add(currRole.getName());
        }
        return roleNames;
    }

}
