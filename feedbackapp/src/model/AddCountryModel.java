package model;

public class AddCountryModel {
    String countryname;
    int countryid;
    
    public AddCountryModel(int countryid,String countryname) {
    	this.countryid=countryid;
    	this.countryname= countryname;
    }
    
	public String getCountryname() {
		return countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	public int getCountryid() {
		return countryid;
	}
	public void setCountryid(int countryid) {
		this.countryid = countryid;
	}
}
