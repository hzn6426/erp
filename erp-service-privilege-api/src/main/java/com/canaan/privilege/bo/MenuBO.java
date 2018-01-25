package com.canaan.privilege.bo;



import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MenuBO implements Serializable{

    private static final long serialVersionUID = -1106118488;

    private Integer id;
    private String  code;
    private String  name;
    private String  url;
    private Integer parentId;

    
}
