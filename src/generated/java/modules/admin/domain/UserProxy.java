package modules.admin.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.admin.UserProxy.UserProxyExtension;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateTime;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.DateTimeMapper;

/**
 * User
 * <br/>
 * A proxy version of the admin.User (without roles and groups etc) used for referencing.
 * 
 * @navhas n contact 1 Contact
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public abstract class UserProxy extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "admin";
	/** @hidden */
	public static final String DOCUMENT_NAME = "UserProxy";

	/** @hidden */
	public static final String userNamePropertyName = "userName";
	/** @hidden */
	public static final String createdDateTimePropertyName = "createdDateTime";
	/** @hidden */
	public static final String contactPropertyName = "contact";
	/** @hidden */
	public static final String inactivePropertyName = "inactive";

	/**
	 * admin.userProxy.userName.displayName
	 * <br/>
	 * Length is derived from the maximum email address length from RFC 5321
	 **/
	private String userName;
	/**
	 * admin.userProxy.createdDateTime.displayName
	 * <br/>
	 * admin.userProxy.createdDateTime.description
	 **/
	private DateTime createdDateTime;
	/**
	 * admin.userProxy.association.contact.displayName
	 * <br/>
	 * admin.userProxy.association.contact.description
	 **/
	private Contact contact = null;
	/**
	 * admin.userProxy.inactive.displayName
	 * <br/>
	 * admin.userProxy.inactive.description
	 **/
	private Boolean inactive;

	@Override
	@XmlTransient
	public String getBizModule() {
		return UserProxy.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return UserProxy.DOCUMENT_NAME;
	}

	public static UserProxyExtension newInstance() {
		try {
			return CORE.getUser().getCustomer().getModule(MODULE_NAME).getDocument(CORE.getUser().getCustomer(), DOCUMENT_NAME).newInstance(CORE.getUser());
		}
		catch (RuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			throw new DomainException(e);
		}
	}

	@Override
	@XmlTransient
	public String getBizKey() {
		try {
			return org.skyve.util.Binder.formatMessage("{userName} - {contact.bizKey}", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof UserProxy) && 
					this.getBizId().equals(((UserProxy) o).getBizId()));
	}

	/**
	 * {@link #userName} accessor.
	 * @return	The value.
	 **/
	public String getUserName() {
		return userName;
	}

	/**
	 * {@link #userName} mutator.
	 * @param userName	The new value.
	 **/
	@XmlElement
	public void setUserName(String userName) {
		preset(userNamePropertyName, userName);
		this.userName = userName;
	}

	/**
	 * {@link #createdDateTime} accessor.
	 * @return	The value.
	 **/
	public DateTime getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * {@link #createdDateTime} mutator.
	 * @param createdDateTime	The new value.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(DateTimeMapper.class)
	@XmlElement
	public void setCreatedDateTime(DateTime createdDateTime) {
		preset(createdDateTimePropertyName, createdDateTime);
		this.createdDateTime = createdDateTime;
	}

	/**
	 * {@link #contact} accessor.
	 * @return	The value.
	 **/
	public Contact getContact() {
		return contact;
	}

	/**
	 * {@link #contact} mutator.
	 * @param contact	The new value.
	 **/
	@XmlElement
	public void setContact(Contact contact) {
		if (this.contact != contact) {
			preset(contactPropertyName, contact);
			this.contact = contact;
		}
	}

	/**
	 * {@link #inactive} accessor.
	 * @return	The value.
	 **/
	public Boolean getInactive() {
		return inactive;
	}

	/**
	 * {@link #inactive} mutator.
	 * @param inactive	The new value.
	 **/
	@XmlElement
	public void setInactive(Boolean inactive) {
		preset(inactivePropertyName, inactive);
		this.inactive = inactive;
	}
}
