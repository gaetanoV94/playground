/**
 * This class is generated by jOOQ
 */
package ch.rasc.jooq.gen.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value = { "http://www.jooq.org", "3.4.1" },
		comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Address
		extends org.jooq.impl.TableImpl<ch.rasc.jooq.gen.tables.records.AddressRecord> {

	private static final long serialVersionUID = 1614570579;

	/**
	 * The singleton instance of <code>test.address</code>
	 */
	public static final ch.rasc.jooq.gen.tables.Address ADDRESS = new ch.rasc.jooq.gen.tables.Address();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<ch.rasc.jooq.gen.tables.records.AddressRecord> getRecordType() {
		return ch.rasc.jooq.gen.tables.records.AddressRecord.class;
	}

	/**
	 * The column <code>test.address.id</code>.
	 */
	public final org.jooq.TableField<ch.rasc.jooq.gen.tables.records.AddressRecord, java.lang.Integer> ID = createField(
			"id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>test.address.firstName</code>.
	 */
	public final org.jooq.TableField<ch.rasc.jooq.gen.tables.records.AddressRecord, java.lang.String> FIRSTNAME = createField(
			"firstName", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>test.address.lastName</code>.
	 */
	public final org.jooq.TableField<ch.rasc.jooq.gen.tables.records.AddressRecord, java.lang.String> LASTNAME = createField(
			"lastName", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>test.address.street</code>.
	 */
	public final org.jooq.TableField<ch.rasc.jooq.gen.tables.records.AddressRecord, java.lang.String> STREET = createField(
			"street", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>test.address.city</code>.
	 */
	public final org.jooq.TableField<ch.rasc.jooq.gen.tables.records.AddressRecord, java.lang.String> CITY = createField(
			"city", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * Create a <code>test.address</code> table reference
	 */
	public Address() {
		this("address", null);
	}

	/**
	 * Create an aliased <code>test.address</code> table reference
	 */
	public Address(java.lang.String alias) {
		this(alias, ch.rasc.jooq.gen.tables.Address.ADDRESS);
	}

	private Address(java.lang.String alias,
			org.jooq.Table<ch.rasc.jooq.gen.tables.records.AddressRecord> aliased) {
		this(alias, aliased, null);
	}

	private Address(java.lang.String alias,
			org.jooq.Table<ch.rasc.jooq.gen.tables.records.AddressRecord> aliased,
			org.jooq.Field<?>[] parameters) {
		super(alias, ch.rasc.jooq.gen.Test.TEST, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<ch.rasc.jooq.gen.tables.records.AddressRecord, java.lang.Integer> getIdentity() {
		return ch.rasc.jooq.gen.Keys.IDENTITY_ADDRESS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<ch.rasc.jooq.gen.tables.records.AddressRecord> getPrimaryKey() {
		return ch.rasc.jooq.gen.Keys.KEY_ADDRESS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<ch.rasc.jooq.gen.tables.records.AddressRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<ch.rasc.jooq.gen.tables.records
				.AddressRecord>> asList(ch.rasc.jooq.gen.Keys.KEY_ADDRESS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ch.rasc.jooq.gen.tables.Address as(java.lang.String alias) {
		return new ch.rasc.jooq.gen.tables.Address(alias, this);
	}

	/**
	 * Rename this table
	 */
	public ch.rasc.jooq.gen.tables.Address rename(java.lang.String name) {
		return new ch.rasc.jooq.gen.tables.Address(name, null);
	}
}
