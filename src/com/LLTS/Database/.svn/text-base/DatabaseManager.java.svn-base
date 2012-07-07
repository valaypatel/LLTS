package com.LLTS.Database;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager {

	static final String dbName = "LLTSDB.db";
	static final String companyInformationTable = "CompanyInfo";
	static final String companyInformationCreateStatment = "CREATE TABLE "
			+ companyInformationTable + " ("
			+ CompanyInformationDatabaseHolder.companyName + " text,"
			+ CompanyInformationDatabaseHolder.companyAddress + " text,"
			+ CompanyInformationDatabaseHolder.companyCity + " text,"
			+ CompanyInformationDatabaseHolder.companyState + " text,"
			+ CompanyInformationDatabaseHolder.companyZip + " text,"
			+ CompanyInformationDatabaseHolder.companyPhone + " text,"
			+ CompanyInformationDatabaseHolder.companyLocation + " text" + ")";
	static final String ticketInformationTable = "TicketInfo";
	static final String ticketInformationCreateStatment = "CREATE TABLE "
			+ ticketInformationTable + " ("
			+ TicketPackInformationDatabaseHolder.ticketValue + " real,"
			+ TicketPackInformationDatabaseHolder.digitInBarcode + " integer,"
			+ TicketPackInformationDatabaseHolder.firstGroup + " integer,"
			+ TicketPackInformationDatabaseHolder.secondGroup + " integer,"
			+ TicketPackInformationDatabaseHolder.thirdGroup + " integer)";

	static final String usersTable = "Users";
	static final String usersTableCreateStatment = "CREATE TABLE " + usersTable
			+ " (" + UserManagementDatabaseHolder.userID + " integer,"
			+ UserManagementDatabaseHolder.userName + " text,"
			+ UserManagementDatabaseHolder.password + " text,"
			+ UserManagementDatabaseHolder.group + " text)";

	static final String packTable = "Packs";
	static final String packTableCreateStatment = "CREATE TABLE " + packTable
			+ " (" + PackInformationDatabaseHolder.packID + " integer,"
			+ PackInformationDatabaseHolder.routeNumber + " text,"
			+ PackInformationDatabaseHolder.serialNumber + " text,"
			+ PackInformationDatabaseHolder.slotNumber + " text,"
			+ PackInformationDatabaseHolder.status + " text,"
			+ PackInformationDatabaseHolder.firstTicketNumber + " text,"
			+ PackInformationDatabaseHolder.ticketPrice + " real,"
			+ PackInformationDatabaseHolder.closeDate + " real,"
			+ PackInformationDatabaseHolder.openDate + " real)";
	
	static final String dailySalesTable = "DailySales";
	static final String dailySalesTableCreateStatment = "CREATE TABLE " + dailySalesTable
			+ " (" + DailySalesDatabaseHelper.transactionID + " integer," 
			+ DailySalesDatabaseHelper.packID + " integer,"
			+ DailySalesDatabaseHelper.openTicketNumber + " integer,"
			+ DailySalesDatabaseHelper.closeTicketNumber + " integer,"
			+ DailySalesDatabaseHelper.shiftOpenDate + " real,"
			+ DailySalesDatabaseHelper.shiftCloseDate + " real,"
			+ DailySalesDatabaseHelper.shiftInfo + " text)";


	static final String routesTable = "Routes";
	static final String routesTableCreateStatment = "CREATE TABLE "
			+ routesTable + " (" + RouteInformationDatabaseHelper.routeNumber
			+ " text," + RouteInformationDatabaseHelper.routeName + " text)";

	private static final String applicationTag = "LLTS Application";
	private static final int databaseVersion = 1;

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	/**
	 * constructor
	 */
	public DatabaseManager(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, dbName, null, databaseVersion);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(companyInformationCreateStatment);
			db.execSQL(ticketInformationCreateStatment);
			db.execSQL(usersTableCreateStatment);
			db.execSQL(packTableCreateStatment);
			db.execSQL(routesTableCreateStatment);
			db.execSQL(dailySalesTableCreateStatment);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(applicationTag, "Upgrading database from version "
					+ oldVersion + " to " + newVersion
					+ ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + companyInformationTable);
			db.execSQL("DROP TABLE IF EXISTS " + ticketInformationTable);
			db.execSQL("DROP TABLE IF EXISTS " + usersTable);
			db.execSQL("DROP TABLE IF EXISTS " + packTable);
			db.execSQL("DROP TABLE IF EXISTS " + routesTable);
			db.execSQL("DROP TABLE IF EXISTS " + dailySalesTable);
			onCreate(db);
		}
	}

	public DatabaseManager open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() throws SQLException {
		DBHelper.close();
	}

	public long insertCompanyInfornation(
			CompanyInformationDatabaseHolder companyInfo) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(CompanyInformationDatabaseHolder.companyName,
				companyInfo.getCompanyNameValue());
		initialValues.put(CompanyInformationDatabaseHolder.companyAddress,
				companyInfo.getCompanyAddressValue());
		initialValues.put(CompanyInformationDatabaseHolder.companyCity,
				companyInfo.getCompanyCityValue());
		initialValues.put(CompanyInformationDatabaseHolder.companyState,
				companyInfo.getCompanyStateValue());
		initialValues.put(CompanyInformationDatabaseHolder.companyZip,
				companyInfo.getCompanyZipValue());
		initialValues.put(CompanyInformationDatabaseHolder.companyPhone,
				companyInfo.getCompanyPhoneValue());
		initialValues.put(CompanyInformationDatabaseHolder.companyLocation,
				companyInfo.getCompanyLocationValue());
		return db.insert(companyInformationTable, null, initialValues);
	}

	public boolean isCompanyInformationStored() {
		Cursor cursor = db.query(companyInformationTable,
				new String[] { CompanyInformationDatabaseHolder.companyName, },
				null, null, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.close();
			return true;
		}
		cursor.close();
		return false;
	}

	public boolean deleteCompanyInformation() throws SQLiteException {
		return db.delete(companyInformationTable, null, null) > 0;
	}

	public CompanyInformationDatabaseHolder getCompanyInformation()
			throws SQLException {
		Cursor cursor = db.query(companyInformationTable, new String[] {
				CompanyInformationDatabaseHolder.companyName,
				CompanyInformationDatabaseHolder.companyAddress,
				CompanyInformationDatabaseHolder.companyCity,
				CompanyInformationDatabaseHolder.companyState,
				CompanyInformationDatabaseHolder.companyZip,
				CompanyInformationDatabaseHolder.companyPhone,
				CompanyInformationDatabaseHolder.companyLocation, }, null,
				null, null, null, null);
		cursor.moveToFirst();
		CompanyInformationDatabaseHolder companyInformationDatabaseHolder = new CompanyInformationDatabaseHolder();
		companyInformationDatabaseHolder
				.setCompanyNameValue(cursor.getString(cursor
						.getColumnIndex(CompanyInformationDatabaseHolder.companyName)));
		companyInformationDatabaseHolder
				.setCompanyAddressValue(cursor.getString(cursor
						.getColumnIndex(CompanyInformationDatabaseHolder.companyAddress)));
		companyInformationDatabaseHolder
				.setCompanyCityValue(cursor.getString(cursor
						.getColumnIndex(CompanyInformationDatabaseHolder.companyCity)));
		companyInformationDatabaseHolder
				.setCompanyStateValue(cursor.getString(cursor
						.getColumnIndex(CompanyInformationDatabaseHolder.companyState)));
		companyInformationDatabaseHolder
				.setCompanyZipValue(cursor.getString(cursor
						.getColumnIndex(CompanyInformationDatabaseHolder.companyZip)));
		companyInformationDatabaseHolder
				.setCompanyPhoneValue(cursor.getString(cursor
						.getColumnIndex(CompanyInformationDatabaseHolder.companyPhone)));
		companyInformationDatabaseHolder
				.setCompanyLocationValue(cursor.getString(cursor
						.getColumnIndex(CompanyInformationDatabaseHolder.companyLocation)));
		cursor.close();
		return companyInformationDatabaseHolder;
	}

	public long insertTicketInfornation(
			TicketPackInformationDatabaseHolder ticketInfo) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(TicketPackInformationDatabaseHolder.ticketValue,
				ticketInfo.getTickeValueValue());
		initialValues.put(TicketPackInformationDatabaseHolder.digitInBarcode,
				ticketInfo.getDigitInBarcodeValue());
		initialValues.put(TicketPackInformationDatabaseHolder.firstGroup,
				ticketInfo.getFirstGroupValue());
		initialValues.put(TicketPackInformationDatabaseHolder.secondGroup,
				ticketInfo.getSecondGroupValue());
		initialValues.put(TicketPackInformationDatabaseHolder.thirdGroup,
				ticketInfo.getThirdGroupValue());
		return db.insert(ticketInformationTable, null, initialValues);
	}

	public boolean isTicketInformationStored() {
		Cursor cursor = db
				.query(ticketInformationTable,
						new String[] { TicketPackInformationDatabaseHolder.ticketValue, },
						null, null, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.close();
			return true;
		}
		cursor.close();
		return false;
	}

	public boolean deleteTicketInformation() throws SQLiteException {
		return db.delete(ticketInformationTable, null, null) > 0;
	}

	public TicketPackInformationDatabaseHolder getTicketInformation()
			throws SQLException {
		Cursor cursor = db.query(ticketInformationTable, new String[] {
				TicketPackInformationDatabaseHolder.ticketValue,
				TicketPackInformationDatabaseHolder.digitInBarcode,
				TicketPackInformationDatabaseHolder.firstGroup,
				TicketPackInformationDatabaseHolder.secondGroup,
				TicketPackInformationDatabaseHolder.thirdGroup, }, null, null,
				null, null, null);
		cursor.moveToFirst();
		TicketPackInformationDatabaseHolder ticketPackInformationDatabaseHolder = new TicketPackInformationDatabaseHolder();
		ticketPackInformationDatabaseHolder
				.setTickeValueValue(cursor.getDouble(cursor
						.getColumnIndex(TicketPackInformationDatabaseHolder.ticketValue)));
		ticketPackInformationDatabaseHolder
				.setDigitInBarcodeValue(cursor.getInt(cursor
						.getColumnIndex(TicketPackInformationDatabaseHolder.digitInBarcode)));
		ticketPackInformationDatabaseHolder
				.setFirstGroupValue(cursor.getInt(cursor
						.getColumnIndex(TicketPackInformationDatabaseHolder.firstGroup)));
		ticketPackInformationDatabaseHolder
				.setSecondGroupValue(cursor.getInt(cursor
						.getColumnIndex(TicketPackInformationDatabaseHolder.secondGroup)));
		ticketPackInformationDatabaseHolder
				.setThirdGroupValue(cursor.getInt(cursor
						.getColumnIndex(TicketPackInformationDatabaseHolder.thirdGroup)));
		cursor.close();
		return ticketPackInformationDatabaseHolder;
	}

	public long insertUserInfornation(UserManagementDatabaseHolder userInfo) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(UserManagementDatabaseHolder.userID,
				userInfo.getUserIDValue());
		initialValues.put(UserManagementDatabaseHolder.userName,
				userInfo.getUserNameValue());
		initialValues.put(UserManagementDatabaseHolder.password,
				userInfo.getPasswordValue());
		initialValues.put(UserManagementDatabaseHolder.group,
				userInfo.getGroupValue());
		return db.insert(usersTable, null, initialValues);
	}

	
	public boolean updateUserInfornation(UserManagementDatabaseHolder userInfo) {
		UserManagementDatabaseHolder userManagementDatabaseHolder = this.checkUser(userInfo.getUserIDValue());
		if(userManagementDatabaseHolder.getUserIDValue()==0) return false;
		else {
		ContentValues args = new ContentValues();
		args.put(UserManagementDatabaseHolder.userID, userInfo.getUserIDValue());
		args.put(UserManagementDatabaseHolder.userName, userInfo.getUserNameValue());
		args.put(UserManagementDatabaseHolder.group, userInfo.getGroupValue());
		args.put(UserManagementDatabaseHolder.password, userInfo.getPasswordValue());
		return db.update(usersTable, args, UserManagementDatabaseHolder.userID + "='" + userInfo.getUserIDValue() +"'",
				null) > 0;
		}
	}

	
	public int getNumberofUsers() {
		Cursor cursor = db.query(usersTable,
				new String[] { UserManagementDatabaseHolder.userID, }, null,
				null, null, null, null);
		int noOfUsers = cursor.getCount();
		cursor.close();
		return noOfUsers;
	}

	public boolean deleteUserName(int userId) throws SQLiteException {
		Log.i("Application", userId + "");
		return db.delete(usersTable, UserManagementDatabaseHolder.userID + "="
				+ userId, null) > 0;
	}

	public UserManagementDatabaseHolder checkUser(String userName)
			throws SQLiteException {

		Cursor cursor = db.query(usersTable, new String[] {
				UserManagementDatabaseHolder.userID,
				UserManagementDatabaseHolder.userName,
				UserManagementDatabaseHolder.password,
				UserManagementDatabaseHolder.group, },
				UserManagementDatabaseHolder.userName + "='" + userName + "'",
				null, null, null, null);
		// cursor.moveToFirst();
		UserManagementDatabaseHolder userManagementDatabaseHolder = new UserManagementDatabaseHolder();
		if (cursor.getCount() == 1) {
			cursor.moveToNext();
			userManagementDatabaseHolder.setUserIDValue(cursor.getInt(cursor
					.getColumnIndex(UserManagementDatabaseHolder.userID)));
			userManagementDatabaseHolder
					.setUserNameValue(cursor.getString(cursor
							.getColumnIndex(UserManagementDatabaseHolder.userName)));
			userManagementDatabaseHolder
					.setPasswordValue(cursor.getString(cursor
							.getColumnIndex(UserManagementDatabaseHolder.password)));
			userManagementDatabaseHolder.setGroupValue(cursor.getString(cursor
					.getColumnIndex(UserManagementDatabaseHolder.group)));
		} else {
			userManagementDatabaseHolder.setUserIDValue(0);
			userManagementDatabaseHolder.setUserNameValue("");
			userManagementDatabaseHolder.setPasswordValue("");
			userManagementDatabaseHolder.setGroupValue("");

		}
		cursor.close();
		return userManagementDatabaseHolder;
	}

	public UserManagementDatabaseHolder checkUser(int userId)
			throws SQLiteException {

		Cursor cursor = db.query(usersTable, new String[] {
				UserManagementDatabaseHolder.userID,
				UserManagementDatabaseHolder.userName,
				UserManagementDatabaseHolder.password,
				UserManagementDatabaseHolder.group, },
				UserManagementDatabaseHolder.userID + "='" + userId + "'",
				null, null, null, null);
		// cursor.moveToFirst();
		UserManagementDatabaseHolder userManagementDatabaseHolder = new UserManagementDatabaseHolder();
		if (cursor.getCount() == 1) {
			cursor.moveToNext();
			userManagementDatabaseHolder.setUserIDValue(cursor.getInt(cursor
					.getColumnIndex(UserManagementDatabaseHolder.userID)));
			userManagementDatabaseHolder
					.setUserNameValue(cursor.getString(cursor
							.getColumnIndex(UserManagementDatabaseHolder.userName)));
			userManagementDatabaseHolder
					.setPasswordValue(cursor.getString(cursor
							.getColumnIndex(UserManagementDatabaseHolder.password)));
			userManagementDatabaseHolder.setGroupValue(cursor.getString(cursor
					.getColumnIndex(UserManagementDatabaseHolder.group)));
		} else {
			userManagementDatabaseHolder.setUserIDValue(0);
			userManagementDatabaseHolder.setUserNameValue("");
			userManagementDatabaseHolder.setPasswordValue("");
			userManagementDatabaseHolder.setGroupValue("");

		}
		cursor.close();
		return userManagementDatabaseHolder;
	}

	public boolean isUserNameStored(String userName) throws SQLiteException {

		Cursor cursor = db.query(usersTable, new String[] {
				UserManagementDatabaseHolder.userID,
				UserManagementDatabaseHolder.userName,
				UserManagementDatabaseHolder.password,
				UserManagementDatabaseHolder.group, },
				UserManagementDatabaseHolder.userName + "='" + userName + "'",
				null, null, null, null);
		// cursor.moveToFirst();
		if (cursor.getCount() == 0) {
			cursor.close();
			return false;
		} else {
			cursor.close();
			return true;

		}

	}

	public UserManagementDatabaseHolder[] getUsers() throws SQLException {
		Cursor cursor = db.query(usersTable, new String[] {
				UserManagementDatabaseHolder.userID,
				UserManagementDatabaseHolder.userName,
				UserManagementDatabaseHolder.password,
				UserManagementDatabaseHolder.group, }, null, null, null, null,
				null);
		// cursor.moveToFirst();
		UserManagementDatabaseHolder users[] = new UserManagementDatabaseHolder[cursor
				.getCount()];
		int i = 0;
		while (cursor.moveToNext()) {
			UserManagementDatabaseHolder userManagementDatabaseHolder = new UserManagementDatabaseHolder();
			userManagementDatabaseHolder.setUserIDValue(cursor.getInt(cursor
					.getColumnIndex(UserManagementDatabaseHolder.userID)));
			userManagementDatabaseHolder
					.setUserNameValue(cursor.getString(cursor
							.getColumnIndex(UserManagementDatabaseHolder.userName)));
			userManagementDatabaseHolder
					.setPasswordValue(cursor.getString(cursor
							.getColumnIndex(UserManagementDatabaseHolder.password)));
			userManagementDatabaseHolder.setGroupValue(cursor.getString(cursor
					.getColumnIndex(UserManagementDatabaseHolder.group)));
			users[i] = userManagementDatabaseHolder;
			i++;
		}
		cursor.close();
		return users;
	}

	public long openPack(PackInformationDatabaseHolder packInfo) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(PackInformationDatabaseHolder.packID,
				this.getNumberOfPacks()+1);
		initialValues.put(PackInformationDatabaseHolder.routeNumber,
				packInfo.getRouteNumberValue());
		initialValues.put(PackInformationDatabaseHolder.serialNumber,
				packInfo.getSerialNumberValue());
		initialValues.put(PackInformationDatabaseHolder.firstTicketNumber,
				packInfo.getFirstTicketNumberValue());
		initialValues.put(PackInformationDatabaseHolder.slotNumber,
				packInfo.getSlotNumberValue());
		initialValues.put(PackInformationDatabaseHolder.ticketPrice,
				packInfo.getTicketPriceValue());
		initialValues.put(PackInformationDatabaseHolder.status,
				packInfo.getStatusValue());
		initialValues.put(PackInformationDatabaseHolder.openDate, 
				packInfo.getPackOpenDateValue());
		initialValues.put(PackInformationDatabaseHolder.closeDate, 
				packInfo.getPackCloseDateValue());
		
		dailySalesStarted(this.getNumberOfPacks()+1, packInfo.getFirstTicketNumberValue());
        
		return db.insert(packTable, null, initialValues);
	}

	public int getNumberOfPacks() {
		Cursor cursor = db.query(packTable,
				new String[] { PackInformationDatabaseHolder.packID, },
				null, null, null, null, null);

		int noOfPacks = cursor.getCount();
		cursor.close();
		return noOfPacks;
	}
	
	public int getNumberOfOpenPacks() {
		Cursor cursor = db.query(packTable,
				new String[] { PackInformationDatabaseHolder.packID, },
				PackInformationDatabaseHolder.status
				+ "= 'Open'", null, null, null, null);

		int noOfPacks = cursor.getCount();
		cursor.close();
		return noOfPacks;
	}
	

	public boolean deletePack(int packID) throws SQLiteException {
		return db.delete(packTable, PackInformationDatabaseHolder.packID
				+ "=" + packID, null) > 0;
	}

	public boolean isPackAlreadyOpen(String routeNumber,String serialNumber) throws SQLException {
		Cursor cursor = db.query(packTable, new String[] {
				PackInformationDatabaseHolder.packID,
				PackInformationDatabaseHolder.routeNumber,
				PackInformationDatabaseHolder.serialNumber,
				PackInformationDatabaseHolder.firstTicketNumber,
				PackInformationDatabaseHolder.slotNumber,
				PackInformationDatabaseHolder.status, },
				PackInformationDatabaseHolder.routeNumber + "='" + routeNumber
						+ "' and " + PackInformationDatabaseHolder.serialNumber + "='" + serialNumber + "'", null, null, null, null);
		// cursor.moveToFirst();
		if (cursor.getCount() == 0) {
			cursor.close();
			return false;
		} else {
			return true;
		}
	}
	
	public int getPackId(String routeNumber,String serialNumber) throws SQLException {
		Cursor cursor = db.query(packTable, new String[] {
				PackInformationDatabaseHolder.packID,
				PackInformationDatabaseHolder.routeNumber,
				PackInformationDatabaseHolder.serialNumber,
				PackInformationDatabaseHolder.firstTicketNumber,
				PackInformationDatabaseHolder.slotNumber,
				PackInformationDatabaseHolder.status, },
				PackInformationDatabaseHolder.routeNumber + "='" + routeNumber
						+ "' and " + PackInformationDatabaseHolder.serialNumber + "='" + serialNumber + "'", null, null, null, null);
		// cursor.moveToFirst();
		if (cursor.getCount() == 0) {
			cursor.close();
			return 0;
		} else {
			cursor.moveToFirst();
			return cursor.getInt(cursor.getColumnIndex(PackInformationDatabaseHolder.packID));
		}
	}


	public boolean closePack(int packID) throws SQLException {
		
		PackInformationDatabaseHolder packInformationDatabaseHolder = this.getPackWithPackID(packID);
		if(packInformationDatabaseHolder.equals(null)) return false;
		else {
		ContentValues args = new ContentValues();
		args.put(PackInformationDatabaseHolder.status, "Close");
		args.put(PackInformationDatabaseHolder.closeDate, new Date().getTime());
		return db.update(packTable, args, PackInformationDatabaseHolder.packID + "=" + packID +"",
				null) > 0;
		}
	}

	public boolean isSlotFree(String slotNumber) throws SQLException {
		Cursor cursor = db.query(packTable, new String[] {
				PackInformationDatabaseHolder.slotNumber,
				PackInformationDatabaseHolder.status, },
				PackInformationDatabaseHolder.slotNumber + "='" + slotNumber
						+ "' and " + PackInformationDatabaseHolder.status
						+ "= 'Open'", null, null, null, null);
		// cursor.moveToFirst();
		if (cursor.getCount() == 0) {
			cursor.close();
			return false;
		} else {
			cursor.close();
			return true;
		}
	}
	public String getSlot(int packID) throws SQLException {
		Cursor cursor = db.query(packTable, new String[] {
				PackInformationDatabaseHolder.packID,
				PackInformationDatabaseHolder.slotNumber,
				PackInformationDatabaseHolder.status, },
				PackInformationDatabaseHolder.packID + "=" + packID +"", null, null, null, null);
		// cursor.moveToFirst();
		if (cursor.getCount() == 0) {
			cursor.close();
			return null;
		} else {
			cursor.moveToFirst();
			String slot =cursor.getString(cursor
					.getColumnIndex(PackInformationDatabaseHolder.slotNumber));
			cursor.close();
			return slot;
		}
	}
	public float getPackTicketPrice(int packID) throws SQLException {
		Cursor cursor = db.query(packTable, new String[] {
				PackInformationDatabaseHolder.packID,
				PackInformationDatabaseHolder.ticketPrice,
				PackInformationDatabaseHolder.status, },
				PackInformationDatabaseHolder.packID + "=" + packID +"", null, null, null, null);
		// cursor.moveToFirst();
		if (cursor.getCount() == 0) {
			cursor.close();
			return 0;
		} else {
			cursor.moveToFirst();
			float value =cursor.getFloat(cursor
					.getColumnIndex(PackInformationDatabaseHolder.ticketPrice));
			cursor.close();
			return value;
		}
	}
	public PackInformationDatabaseHolder getPackWithPackID(int packID) throws SQLException {
		Cursor cursor = db.query(packTable, new String[] {
				PackInformationDatabaseHolder.packID,
				PackInformationDatabaseHolder.routeNumber,
				PackInformationDatabaseHolder.serialNumber,
				PackInformationDatabaseHolder.firstTicketNumber,
				PackInformationDatabaseHolder.ticketPrice,
				PackInformationDatabaseHolder.slotNumber,
				PackInformationDatabaseHolder.status, }, PackInformationDatabaseHolder.packID + "=" + packID + "", null, null,
				null, null); 
		if (cursor.getCount()==1) {
			cursor.moveToFirst();
			PackInformationDatabaseHolder packInformationDatabaseHolder = new PackInformationDatabaseHolder();
			packInformationDatabaseHolder
					.setPackIDValue(cursor.getInt(cursor
							.getColumnIndex(PackInformationDatabaseHolder.packID)));
			packInformationDatabaseHolder
					.setRouteNumberValue(cursor.getString(cursor
							.getColumnIndex(PackInformationDatabaseHolder.routeNumber)));
			packInformationDatabaseHolder
					.setSerialNumberValue(cursor.getString(cursor
							.getColumnIndex(PackInformationDatabaseHolder.serialNumber)));
			packInformationDatabaseHolder
					.setFirstTicketNumberValue(cursor.getInt(cursor
							.getColumnIndex(PackInformationDatabaseHolder.firstTicketNumber)));
			packInformationDatabaseHolder
			.setTicketPriceValue(cursor.getFloat(cursor
					.getColumnIndex(PackInformationDatabaseHolder.ticketPrice)));
			packInformationDatabaseHolder
					.setSlotNumberValue(cursor.getString(cursor
							.getColumnIndex(PackInformationDatabaseHolder.slotNumber)));
			packInformationDatabaseHolder
					.setStatusValue(cursor.getString(cursor
							.getColumnIndex(PackInformationDatabaseHolder.status)));
			cursor.close();
			return packInformationDatabaseHolder;
		}
		else {
			cursor.close();
			return null;
		}
	
	}
	public PackInformationDatabaseHolder[] getPacks() throws SQLException {
		Cursor cursor = db.query(packTable, new String[] {
				PackInformationDatabaseHolder.packID,
				PackInformationDatabaseHolder.routeNumber,
				PackInformationDatabaseHolder.serialNumber,
				PackInformationDatabaseHolder.firstTicketNumber,
				PackInformationDatabaseHolder.slotNumber,
				PackInformationDatabaseHolder.ticketPrice,
				PackInformationDatabaseHolder.status, }, null, null, null,
				null, null);
		// cursor.moveToFirst();
		PackInformationDatabaseHolder packs[] = new PackInformationDatabaseHolder[cursor
				.getCount()];
		int i = 0;
		while (cursor.moveToNext()) {
			PackInformationDatabaseHolder packInformationDatabaseHolder = new PackInformationDatabaseHolder();
			packInformationDatabaseHolder
					.setPackIDValue(cursor.getInt(cursor
							.getColumnIndex(PackInformationDatabaseHolder.packID)));
			packInformationDatabaseHolder
					.setRouteNumberValue(cursor.getString(cursor
							.getColumnIndex(PackInformationDatabaseHolder.routeNumber)));
			packInformationDatabaseHolder
					.setSerialNumberValue(cursor.getString(cursor
							.getColumnIndex(PackInformationDatabaseHolder.serialNumber)));
			packInformationDatabaseHolder
					.setFirstTicketNumberValue(cursor.getInt(cursor
							.getColumnIndex(PackInformationDatabaseHolder.firstTicketNumber)));
			packInformationDatabaseHolder
			.setTicketPriceValue(cursor.getFloat(cursor
					.getColumnIndex(PackInformationDatabaseHolder.ticketPrice)));
			packInformationDatabaseHolder
					.setSlotNumberValue(cursor.getString(cursor
							.getColumnIndex(PackInformationDatabaseHolder.slotNumber)));
			packInformationDatabaseHolder
					.setStatusValue(cursor.getString(cursor
							.getColumnIndex(PackInformationDatabaseHolder.status)));
			packs[i] = packInformationDatabaseHolder;
			i++;
		}
		cursor.close();
		return packs;
	}
	
	public PackInformationDatabaseHolder[] getOpenPacks() throws SQLException {
		Cursor cursor = db.query(packTable, new String[] {
				PackInformationDatabaseHolder.packID,
				PackInformationDatabaseHolder.routeNumber,
				PackInformationDatabaseHolder.serialNumber,
				PackInformationDatabaseHolder.firstTicketNumber,
				PackInformationDatabaseHolder.slotNumber,
				PackInformationDatabaseHolder.ticketPrice,
				PackInformationDatabaseHolder.status, }, PackInformationDatabaseHolder.status
				+ "= 'Open'", null, null,
				null, null);
		// cursor.moveToFirst();
		PackInformationDatabaseHolder packs[] = new PackInformationDatabaseHolder[cursor
				.getCount()];
		int i = 0;
		while (cursor.moveToNext()) {
			PackInformationDatabaseHolder packInformationDatabaseHolder = new PackInformationDatabaseHolder();
			packInformationDatabaseHolder
					.setPackIDValue(cursor.getInt(cursor
							.getColumnIndex(PackInformationDatabaseHolder.packID)));
			packInformationDatabaseHolder
					.setRouteNumberValue(cursor.getString(cursor
							.getColumnIndex(PackInformationDatabaseHolder.routeNumber)));
			packInformationDatabaseHolder
					.setSerialNumberValue(cursor.getString(cursor
							.getColumnIndex(PackInformationDatabaseHolder.serialNumber)));
			packInformationDatabaseHolder
					.setFirstTicketNumberValue(cursor.getInt(cursor
							.getColumnIndex(PackInformationDatabaseHolder.firstTicketNumber)));
			packInformationDatabaseHolder
					.setSlotNumberValue(cursor.getString(cursor
							.getColumnIndex(PackInformationDatabaseHolder.slotNumber)));
			packInformationDatabaseHolder
			.setTicketPriceValue(cursor.getFloat(cursor
					.getColumnIndex(PackInformationDatabaseHolder.ticketPrice)));
			packInformationDatabaseHolder
					.setStatusValue(cursor.getString(cursor
							.getColumnIndex(PackInformationDatabaseHolder.status)));
			packs[i] = packInformationDatabaseHolder;
			i++;
		}
		cursor.close();
		return packs;
	}

	public long storeRoute(RouteInformationDatabaseHelper routeInfo) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(RouteInformationDatabaseHelper.routeNumber,
				routeInfo.getRouteNumberValue());
		initialValues.put(RouteInformationDatabaseHelper.routeName,
				routeInfo.getRouteNameValue());
		return db.insert(routesTable, null, initialValues);
	}

	public RouteInformationDatabaseHelper getRouteName(String routeNumber) {
		Cursor cursor = db.query(routesTable, new String[] {
				RouteInformationDatabaseHelper.routeNumber,
				RouteInformationDatabaseHelper.routeName, }, null, null, null,
				null, null);
		// cursor.moveToFirst();
		RouteInformationDatabaseHelper route = new RouteInformationDatabaseHelper();
		while (cursor.moveToNext()) {
			if (cursor
					.getString(
							cursor.getColumnIndex(PackInformationDatabaseHolder.routeNumber))
					.equals(routeNumber)) {
				route.setRouteNameValue(cursor.getString(cursor
						.getColumnIndex(RouteInformationDatabaseHelper.routeName)));
				route.setRouteNumberValue(cursor.getString(cursor
						.getColumnIndex(RouteInformationDatabaseHelper.routeNumber)));
			}
		}
		cursor.close();
		return route;
	}
	
	public int getNumberOfTransaction() {
		Cursor cursor = db.query(dailySalesTable,
				new String[] { DailySalesDatabaseHelper.transactionID, },
				null, null, null, null, null);

		int noOfPacks = cursor.getCount();
		cursor.close();
		return noOfPacks;
	}
	
	public long dailySalesStarted(int packID, int openTicketNumber) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DailySalesDatabaseHelper.transactionID,getNumberOfTransaction()+1);
		initialValues.put(DailySalesDatabaseHelper.packID,
				packID);
		initialValues.put(DailySalesDatabaseHelper.openTicketNumber,
				openTicketNumber);
		initialValues.put(DailySalesDatabaseHelper.shiftOpenDate,
				new Date().getTime());
		return db.insert(dailySalesTable, null, initialValues);
	}
	
	public DailySalesDatabaseHelper getDailyShiftByPackID(int packID) throws SQLException{
		
		
		Cursor cursor = db.query(dailySalesTable, new String[] {
				DailySalesDatabaseHelper.packID,
				DailySalesDatabaseHelper.transactionID,
				DailySalesDatabaseHelper.openTicketNumber, }, DailySalesDatabaseHelper.packID + "=" + packID, null, null,
				null, null); 
		if (cursor.getCount()>=1) {
			cursor.moveToLast();
			DailySalesDatabaseHelper dailySalesDatabaseHelper = new DailySalesDatabaseHelper();
			dailySalesDatabaseHelper
			.setTransactionIDValue(cursor.getInt(cursor
					.getColumnIndex(DailySalesDatabaseHelper.transactionID)));
			dailySalesDatabaseHelper
					.setPackIDValue(cursor.getInt(cursor
							.getColumnIndex(DailySalesDatabaseHelper.packID)));
			dailySalesDatabaseHelper
					.setOpenTicketNumberValue(cursor.getInt(cursor
							.getColumnIndex(DailySalesDatabaseHelper.openTicketNumber)));
			cursor.close();
			return dailySalesDatabaseHelper;
		}
		else
			return null;
			
	}
	
	public boolean dailySalesShiftEnds(int packID, int closeTicketNumber , String shiftValue) {
		DailySalesDatabaseHelper dailySalesDatabaseHelper = this.getDailyShiftByPackID(packID);
		if(dailySalesDatabaseHelper.equals(null)) return false;
		else {
		ContentValues args = new ContentValues();
		args.put(DailySalesDatabaseHelper.closeTicketNumber, closeTicketNumber);
		args.put(DailySalesDatabaseHelper.shiftInfo, shiftValue);
		args.put(DailySalesDatabaseHelper.shiftCloseDate, new Date().getTime());
		return db.update(dailySalesTable, args, DailySalesDatabaseHelper.transactionID + "=" + dailySalesDatabaseHelper.getTransactionIDValue() +"",
				null) > 0;
		}
	}

	
public DailySalesDatabaseHelper[] getDailySalesByDate(Date reportDate) throws SQLException{
		
		
		Cursor cursor = db.query(dailySalesTable, new String[] {
				DailySalesDatabaseHelper.packID,
				DailySalesDatabaseHelper.transactionID,
				DailySalesDatabaseHelper.openTicketNumber,
				DailySalesDatabaseHelper.closeTicketNumber,
				DailySalesDatabaseHelper.shiftOpenDate,
				DailySalesDatabaseHelper.shiftCloseDate,
				DailySalesDatabaseHelper.shiftInfo,}, DailySalesDatabaseHelper.closeTicketNumber + " IS NOT NULL", null, null,
				null, null); 
		if (cursor.getCount()>=1) {
			DailySalesDatabaseHelper dailySales[] = new DailySalesDatabaseHelper[cursor.getCount()];
			int i=0;
			while(cursor.moveToNext()) { 
				Date salesDate = new Date(cursor.getLong(cursor.getColumnIndex(DailySalesDatabaseHelper.shiftCloseDate)));
				if(salesDate.getDate()==reportDate.getDate() && salesDate.getMonth()==reportDate.getMonth() && salesDate.getYear()==reportDate.getYear()) {
					DailySalesDatabaseHelper dailySalesDatabaseHelper = new DailySalesDatabaseHelper();
					dailySalesDatabaseHelper
					.setTransactionIDValue(cursor.getInt(cursor
							.getColumnIndex(DailySalesDatabaseHelper.transactionID)));
					dailySalesDatabaseHelper
							.setPackIDValue(cursor.getInt(cursor
									.getColumnIndex(DailySalesDatabaseHelper.packID)));
					dailySalesDatabaseHelper
							.setOpenTicketNumberValue(cursor.getInt(cursor
									.getColumnIndex(DailySalesDatabaseHelper.openTicketNumber)));
					dailySalesDatabaseHelper
							.setCloseTicketNumberValue(cursor.getInt(cursor
									.getColumnIndex(DailySalesDatabaseHelper.closeTicketNumber)));
					dailySales[i]=dailySalesDatabaseHelper;
					i++; 
					
				}
			}
			if(i<cursor.getCount())
				dailySales[i]=null;
			cursor.close();
			
			return dailySales;
		}
		else
			return null;
			
	}
	/**
	 * 
	 * This method use to update the existing alarm based on the sent row id and
	 * new data for the Alarm
	 * 
	 * @param rowId
	 *            - int row id of the Alarm data which you want to update
	 * @param alarm
	 *            - New data of Alarm
	 * @return true if updated successfully and false if not
	 * @author Valay Patel, Aug 11, 2010
	 */
	// public boolean updateAlarm(int rowId, Alarm alarm) {
	// ContentValues args = new ContentValues();
	// args.put(ALARAM_NAME, alarm.getName());
	// args.put(LATITUDE, alarm.getLatitude());
	// args.put(LONGITUDE, alarm.getLongitude());
	// args.put(DISTANCE, alarm.getDistance());
	// args.put(ALERT_TYPE, alarm.getAlertType());
	// args.put(STATUS, alarm.getSet());
	// return db.update(DATABASE_TABLE_ALARM, args, KEY_ROWID + "=" + rowId,
	// null) > 0;
	// }

	/**
	 * 
	 * This method simply change the alarm status of specific alarm based on the
	 * sent RowId
	 * 
	 * @param rowId
	 *            - Row Id of alarm which you want to change the status
	 * @param status
	 *            - changed status... Enable or disable
	 * @return true if changed made successfully else false
	 * @author Valay Patel, Aug 11, 2010
	 */
	// public boolean changeAlarmStatus(int rowId,boolean status)
	// {
	// ContentValues args = new ContentValues();
	// args.put(STATUS, status);
	// return db.update(DATABASE_TABLE_ALARM, args, KEY_ROWID + "=" + rowId,
	// null) > 0;
	// }
}
