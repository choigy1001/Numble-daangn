package numble.daangnservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    public String nickname;
    public String comment;
    public String image;
    public LocalDateTime createdAt;

//    @Builder
//    public CommentDto(String nickname, String comment) {
//        this.nickname = nickname;
//        this.comment = comment;
//    }
}
