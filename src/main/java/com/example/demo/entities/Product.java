// Generated with g9.

package com.example.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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

@Entity(name = "products")
public class Product implements Serializable {

	/** Primary key. */
	protected static final String PK = "id";

	/**
	 * The optimistic lock. Available via standard bean get/set operations.
	 */
//    @Version
//    @Column(name="LOCK_FLAG")
//    private Integer lockFlag;
//
//    /**
//     * Access method for the lockFlag property.
//     *
//     * @return the current value of the lockFlag property
//     */
//    public Integer getLockFlag() {
//        return lockFlag;
//    }
//
//    /**
//     * Sets the value of the lockFlag property.
//     *
//     * @param aLockFlag the new value of the lockFlag property
//     */
//    public void setLockFlag(Integer aLockFlag) {
//        lockFlag = aLockFlag;
//    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false, length = 255)
	private String name;
	@Column(length = 255)
	private String image;
	@Column(nullable = false, precision = 10)
	private int price;
	@Column(name = "created_date", nullable = false)
	private LocalDate createdDate;
	@Column(nullable = false, precision = 10)
	private int available;
	@OneToMany(mappedBy = "product")
	private Set<OrderDetail> orderDetail;
	@ManyToOne(optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	/** Default constructor. */
	public Product() {
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

	/**
	 * Access method for name.
	 *
	 * @return the current value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method for name.
	 *
	 * @param aName the new value for name
	 */
	public void setName(String aName) {
		name = aName;
	}

	/**
	 * Access method for image.
	 *
	 * @return the current value of image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Setter method for image.
	 *
	 * @param aImage the new value for image
	 */
	public void setImage(String aImage) {
		image = aImage;
	}

	/**
	 * Access method for price.
	 *
	 * @return the current value of price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Setter method for price.
	 *
	 * @param aPrice the new value for price
	 */
	public void setPrice(int aPrice) {
		price = aPrice;
	}

	/**
	 * Access method for createdDate.
	 *
	 * @return the current value of createdDate
	 */
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	/**
	 * Setter method for createdDate.
	 *
	 * @param now the new value for createdDate
	 */
	public void setCreatedDate(LocalDate aCreatedDate) {
		createdDate = aCreatedDate;
	}

	/**
	 * Access method for available.
	 *
	 * @return the current value of available
	 */
	public int getAvailable() {
		return available;
	}

	/**
	 * Setter method for available.
	 *
	 * @param aAvailable the new value for available
	 */
	public void setAvailable(int aAvailable) {
		available = aAvailable;
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
	 * Access method for category.
	 *
	 * @return the current value of category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Setter method for category.
	 *
	 * @param aCategory the new value for category
	 */
	public void setCategory(Category aCategory) {
		category = aCategory;
	}

	/**
	 * Compares the key for this instance with another Product.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class Product and the key objects
	 *         are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Product)) {
			return false;
		}
		Product that = (Product) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another Product.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Product))
			return false;
		return this.equalKeys(other) && ((Product) other).equalKeys(this);
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
		StringBuffer sb = new StringBuffer("[Product |");
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
