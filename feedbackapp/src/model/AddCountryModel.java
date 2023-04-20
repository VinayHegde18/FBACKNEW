package model;

public class AddCountryModel {
    String countryNames;
    public AddCountryModel(String countryNames) {
    	this.countryNames= countryNames;
    }
    
    public String getCountryNames() {
    	return countryNames;
    }
    public void setCountryNames(String countryNames) {
    	this.countryNames = countryNames;
    }
}
