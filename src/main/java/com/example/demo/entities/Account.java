// Generated with g9.

package com.example.demo.entities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="accounts", indexes={@Index(name="accounts_username_IX", columnList="username", unique=true), @Index(name="accounts_email_IX", columnList="email", unique=true)})
public class Account implements Serializable {

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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(unique=true, nullable=false, length=255)
    private String username;
    @Column(nullable=false, length=255)
    private String password;
    @Column(nullable=false, length=255)
    private String fullname;
    @Column(unique=true, nullable=false, length=255)
    private String email;
    @Column(length=255)
    private String photo;
    @Column(nullable=false, precision=10)
    private int activated;
    @Column(nullable=false, precision=10)
    private int admin;
    @OneToMany(mappedBy="account")
    private Set<Order> order;

    /** Default constructor. */
    public Account() {
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
     * Access method for username.
     *
     * @return the current value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for username.
     *
     * @param aUsername the new value for username
     */
    public void setUsername(String aUsername) {
        username = aUsername;
    }

    /**
     * Access method for password.
     *
     * @return the current value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for password.
     *
     * @param aPassword the new value for password
     */
    public void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * Access method for fullname.
     *
     * @return the current value of fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Setter method for fullname.
     *
     * @param aFullname the new value for fullname
     */
    public void setFullname(String aFullname) {
        fullname = aFullname;
    }

    /**
     * Access method for email.
     *
     * @return the current value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for email.
     *
     * @param aEmail the new value for email
     */
    public void setEmail(String aEmail) {
        email = aEmail;
    }

    /**
     * Access method for photo.
     *
     * @return the current value of photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Setter method for photo.
     *
     * @param aPhoto the new value for photo
     */
    public void setPhoto(String aPhoto) {
        photo = aPhoto;
    }

    /**
     * Access method for activated.
     *
     * @return the current value of activated
     */
    public int getActivated() {
        return activated;
    }

    /**
     * Setter method for activated.
     *
     * @param aActivated the new value for activated
     */
    public void setActivated(int aActivated) {
        activated = aActivated;
    }

    /**
     * Access method for admin.
     *
     * @return the current value of admin
     */
    public int getAdmin() {
        return admin;
    }

    /**
     * Setter method for admin.
     *
     * @param aAdmin the new value for admin
     */
    public void setAdmin(int aAdmin) {
        admin = aAdmin;
    }

    /**
     * Access method for order.
     *
     * @return the current value of order
     */
    public Set<Order> getOrder() {
        return order;
    }

    /**
     * Setter method for order.
     *
     * @param aOrder the new value for order
     */
    public void setOrder(Set<Order> aOrder) {
        order = aOrder;
    }

    /**
     * Compares the key for this instance with another Account.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Account and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Account)) {
            return false;
        }
        Account that = (Account) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Account.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Account)) return false;
        return this.equalKeys(other) && ((Account)other).equalKeys(this);
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
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Account |");
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
