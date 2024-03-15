package com.huannguyen.vietsound.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "song")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "link")
    private String link;

    @Column(name = "content")
    private String content;

    @Column(name = "date_release")
    private String dateRelease;

    @Column(name = "listens")
    private Integer listens;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dislike")
    private Integer dislikes;

    @Column(name = "name_model")
    private final String nameModel = "SONG";
    @ManyToOne
    @JoinColumn(name = "category")
    private Category categoryOfSong;

    @ManyToOne
    @JoinColumn(name = "album")
    private Album album;

//    @ManyToMany(mappedBy = "songOfSinger")
//    private List<Singer> singerInSong;

    @ManyToOne
    @JoinColumn(name = "singer_of_song")
    private Singer singerOfSong;

    @ManyToMany(mappedBy = "songLike",cascade = CascadeType.ALL)
    private List<User> userLikedSong;
}
