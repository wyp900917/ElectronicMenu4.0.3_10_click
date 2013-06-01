package com.EleMenu403_click;

import android.app.Application;
/**
 * 全局Application类，该类中存放了一个变量，代表是否使用服务器
 * @author ping
 *
 */
public class myApplication extends Application {
	/**
	 * 静态常量，代表使用服务器，值为1
	 */
	//private static final int HAVESERVER = 1;
	
	private static final int NOSERVER = 0;
	private static final String SqlPath = "/mnt/sdcard/electronicMenu/sql/";
	
	private static final String TFSqlPath = "/mnt/extsd/electronicMenu/sql/";
	
	private static final String DBname = "electronicMenu.db";
	
	private static final String picturePath = "/mnt/sdcard/electronicMenu/picture/";
	
	private static final String TFpicturePath = "/mnt/extsd/electronicMenu/picture/";
	
	private static final String recommendPic = "recommend/";
	
	private static final String specialPic = "special/";
	
	private static final String coolPic = "cool/";
	
	private static final String hotPic = "hot/";
	
	private static final String seafoodPic = "seafood/";
	
	private static final String hotpotPic = "hotpot/";
	
	private static final String soupPic = "soup/";
	
	private static final String winePic = "wine/";
	
	private static final String defaultPic = "default/";
	/**
	 * 该变量代表了是否使用服务器
	 */
	private int isHaveServer;//1表示有服务器；0表示无服务器
	/**
	 * 获取变量值
	 * @return isHaveServer 返回是否使用服务器
	 */
	public int getIsHaveServer() {
		return isHaveServer;
	}
	/**
	 * 设置变量值
	 * @param isHaveServer 传递一个整形值，代表是否使用服务器
	 */
	public void setIsHaveServer(int isHaveServer) {
		this.isHaveServer = isHaveServer;
	}
	public String getSqlpath() {
		return SqlPath;
	}
	public String getTfsqlpath() {
		return TFSqlPath;
	}
	public String getDbname() {
		return DBname;
	}
	public String getPicturepath() {
		return picturePath;
	}
	public String getTfpicturepath() {
		return TFpicturePath;
	}
	public String getRecommendpic() {
		return recommendPic;
	}
	public String getSpecialpic() {
		return specialPic;
	}
	public String getCoolpic() {
		return coolPic;
	}
	public String getHotpic() {
		return hotPic;
	}
	public String getSeafoodpic() {
		return seafoodPic;
	}
	public String getHotpotpic() {
		return hotpotPic;
	}
	public String getSouppic() {
		return soupPic;
	}
	public String getWinepic() {
		return winePic;
	}
	public String getDefaultpic() {
		return defaultPic;
	}
	/**
	 * 默认的构造方法。调用了父类的onCreate方法，设置是否使用服务器
	 */
	public void onCreate() {
		super.onCreate();
		//setIsHaveServer(HAVESERVER);//初始化为使用服务器
		setIsHaveServer(NOSERVER);//初始化为不使用服务器
	}

}
