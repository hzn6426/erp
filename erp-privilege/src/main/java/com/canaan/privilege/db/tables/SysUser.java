/**
 * This class is generated by jOOQ
 */
package com.canaan.privilege.db.tables;


import com.canaan.privilege.db.DubboTest;
import com.canaan.privilege.db.Keys;
import com.canaan.privilege.db.tables.records.SysUserRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class SysUser extends TableImpl<SysUserRecord> {

    private static final long serialVersionUID = 1183551463;

    /**
     * The reference instance of <code>dubbo_test.sys_user</code>
     */
    public static final SysUser SYS_USER = new SysUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SysUserRecord> getRecordType() {
        return SysUserRecord.class;
    }

    /**
     * The column <code>dubbo_test.sys_user.id</code>.
     */
    public final TableField<SysUserRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>dubbo_test.sys_user.name</code>.
     */
    public final TableField<SysUserRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(16), this, "");

    /**
     * The column <code>dubbo_test.sys_user.passwd</code>.
     */
    public final TableField<SysUserRecord, String> PASSWD = createField("passwd", org.jooq.impl.SQLDataType.VARCHAR.length(20), this, "");

    /**
     * The column <code>dubbo_test.sys_user.mobile</code>.
     */
    public final TableField<SysUserRecord, String> MOBILE = createField("mobile", org.jooq.impl.SQLDataType.VARCHAR.length(12), this, "");

    /**
     * The column <code>dubbo_test.sys_user.real_name</code>.
     */
    public final TableField<SysUserRecord, String> REAL_NAME = createField("real_name", org.jooq.impl.SQLDataType.VARCHAR.length(16), this, "");

    /**
     * Create a <code>dubbo_test.sys_user</code> table reference
     */
    public SysUser() {
        this("sys_user", null);
    }

    /**
     * Create an aliased <code>dubbo_test.sys_user</code> table reference
     */
    public SysUser(String alias) {
        this(alias, SYS_USER);
    }

    private SysUser(String alias, Table<SysUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private SysUser(String alias, Table<SysUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DubboTest.DUBBO_TEST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<SysUserRecord> getPrimaryKey() {
        return Keys.KEY_SYS_USER_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<SysUserRecord>> getKeys() {
        return Arrays.<UniqueKey<SysUserRecord>>asList(Keys.KEY_SYS_USER_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysUser as(String alias) {
        return new SysUser(alias, this);
    }

    /**
     * Rename this table
     */
    public SysUser rename(String name) {
        return new SysUser(name, null);
    }
}
