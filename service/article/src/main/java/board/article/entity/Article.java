package board.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name ="article")
public class Article {

    @Id
    private Long articleId;

    private String title;

    private String content;

    private Long boardId; // shard key

    private Long writerId;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


    public static Article create(Long articleId, String title, String content, Long boardId, Long writerId) {
        Article article = new Article();
        article.articleId = articleId;
        article.title = title;
        article.content = content;
        article.boardId = boardId;
        article.writerId = writerId;
        article.createdAt = LocalDateTime.now();
        article.modifiedAt = article.createdAt;
        return article;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        modifiedAt = LocalDateTime.now();
    }
}
