package com.huannguyen.vietsound.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "album")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "date_public")
    private String datePublic;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dislikes")
    private Integer dislikes;

    @Column(name = "name_model")
    private final String nameModel = "ALBUM";

    @OneToMany(mappedBy = "album")
    private List<Song> songInAlbum;

    @ManyToOne
    @JoinColumn(name = "category_of_album")
    private Category categoryOfAlbum;

    @ManyToOne
    @JoinColumn(name = "singer_of_album")
    private Singer singerOfAlbum;

    @ManyToMany(mappedBy = "albumList",cascade = CascadeType.ALL)
    private List<User> userLikedAlbum;
}
