package com.EleMenu403_click;

import android.app.Application;
/**
 * ȫ��Application�࣬�����д����һ�������������Ƿ�ʹ�÷�����
 * @author ping
 *
 */
public class myApplication extends Application {
	/**
	 * ��̬����������ʹ�÷�������ֵΪ1
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
	 * �ñ����������Ƿ�ʹ�÷�����
	 */
	private int isHaveServer;//1��ʾ�з�������0��ʾ�޷�����
	/**
	 * ��ȡ����ֵ
	 * @return isHaveServer �����Ƿ�ʹ�÷�����
	 */
	public int getIsHaveServer() {
		return isHaveServer;
	}
	/**
	 * ���ñ���ֵ
	 * @param isHaveServer ����һ������ֵ�������Ƿ�ʹ�÷�����
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
	 * Ĭ�ϵĹ��췽���������˸����onCreate�����������Ƿ�ʹ�÷�����
	 */
	public void onCreate() {
		super.onCreate();
		//setIsHaveServer(HAVESERVER);//��ʼ��Ϊʹ�÷�����
		setIsHaveServer(NOSERVER);//��ʼ��Ϊ��ʹ�÷�����
	}

}
