package model;

public class AllRequirementsModel {
   public int reqno;
   public String req;
   public String uName;

	public AllRequirementsModel(int reqno, String req,String uName) {
		this.reqno = reqno;
		this.req = req;
		this.uName = uName;
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

	public String getUName() {
		return uName;
	}

	public void setUName(String uName) {
		this.uName = uName;
	}
	
	
//
//	public int getReqno() {
//		return reqno;
//	}
//
//	public void setReqno(int reqno) {
//		this.reqno = reqno;
//	}
//
//	public String getReq() {
//		return req;
//	}
//
//	public void setReq(String req) {
//		this.req = req;
//	}
//	
//	public String getuName() {
//		return uName;
//	}
//
//	public void setuName(String uName) {
//		this.uName = uName;
//	}

}
