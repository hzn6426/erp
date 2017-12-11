
package com.canaan.privilege.bo;


import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class RoleBO implements Serializable {

    private static final long serialVersionUID = 1377985771;

    private Integer id;
    private String  name;
    private String  note;

    
}
