package kr.co.sboard.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "board_file")
public class FileVO {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
	private int fno;
	private int parent;
	private String newName;
	private String oriName;
	private int download;
	private String rdate;
	
}
