package com.nssliu.dataserver.entity;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/23 22:33
 * @describe:
 */
public class Table {
    private String column_name;
    private String type_name;
    private Integer index;

    public Table() {
    }

    public Table(String column_name, String type_name) {
        this.column_name = column_name;
        this.type_name = type_name;
    }

    public Table(String column_name, String type_name, Integer index) {
        this.column_name = column_name;
        this.type_name = type_name;
        this.index = index;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Table{" +
                "column_name='" + column_name + '\'' +
                ", type_name='" + type_name + '\'' +
                ", index=" + index +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        if (column_name != null ? !column_name.equals(table.column_name) : table.column_name != null) return false;
        if (type_name != null ? !type_name.equals(table.type_name) : table.type_name != null) return false;
        return index != null ? index.equals(table.index) : table.index == null;
    }

    @Override
    public int hashCode() {
        int result = column_name != null ? column_name.hashCode() : 0;
        result = 31 * result + (type_name != null ? type_name.hashCode() : 0);
        result = 31 * result + (index != null ? index.hashCode() : 0);
        return result;
    }
}
