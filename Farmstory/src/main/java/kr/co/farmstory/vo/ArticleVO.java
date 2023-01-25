package kr.co.farmstory.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
@Builder
public class ArticleVO {
    private int no;
    private int parent;
    private int comment;
    private String cate;
    private String title;
    private String content;
    private int file;
    private int hit;
    private String uid;
    private String regip;
    private String rdate;

    public String getRdate(){
        return rdate.substring(2, 10);
    }
}
