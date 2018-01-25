package com.canaan.privilege.bo;



import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ElementBO implements Serializable {

    private static final long serialVersionUID = -854702143;

    private Integer id;
    private String  code;
    private String  name;
    private String  url;
    private String  elementType;
    private Integer menuId;

    
}
