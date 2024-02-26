package com.huannguyen.vietsound.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(mappedBy = "singerOfAlbum")
    private List<Album> albumList;

    @ManyToMany
    @JoinTable(
            name = "singer_song",
            joinColumns = @JoinColumn(name = "singerId"),
            inverseJoinColumns = @JoinColumn(name = "songId")
    )
    private List<Song> songOfSinger;

    @ManyToMany(mappedBy = "singerList")
    private List<User> userLikedSinger;
}
