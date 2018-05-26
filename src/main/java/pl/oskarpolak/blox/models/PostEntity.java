package pl.oskarpolak.blox.models;


import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "post")
@Getter @Setter
public class PostEntity {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String article;
    private String author;

    @Column(name = "creation_time")
    private LocalDateTime creationTime; // JSR310 (Java8 Time)

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id") // == @Column
    private CategoryEntity category;

    @OneToMany(mappedBy = "post", fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", author='" + author + '\'' +
                ", creationTime=" + creationTime +
                ", category=" + category +
                '}';
    }
}
