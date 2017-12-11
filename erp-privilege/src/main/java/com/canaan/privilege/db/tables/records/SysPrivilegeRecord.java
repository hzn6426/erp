/**
 * This class is generated by jOOQ
 */
package com.canaan.privilege.db.tables.records;


import com.canaan.privilege.db.tables.SysPrivilege;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
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
public class SysPrivilegeRecord extends UpdatableRecordImpl<SysPrivilegeRecord> implements Record5<Integer, String, String, String, Integer> {

    private static final long serialVersionUID = 1418525187;

    /**
     * Setter for <code>dubbo_test.sys_privilege.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_privilege.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>dubbo_test.sys_privilege.code</code>.
     */
    public void setCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_privilege.code</code>.
     */
    public String getCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>dubbo_test.sys_privilege.name</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_privilege.name</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>dubbo_test.sys_privilege.source_type</code>.
     */
    public void setSourceType(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_privilege.source_type</code>.
     */
    public String getSourceType() {
        return (String) get(3);
    }

    /**
     * Setter for <code>dubbo_test.sys_privilege.source_id</code>.
     */
    public void setSourceId(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_privilege.source_id</code>.
     */
    public Integer getSourceId() {
        return (Integer) get(4);
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
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, String, String, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, String, String, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return SysPrivilege.SYS_PRIVILEGE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return SysPrivilege.SYS_PRIVILEGE.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return SysPrivilege.SYS_PRIVILEGE.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return SysPrivilege.SYS_PRIVILEGE.SOURCE_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return SysPrivilege.SYS_PRIVILEGE.SOURCE_ID;
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
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getSourceType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getSourceId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysPrivilegeRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysPrivilegeRecord value2(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysPrivilegeRecord value3(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysPrivilegeRecord value4(String value) {
        setSourceType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysPrivilegeRecord value5(Integer value) {
        setSourceId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysPrivilegeRecord values(Integer value1, String value2, String value3, String value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SysPrivilegeRecord
     */
    public SysPrivilegeRecord() {
        super(SysPrivilege.SYS_PRIVILEGE);
    }

    /**
     * Create a detached, initialised SysPrivilegeRecord
     */
    public SysPrivilegeRecord(Integer id, String code, String name, String sourceType, Integer sourceId) {
        super(SysPrivilege.SYS_PRIVILEGE);

        set(0, id);
        set(1, code);
        set(2, name);
        set(3, sourceType);
        set(4, sourceId);
    }
}
