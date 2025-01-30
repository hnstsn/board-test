package board.article.api;

import board.article.service.response.ArticleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

public class ArticleApiTest {

    private RestClient restClient = RestClient.create("http://localhost:9000");

    @Test
    public void createTest() {
        ArticleResponse response = create(new ArticleCreateRequest("title test", "content test", 1L, 1L));
        System.out.println("response = " + response);
        // articleId=141186950333251584, title=title test, content=content test, boardId=1, writerId=1, createdAt=2025-01-24T23:26:32.086665900, modifiedAt=2025-01-24T23:26:32.086665900
    }

    private ArticleResponse create(ArticleCreateRequest request) {
        return restClient.post()
                .uri("/v1/articles")
                .body(request)
                .retrieve()
                .body(ArticleResponse.class);
    }

    @Test
    public void readTest() {
        Long articleId = 141186950333251584L;
        ArticleResponse response = read(articleId);
        System.out.println("response = " + response);
    }

    private ArticleResponse read(Long articleId) {
        return restClient.get()
                .uri("/v1/articles/{articleId}", articleId)
                .retrieve()
                .body(ArticleResponse.class);
    }

    @Test
    public void updateTest() {
        Long articleId = 141186950333251584L;
        update(articleId);
        ArticleResponse response = read(articleId);
        System.out.println("response = " + response);
    }

    private void update(Long articleId) {
        restClient.put()
                .uri("/v1/articles/{articleId}", articleId)
                .body(new ArticleUpdateRequest("title update test", "content update test"))
                .retrieve();
    }


    @Transactional
    @Test
    public void deleteTest() {
        Long articleId = 141186950333251584L;
        restClient.delete()
                .uri("v1/articles/{articleId}", articleId)
                .retrieve();
    }

    @Getter
    @AllArgsConstructor
    static class ArticleCreateRequest {

        private String title;

        private String content;

        private Long writerId;

        private Long boardId;
    }

    @Getter
    @AllArgsConstructor
    static class ArticleUpdateRequest {

        private String title;

        private String content;
    }
}
