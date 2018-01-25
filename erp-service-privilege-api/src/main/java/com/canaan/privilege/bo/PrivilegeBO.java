package com.canaan.privilege.bo;


import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PrivilegeBO implements Serializable {

    private static final long serialVersionUID = -5124231;

    private Integer id;
    private String  code;
    private String  name;
    private String  sourceType;
    private Integer sourceId;

    
}
