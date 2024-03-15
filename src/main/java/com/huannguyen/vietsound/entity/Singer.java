package com.huannguyen.vietsound.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "singer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "stage_name")
    private String stageName;

    @Column(name = "debut")
    private String debut;

    @Column(name = "story")
    private String story;

    @Column(name = "name_model")
    private final String nameModel = "SINGER";

    @OneToMany(mappedBy = "singerOfAlbum")
    private List<Album> albumList;

    @OneToMany(mappedBy = "singerOfSong")
    private List<Song> songList;

//    @ManyToMany
//    @JoinTable(
//            name = "singer_song",
//            joinColumns = @JoinColumn(name = "singerId"),
//            inverseJoinColumns = @JoinColumn(name = "songId")
//    )
//    private List<Song> songOfSinger;

    @ManyToMany(mappedBy = "singerList",cascade = CascadeType.ALL)
    private List<User> userLikedSinger;
}
