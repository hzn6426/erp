/**
 * This class is generated by jOOQ
 */
package com.canaan.privilege.db;


import com.canaan.privilege.db.tables.SysElement;
import com.canaan.privilege.db.tables.SysMenu;
import com.canaan.privilege.db.tables.SysPrivilege;
import com.canaan.privilege.db.tables.SysRole;
import com.canaan.privilege.db.tables.SysUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DubboTest extends SchemaImpl {

    private static final long serialVersionUID = -647040796;

    /**
     * The reference instance of <code>dubbo_test</code>
     */
    public static final DubboTest DUBBO_TEST = new DubboTest();

    /**
     * The table <code>dubbo_test.sys_element</code>.
     */
    public final SysElement SYS_ELEMENT = com.canaan.privilege.db.tables.SysElement.SYS_ELEMENT;

    /**
     * The table <code>dubbo_test.sys_menu</code>.
     */
    public final SysMenu SYS_MENU = com.canaan.privilege.db.tables.SysMenu.SYS_MENU;

    /**
     * The table <code>dubbo_test.sys_privilege</code>.
     */
    public final SysPrivilege SYS_PRIVILEGE = com.canaan.privilege.db.tables.SysPrivilege.SYS_PRIVILEGE;

    /**
     * The table <code>dubbo_test.sys_role</code>.
     */
    public final SysRole SYS_ROLE = com.canaan.privilege.db.tables.SysRole.SYS_ROLE;

    /**
     * The table <code>dubbo_test.sys_user</code>.
     */
    public final SysUser SYS_USER = com.canaan.privilege.db.tables.SysUser.SYS_USER;

    /**
     * No further instances allowed
     */
    private DubboTest() {
        super("dubbo_test", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            SysElement.SYS_ELEMENT,
            SysMenu.SYS_MENU,
            SysPrivilege.SYS_PRIVILEGE,
            SysRole.SYS_ROLE,
            SysUser.SYS_USER);
    }
}
