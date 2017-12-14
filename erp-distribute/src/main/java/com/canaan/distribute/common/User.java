package com.canaan.distribute.common;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
@Setter @Getter
public class User implements Serializable{
	private static final long serialVersionUID = 813429791993510856L;
	private Long id;
	private String name;
	private String realName;
	private Map<String,String> paramMap;
	
	
}
