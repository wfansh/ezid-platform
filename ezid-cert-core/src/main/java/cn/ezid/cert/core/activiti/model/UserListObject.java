/**
 * 
 */
package cn.ezid.cert.core.activiti.model;

import java.util.List;

public class UserListObject {
	
    private List<UserObject> data;
    private int total;
    private int start;
    private String sort;
    private String order;
    private int size;

    /**
     * @return the data
     */
    public List<UserObject> getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(List<UserObject> data) {
        this.data = data;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start
     *            the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return the sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * @param sort
     *            the sort to set
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * @param order
     *            the order to set
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size
     *            the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

}
