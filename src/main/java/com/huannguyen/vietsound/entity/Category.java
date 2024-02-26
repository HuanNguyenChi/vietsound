package com.huannguyen.vietsound.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "popularity")
    private String popularity;

    @OneToMany(mappedBy = "categoryOfSong")
    private List<Song> songInCategory;

    @ManyToMany(mappedBy = "categoryLikeList")
    private List<User> userLikedCategory;
    @OneToMany(mappedBy = "categoryOfAlbum")
    private List<Album> albumInCategory;
}
