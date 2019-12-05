package com.github.yeqinyong.seed.basemodel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页数据
 * @author 叶勤勇(卡虚)
 * @date 2019/12/05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageData<T> {

	private List<T> data;

	private int pageNo;

	private int pageSize;

	private Long totalCount;

}
