package model;


public class YourRequirementsModel {
	int reqno;
	String req;

	public YourRequirementsModel(int reqno, String req) {
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
