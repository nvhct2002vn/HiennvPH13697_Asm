// Generated with g9.

package com.example.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity(name = "orders")
public class Order implements Serializable {

	/** Primary key. */
	protected static final String PK = "id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(name = "create_date", nullable = false)
	private LocalDate createDate;
	@Column(nullable = false, length = 255)
	private String address;
	@Column(nullable = false, length = 10)
	private String sdt;
	@Column(nullable = false, precision = 10)
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@OneToMany(mappedBy = "order")
	private Set<OrderDetail> orderDetail;
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private Account account;

	/** Default constructor. */
	public Order() {
		super();
	}

	/**
	 * Access method for id.
	 *
	 * @return the current value of id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter method for id.
	 *
	 * @param aId the new value for id
	 */
	public void setId(int aId) {
		id = aId;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	/**
	 * Access method for createDate.
	 *
	 * @return the current value of createDate
	 */
	public LocalDate getCreateDate() {
		return createDate;
	}

	/**
	 * Setter method for createDate.
	 *
	 * @param aCreateDate the new value for createDate
	 */
	public void setCreateDate(LocalDate aCreateDate) {
		createDate = aCreateDate;
	}

	/**
	 * Access method for address.
	 *
	 * @return the current value of address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter method for address.
	 *
	 * @param aAddress the new value for address
	 */
	public void setAddress(String aAddress) {
		address = aAddress;
	}

	/**
	 * Access method for orderDetail.
	 *
	 * @return the current value of orderDetail
	 */
	public Set<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	/**
	 * Setter method for orderDetail.
	 *
	 * @param aOrderDetail the new value for orderDetail
	 */
	public void setOrderDetail(Set<OrderDetail> aOrderDetail) {
		orderDetail = aOrderDetail;
	}

	/**
	 * Access method for account.
	 *
	 * @return the current value of account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * Setter method for account.
	 *
	 * @param aAccount the new value for account
	 */
	public void setAccount(Account aAccount) {
		account = aAccount;
	}

	/**
	 * Compares the key for this instance with another Order.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class Order and the key objects
	 *         are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Order)) {
			return false;
		}
		Order that = (Order) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another Order.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Order))
			return false;
		return this.equalKeys(other) && ((Order) other).equalKeys(this);
	}

	/**
	 * Returns a hash code for this instance.
	 *
	 * @return Hash code
	 */
	@Override
	public int hashCode() {
		int i;
		int result = 17;
		i = getId();
		result = 37 * result + i;
		return result;
	}

	/**
	 * Returns a debug-friendly String representation of this instance.
	 *
	 * @return String representation of this instance
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("[Order |");
		sb.append(" id=").append(getId());
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Return all elements of the primary key.
	 *
	 * @return Map of key names to values
	 */
	public Map<String, Object> getPrimaryKey() {
		Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
		ret.put("id", Integer.valueOf(getId()));
		return ret;
	}

}
