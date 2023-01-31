package kr.co.farmstory.vo;

import lombok.*;

@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentVO {

    private Integer no;
    private Integer parent;
    private String comment;
    private String uid;
    private String nick;
    private String regip;
    private String rdate;

    public String getRdate(){ return rdate.substring(2, 10); }
}
