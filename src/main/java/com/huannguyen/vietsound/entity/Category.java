package com.huannguyen.vietsound.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "description")
    private String description;

    @Column(name = "name_model")
    private final String nameModel = "CATEGORY";

    @OneToMany(mappedBy = "categoryOfSong")
    private List<Song> songInCategory;

    @ManyToMany(mappedBy = "categoryLikeList")
    private List<User> userLikedCategory;

    @OneToMany(mappedBy = "categoryOfAlbum")
    private List<Album> albumInCategory;
}
