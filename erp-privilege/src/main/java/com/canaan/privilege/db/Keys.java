/**
 * This class is generated by jOOQ
 */
package com.canaan.privilege.db;


import com.canaan.privilege.db.tables.SysElement;
import com.canaan.privilege.db.tables.SysMenu;
import com.canaan.privilege.db.tables.SysPrivilege;
import com.canaan.privilege.db.tables.SysRole;
import com.canaan.privilege.db.tables.SysRolePrivilege;
import com.canaan.privilege.db.tables.SysUser;
import com.canaan.privilege.db.tables.SysUserRole;
import com.canaan.privilege.db.tables.records.SysElementRecord;
import com.canaan.privilege.db.tables.records.SysMenuRecord;
import com.canaan.privilege.db.tables.records.SysPrivilegeRecord;
import com.canaan.privilege.db.tables.records.SysRolePrivilegeRecord;
import com.canaan.privilege.db.tables.records.SysRoleRecord;
import com.canaan.privilege.db.tables.records.SysUserRecord;
import com.canaan.privilege.db.tables.records.SysUserRoleRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>dubbo_test</code> 
 * schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<SysMenuRecord, Integer> IDENTITY_SYS_MENU = Identities0.IDENTITY_SYS_MENU;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<SysElementRecord> KEY_SYS_ELEMENT_PRIMARY = UniqueKeys0.KEY_SYS_ELEMENT_PRIMARY;
    public static final UniqueKey<SysMenuRecord> KEY_SYS_MENU_PRIMARY = UniqueKeys0.KEY_SYS_MENU_PRIMARY;
    public static final UniqueKey<SysPrivilegeRecord> KEY_SYS_PRIVILEGE_PRIMARY = UniqueKeys0.KEY_SYS_PRIVILEGE_PRIMARY;
    public static final UniqueKey<SysRoleRecord> KEY_SYS_ROLE_PRIMARY = UniqueKeys0.KEY_SYS_ROLE_PRIMARY;
    public static final UniqueKey<SysRolePrivilegeRecord> KEY_SYS_ROLE_PRIVILEGE_PRIMARY = UniqueKeys0.KEY_SYS_ROLE_PRIVILEGE_PRIMARY;
    public static final UniqueKey<SysUserRecord> KEY_SYS_USER_PRIMARY = UniqueKeys0.KEY_SYS_USER_PRIMARY;
    public static final UniqueKey<SysUserRoleRecord> KEY_SYS_USER_ROLE_PRIMARY = UniqueKeys0.KEY_SYS_USER_ROLE_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<SysMenuRecord, Integer> IDENTITY_SYS_MENU = createIdentity(SysMenu.SYS_MENU, SysMenu.SYS_MENU.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<SysElementRecord> KEY_SYS_ELEMENT_PRIMARY = createUniqueKey(SysElement.SYS_ELEMENT, "KEY_sys_element_PRIMARY", SysElement.SYS_ELEMENT.ID);
        public static final UniqueKey<SysMenuRecord> KEY_SYS_MENU_PRIMARY = createUniqueKey(SysMenu.SYS_MENU, "KEY_sys_menu_PRIMARY", SysMenu.SYS_MENU.ID);
        public static final UniqueKey<SysPrivilegeRecord> KEY_SYS_PRIVILEGE_PRIMARY = createUniqueKey(SysPrivilege.SYS_PRIVILEGE, "KEY_sys_privilege_PRIMARY", SysPrivilege.SYS_PRIVILEGE.ID);
        public static final UniqueKey<SysRoleRecord> KEY_SYS_ROLE_PRIMARY = createUniqueKey(SysRole.SYS_ROLE, "KEY_sys_role_PRIMARY", SysRole.SYS_ROLE.ID);
        public static final UniqueKey<SysRolePrivilegeRecord> KEY_SYS_ROLE_PRIVILEGE_PRIMARY = createUniqueKey(SysRolePrivilege.SYS_ROLE_PRIVILEGE, "KEY_sys_role_privilege_PRIMARY", SysRolePrivilege.SYS_ROLE_PRIVILEGE.ROLE_ID, SysRolePrivilege.SYS_ROLE_PRIVILEGE.PRIVILEGE_ID);
        public static final UniqueKey<SysUserRecord> KEY_SYS_USER_PRIMARY = createUniqueKey(SysUser.SYS_USER, "KEY_sys_user_PRIMARY", SysUser.SYS_USER.ID);
        public static final UniqueKey<SysUserRoleRecord> KEY_SYS_USER_ROLE_PRIMARY = createUniqueKey(SysUserRole.SYS_USER_ROLE, "KEY_sys_user_role_PRIMARY", SysUserRole.SYS_USER_ROLE.USER_ID, SysUserRole.SYS_USER_ROLE.ROLE_ID);
    }
}
