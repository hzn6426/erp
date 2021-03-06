/**
 * This class is generated by jOOQ
 */
package com.canaan.privilege.db.tables.records;


import com.canaan.privilege.db.tables.SysButton;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class SysButtonRecord extends UpdatableRecordImpl<SysButtonRecord> implements Record5<Integer, String, String, String, Integer> {

    private static final long serialVersionUID = -1516699462;

    /**
     * Setter for <code>dubbo_test.sys_button.id</code>. 按钮ID
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_button.id</code>. 按钮ID
     */
    @NotNull
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>dubbo_test.sys_button.button_code</code>. 按钮编码
     */
    public void setButtonCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_button.button_code</code>. 按钮编码
     */
    @Size(max = 20)
    public String getButtonCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>dubbo_test.sys_button.button_name</code>. 按钮名称
     */
    public void setButtonName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_button.button_name</code>. 按钮名称
     */
    @Size(max = 50)
    public String getButtonName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>dubbo_test.sys_button.button_url</code>. 按钮URL
     */
    public void setButtonUrl(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_button.button_url</code>. 按钮URL
     */
    @Size(max = 50)
    public String getButtonUrl() {
        return (String) get(3);
    }

    /**
     * Setter for <code>dubbo_test.sys_button.menu_id</code>. 菜单ID
     */
    public void setMenuId(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>dubbo_test.sys_button.menu_id</code>. 菜单ID
     */
    public Integer getMenuId() {
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
        return SysButton.SYS_BUTTON.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return SysButton.SYS_BUTTON.BUTTON_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return SysButton.SYS_BUTTON.BUTTON_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return SysButton.SYS_BUTTON.BUTTON_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return SysButton.SYS_BUTTON.MENU_ID;
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
        return getButtonCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getButtonName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getButtonUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getMenuId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysButtonRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysButtonRecord value2(String value) {
        setButtonCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysButtonRecord value3(String value) {
        setButtonName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysButtonRecord value4(String value) {
        setButtonUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysButtonRecord value5(Integer value) {
        setMenuId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysButtonRecord values(Integer value1, String value2, String value3, String value4, Integer value5) {
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
     * Create a detached SysButtonRecord
     */
    public SysButtonRecord() {
        super(SysButton.SYS_BUTTON);
    }

    /**
     * Create a detached, initialised SysButtonRecord
     */
    public SysButtonRecord(Integer id, String buttonCode, String buttonName, String buttonUrl, Integer menuId) {
        super(SysButton.SYS_BUTTON);

        set(0, id);
        set(1, buttonCode);
        set(2, buttonName);
        set(3, buttonUrl);
        set(4, menuId);
    }
}
