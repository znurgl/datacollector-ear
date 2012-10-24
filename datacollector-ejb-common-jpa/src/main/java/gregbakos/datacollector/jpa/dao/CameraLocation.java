package gregbakos.datacollector.jpa.dao;

import java.io.Serializable;

public class CameraLocation extends Location implements Serializable {
	private static final long serialVersionUID = 6100984824055447318L;
	
	private boolean activeAlarm;

	public CameraLocation() {
	}
	
	public CameraLocation(Location location) {
		this.setConsumptions(location.getConsumptions());
		this.setCustomerGroups(location.getCustomerGroups());
		this.setCustomers(location.getCustomers());
		this.setDevices(location.getDevices());
		this.setDoor(location.getDoor());
		this.setId(location.getId());
		this.setLatitude(location.getLatitude());
		this.setLocality(location.getLocality());
		this.setLockVersion(location.getLockVersion());
		this.setLongitude(location.getLongitude());
		this.setLotNumber(location.getLotNumber());
		this.setPictures(location.getPictures());
		this.setPlacement(location.getPlacement());
		this.setPostalCode(location.getPostalCode());
		this.setProvider(location.getProvider());
		this.setProvisions(location.getProvisions());
		this.setStairCase(location.getStairCase());
		this.setStorey(location.getStorey());
		this.setStreet(location.getStreet());
		this.setStreetNumber(location.getStreetNumber());
	}
	
	public void setActiveAlarm(boolean activeAlarm) {
		this.activeAlarm = activeAlarm;
	}

	public boolean getActiveAlarm() {
		return activeAlarm;
	}
}
