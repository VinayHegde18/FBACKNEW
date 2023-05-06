package model;

public class AllRequirementsModel {
   public static int reqno;
   public static String req;

	public AllRequirementsModel(int reqno, String req) {
		this.reqno = reqno;
		this.req = req;
	}

	public int getReqno() {
		return reqno;
	}

	public void setReqno(int reqno) {
		this.reqno = reqno;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

}
