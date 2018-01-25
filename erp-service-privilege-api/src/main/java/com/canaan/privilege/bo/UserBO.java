package com.canaan.privilege.bo;


import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class UserBO implements Serializable {

    private static final long serialVersionUID = -1651105460;

    private Integer id;
    private String  name;
    private String  passwd;
    private String  mobile;
    private String  realName;

}
