package com.canaan.common;

import java.io.Serializable;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Setter @Getter @AllArgsConstructor
public class SearchResult<T> implements Serializable{
	private static final long serialVersionUID = 4509800259617559166L;
	private int totalSize;
	private List<T> dataList;
}
