package pl.oskarpolak.blox.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "comment")
@Getter @Setter
public class CommentEntity {
    @Id
    @GeneratedValue
    private int id;
    private String content;
    private String author;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private PostEntity post;


    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", creationTime=" + creationTime +
                ", post=" + post.toString() +
                '}';
    }
}
