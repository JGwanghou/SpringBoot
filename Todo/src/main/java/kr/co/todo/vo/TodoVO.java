package kr.co.todo.vo;

import lombok.*;

import java.time.LocalDate;


@Getter@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {
    private int no;
    private Integer tit;
    private String content;
    private Integer status;
    private LocalDate date;
}
