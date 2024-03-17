package com.huannguyen.vietsound.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "phone",unique = true)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "fullname")
    private String fullname;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    @JsonIgnore
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_album",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "albumId")
    )
    private List<Album> albumList;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_song",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "songId")
    )
    private List<Song> songLike;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_category",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId")
    )
    private List<Category> categoryLikeList;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_singer",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "singerId")
    )
    private List<Singer> singerList;

    public boolean hasRole(String role){
        for (Role roleItem : this.roles){
            if( roleItem.getName().equals(role)) return true;
        }
        return false;
    }
    public boolean hasSong(int id){
        for (Song songItem : this.songLike){
            if(songItem.getId().equals(id)) return true;
        }
        return false;
    }
    public boolean hasCategory(int id){
        for(Category categoryItem : this.categoryLikeList){
            if(categoryItem.getId().equals(id)) return true;
        }
        return false;
    }
    public boolean hasAlbum(int id){
        for(Album albumItem : this.albumList){
            if(albumItem.getId().equals(id)) return true;
        }
        return false;
    }
    public boolean hasSinger(int id){
        for(Singer singerItem : this.singerList){
            if(singerItem.getId().equals(id)) return true;
        }
        return false;
    }
}
