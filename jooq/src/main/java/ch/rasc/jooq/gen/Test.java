/**
 * This class is generated by jOOQ
 */
package ch.rasc.jooq.gen;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value = { "http://www.jooq.org", "3.4.1" },
		comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Test extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = -1535937714;

	/**
	 * The singleton instance of <code>test</code>
	 */
	public static final Test TEST = new Test();

	/**
	 * No further instances allowed
	 */
	private Test() {
		super("test");
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final java.util.List<org.jooq.Table<?>> getTables0() {
		return java.util.Arrays.<org.jooq
				.Table<?>> asList(ch.rasc.jooq.gen.tables.Address.ADDRESS);
	}
}
