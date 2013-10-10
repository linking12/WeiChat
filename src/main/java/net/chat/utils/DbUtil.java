package net.chat.utils;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.ConnectionFactory;

public class DbUtil {

	/**
	 * Logger for this class
	 */

	private Connection connection;

	private java.sql.Statement stmt;

	private static Hashtable dbHt = new Hashtable();

	private static Hashtable basic = new Hashtable();

	private String pwd;// = "123";

	private java.sql.ResultSet result;

	private List batchParam = null;

	private int count;

	private int fetchSize = 500;

	private boolean flag;

	private PreparedStatement pst = null;

	private String base;

	private DataSource dataSource;

	/**
	 * @param base
	 */
	public DbUtil(String base) {
		batchParam = new ArrayList();
		this.base = base;
		// setDataSource();
	}

	/**
         * 
         */
	public DbUtil(DataSource dataSource) {
		base = "jdbc";
		batchParam = new ArrayList();
		this.dataSource = dataSource;
	}

	public Connection getConnection() {
		return connection;
	}

	public void addBatchParam(String str) {
		batchParam.add(str);

	}

	public int batchSucc(int[] arr) {
		int updatesucc = 0;
		for (int i = 0; i < arr.length; i++) {
			int j = arr[i];
			if (j > 0) {
				updatesucc++;
			}

		}
		return updatesucc;
	}

	/**
	 * 返回执行结果的数组，数组中的值当大于 0时 对应的SQL执行成功，如果小于0则执行失败。
	 * 
	 * @param list
	 * @throws DBException
	 */
	public int[] excuteBatch(List list) throws DBException {
		int leng[];
		try {
			if (connection == null || connection.isClosed()) {
				this.createConn();
			}
			this.stmt = this.connection.createStatement();
			for (int i = 0; i < list.size(); i++) {
				String sql = (String) list.get(i);
				stmt.addBatch(sql);

			}

			leng = stmt.executeBatch();
			this.stmt.close();
		} catch (BatchUpdateException e) {
			leng = e.getUpdateCounts();
			throw new DBException(e);
		} catch (SQLException e) {
			throw new DBException(e);

		}
		return leng;

	}

	public void createConn() {
		try {

			for (int i = 0; i < 10; i++) {

				this.connection = getConnection(base);// dataSource.getConnection();
				// com.nm.tools.DataBase.getConnection("9588");//
				if (this.connection == null || this.connection.isClosed()) {

					continue;
				} else {
					break;
				}

			}

		} catch (Exception sqle) {

			sqle.printStackTrace();

		}

	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * execute update or delete command
	 * 
	 * @param sql
	 * @return update rows
	 * @throws DBException
	 */
	public int executeSql(String sql) throws SQLException {
		log("executeSql:" + sql);
		int temp = 0;
		try {
			if (connection == null || connection.isClosed()) {
				this.createConn();
			}
			// Center.log(sql);
			this.stmt = this.connection.createStatement();
			// Center.log(sql);
			temp = this.stmt.executeUpdate(sql);
			this.stmt.close();
		} catch (SQLException e) {

			throw e;

		}
		this.connClose();
		return temp;

	}

	/**
	 * quary sql and return result
	 * 
	 * @param sql
	 * @return
	 * @throws DBException
	 */
	public ResultSet getResultSet(String sql) throws DBException {
		try {

			if (connection == null || connection.isClosed()) {
				this.createConn();
			}

			this.stmt = this.connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			stmt.setFetchSize(fetchSize);
			this.result = stmt.executeQuery(sql);
			return this.result;
		} catch (SQLException e) {
			this.stmtClose();
			throw new DBException(e);
		}
	}

	public PreparedStatement createPrepearStmt(String sql) throws DBException {
		try {
			if (connection == null || connection.isClosed()) {
				this.createConn();
			}
			this.pst = this.connection.prepareStatement(sql);

		} catch (SQLException e) {
			this.stmtClose();
			throw new DBException(e);
		}

		return pst;

	}

	public ResultSet query(String sql) throws SQLException {
		try {
			if (connection == null || connection.isClosed()) {
				this.createConn();

			}

			this.stmt = this.connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.setFetchSize(fetchSize);
			this.result = stmt.executeQuery(sql);

		} catch (SQLException e) {
			this.stmtClose();
			throw e;
		}
		return this.result;

	}

	/**
	 * 以List 对象的形式返回结果集
	 * 
	 * @param rs
	 * @return
	 * @throws DBException
	 */
	public List getResultSetList(ResultSet rs) throws DBException {
		List list = new ArrayList();
		try {
			ResultSetMetaData rsm = rs.getMetaData();
			int col = rsm.getColumnCount();
			while (rs.next()) {
				Vector v = new Vector();
				for (int i = 1; i <= col; i++) {
					v.add(rs.getObject(i));
				}
				list.add(v);
			}
		} catch (SQLException e) {

			throw new DBException(e.toString());

		}

		return list;
	}

	/**
	 * 在list中放hashtable序列，一行就是一个hashtable，每一个hashtable的每一个值就是 一行的一列
	 * 
	 * @param rs
	 * @return
	 * @throws DBException
	 */
	public List getHashList() throws DBException {
		List list = new ArrayList();
		List colName = getColumNames();

		try {
			while (this.result.next()) {
				Hashtable hs = new Hashtable();
				for (int i = 0; i < colName.size(); i++) {
					String temp = colName.get(i) + "";
					String re = "";
					if (this.result.getObject(temp) != null) {
						re = this.result.getObject(temp).toString().trim();
					}
					hs.put(temp, re);

				}

				list.add(hs);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List getRSList(String sql) throws Exception {
		return getResultSetList(getResultSet(sql));
	}

	public List getRSList(String sql, int start, int size) throws Exception {

		return getResultSetList(getResultSet(sql));
	}

	public List getColumNames() {
		List list = new ArrayList();
		ResultSetMetaData rsm;
		try {
			rsm = result.getMetaData();
			int col = rsm.getColumnCount();
			for (int i = 1; i <= col; i++) {
				list.add(rsm.getColumnName(i));
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return list;

	}

	/**
	 * close resulset and statement object
	 */
	public void stmtClose() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {

		}
	}

	/**
	 * close connection object
	 * 
	 * @throws DBException
	 */
	public void connClose() {
		try {
			stmtClose();
			if (connection != null)
				this.connection.close();
			// this.connection=null;

		} catch (Exception e) {

		}
	}

	/**
	 * transction rollback
	 * 
	 * @throws DBException
	 */
	public void rollback() throws SQLException {
		try {
			this.connection.rollback();
		} catch (SQLException e) {
			throw e;

		}

	}

	/**
	 * set transction state
	 * 
	 * @param autoCommit
	 * @throws DBException
	 */
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		try {
			this.connection.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			throw e;

		}

	}

	/**
	 * @param list
	 * @throws DBException
	 */
	public int[] excuteBatch() throws DBException {
		int t[] = this.excuteBatch(batchParam);
		batchParam.clear();
		return t;

	}

	public int getBatchSize() {
		return batchParam.size();
	}

	/**
	 * 
	 * @param iterator
	 * @throws DBException
	 */
	public void excuteBatch(Iterator iterator) throws DBException {
		try {
			if (connection == null || connection.isClosed()) {
				this.createConn();
			}
			this.stmt = this.connection.createStatement();
			while (iterator.hasNext()) {

				stmt.addBatch(iterator.next() + "");

			}
			stmt.executeBatch();
			this.stmt.close();
		} catch (SQLException e) {
			throw new DBException(e.toString());

		}
		this.connClose();

	}

	/**
	 * transaction
	 * 
	 * @throws DBException
	 */
	public void commit() throws SQLException {
		try {
			this.connection.commit();
		} catch (SQLException e) {
			throw e;

		}

	}

	/**
	 * 如果返回真那，就有返回查询语句
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */

	public boolean excute(String sql) throws Exception {
		log("excute:" + sql);
		flag = false;
		try {
			// Center.log("do getResultSet");
			if (connection == null || connection.isClosed()) {
				this.createConn();
				// System.out.print("\n" + connection);
				// Center.log("no connection");
			}

			this.stmt = this.connection.createStatement();
			// ResultSet.CONCUR_READ_ONLY);

			flag = stmt.execute(sql);
			if (flag) {
				this.result = stmt.getResultSet();
				// result.last();
				// this.count=result.getRow();
				// result.beforeFirst();
				this.count = stmt.getFetchSize();
			} else {
				this.count = stmt.getUpdateCount();

			}

		} catch (SQLException e) {
			this.stmtClose();

			throw e;
		}
		return flag;
	}

	public void log(String sql) {
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return
	 */
	public String getBase() {
		return base;
	}

	/**
	 * @param string
	 */
	public void setBase(String string) {
		base = string;
	}

	public java.sql.ResultSet getResult() {
		return result;
	}

	public void setResult(java.sql.ResultSet result) {
		this.result = result;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return 返回 fetchSize。
	 */
	public int getFetchSize() {
		return this.fetchSize;
	}

	/**
	 * @param fetchSize
	 *            要设置的 fetchSize。
	 */
	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	public static void onclear() {
		if (dbHt == null)
			return;
		Enumeration keys = dbHt.keys();
		while (keys.hasMoreElements()) {
			Object ob = keys.nextElement();
			try {
				ConnectionFactory conf = (ConnectionFactory) dbHt.remove(ob);
				conf = null;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		keys = basic.keys();
		while (keys.hasMoreElements()) {
			Object ob = keys.nextElement();
			try {
				BasicDataSource db = (BasicDataSource) basic.remove(ob);
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public Connection getConnection(String sAlias) throws SQLException {
		return dataSource.getConnection();
	}

}
