package uwi.dcit.AgriExpenseTT.models;


import android.os.Parcel;
import android.os.Parcelable;

import uwi.dcit.agriexpensesvr.rPurchaseApi.model.RPurchase;


public class LocalResourcePurchase implements Parcelable{
	private int pId;
	private int resourceId;
	private String quantifier;
	private double qty;
	private double cost;
	private double qtyRemaining;
	private String type;
	
	public LocalResourcePurchase() {
		super();
	}
	public LocalResourcePurchase(int pId, int resourceId, String quantifier,
			double qty, double cost, double qtyRemaining,String type) {
		super();
		this.pId = pId;
		this.resourceId = resourceId;
		this.quantifier = quantifier;
		this.qty = qty;
		this.cost = cost;
		this.qtyRemaining = qtyRemaining;
		this.type=type;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getQuantifier() {
		return quantifier;
	}
	public void setQuantifier(String quantifier) {
		this.quantifier = quantifier;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getQtyRemaining() {
		return qtyRemaining;
	}
	public void setQtyRemaining(double qtyRemaining) {
		this.qtyRemaining = qtyRemaining;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "purchaseId:"+pId+" resourceId:"+resourceId+" quantifier:"+quantifier+" qty:"+qty+" cost:"+cost+" remaining:"+qtyRemaining;
	}
	
	public RPurchase toRPurchase(){
		RPurchase p=new RPurchase();
		p.setPId(this.pId);
		p.setCost(this.cost);
		p.setQty(this.qty);
		p.setQtyRemaining(this.qtyRemaining);
		p.setQuantifier(this.quantifier);
		p.setType(this.type);
		p.setResourceId(this.resourceId);
		return p;
	}
	
	@Override
	public int describeContents() {
		
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeInt(pId);
		dest.writeInt(resourceId);
		dest.writeString(type);
		dest.writeString(quantifier);
		dest.writeDouble(cost);
		dest.writeDouble(qty);
		dest.writeDouble(qtyRemaining);
	}
	public LocalResourcePurchase(Parcel dest){
		pId=dest.readInt();
		resourceId=dest.readInt();
		type=dest.readString();
		quantifier=dest.readString();
		cost=dest.readDouble();
		qty=dest.readDouble();
		qtyRemaining=dest.readDouble();
	}
	public final static Parcelable.Creator<LocalResourcePurchase> CREATOR = new Parcelable.Creator<LocalResourcePurchase>() {

		@Override
		public LocalResourcePurchase createFromParcel(Parcel source) {
			return new LocalResourcePurchase(source);
		}

		@Override
		public LocalResourcePurchase[] newArray(int size) {
			return new LocalResourcePurchase[size];
		}
	};
	
	
}
