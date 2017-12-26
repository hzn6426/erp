/**
 * This class is generated by jOOQ
 */
package com.canaan.privilege.db.tables.records;


import com.canaan.privilege.db.tables.SysRole;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


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
public class SysRoleRecord extends UpdatableRecordImpl<SysRoleRecord> implements Record4<Integer, String, String, String> {

    private static final long serialVersionUID = 1526200400;

    /**
     * Setter for <code>dubbo_test.sys_role.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_role.id</code>.
     */
    @NotNull
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>dubbo_test.sys_role.role_code</code>.
     */
    public void setRoleCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_role.role_code</code>.
     */
    @Size(max = 20)
    public String getRoleCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>dubbo_test.sys_role.role_name</code>.
     */
    public void setRoleName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_role.role_name</code>.
     */
    @Size(max = 50)
    public String getRoleName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>dubbo_test.sys_role.note</code>.
     */
    public void setNote(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_role.note</code>.
     */
    @Size(max = 255)
    public String getNote() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return SysRole.SYS_ROLE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return SysRole.SYS_ROLE.ROLE_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return SysRole.SYS_ROLE.ROLE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return SysRole.SYS_ROLE.NOTE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getRoleCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getRoleName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getNote();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysRoleRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysRoleRecord value2(String value) {
        setRoleCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysRoleRecord value3(String value) {
        setRoleName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysRoleRecord value4(String value) {
        setNote(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysRoleRecord values(Integer value1, String value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SysRoleRecord
     */
    public SysRoleRecord() {
        super(SysRole.SYS_ROLE);
    }

    /**
     * Create a detached, initialised SysRoleRecord
     */
    public SysRoleRecord(Integer id, String roleCode, String roleName, String note) {
        super(SysRole.SYS_ROLE);

        set(0, id);
        set(1, roleCode);
        set(2, roleName);
        set(3, note);
    }
}
