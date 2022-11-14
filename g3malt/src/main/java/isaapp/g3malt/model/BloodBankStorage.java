package isaapp.g3malt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bloodBankStorage")
public class BloodBankStorage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="APlus", unique=false, nullable=true)
	private int APlus;
	@Column(name="BPlus", unique=false, nullable=true)
	private int BPlus;
	@Column(name="ABPlus", unique=false, nullable=true)
	private int ABPlus;
	@Column(name="OPlus", unique=false, nullable=true)
	private int OPlus;
	@Column(name="AMinus", unique=false, nullable=true)
	private int AMinus;
	@Column(name="BMinus", unique=false, nullable=true)
	private int BMinus;
	@Column(name="ABMinus", unique=false, nullable=true)
	private int ABMinus;
	@Column(name="OMinus", unique=false, nullable=true)
	private int OMinus;
	
	public BloodBankStorage() {
		super();
	}
	
	public BloodBankStorage(Integer id, int aPlus, int bPlus, int aBPlus, int oPlus, int aMinus, int bMinus,
			int aBMinus, int oMinus) {
		super();
		this.id = id;
		APlus = aPlus;
		BPlus = bPlus;
		ABPlus = aBPlus;
		OPlus = oPlus;
		AMinus = aMinus;
		BMinus = bMinus;
		ABMinus = aBMinus;
		OMinus = oMinus;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getAPlus() {
		return APlus;
	}
	public void setAPlus(int aPlus) {
		APlus = aPlus;
	}
	public int getBPlus() {
		return BPlus;
	}
	public void setBPlus(int bPlus) {
		BPlus = bPlus;
	}
	public int getABPlus() {
		return ABPlus;
	}
	public void setABPlus(int aBPlus) {
		ABPlus = aBPlus;
	}
	public int getOPlus() {
		return OPlus;
	}
	public void setOPlus(int oPlus) {
		OPlus = oPlus;
	}
	public int getAMinus() {
		return AMinus;
	}
	public void setAMinus(int aMinus) {
		AMinus = aMinus;
	}
	public int getBMinus() {
		return BMinus;
	}
	public void setBMinus(int bMinus) {
		BMinus = bMinus;
	}
	public int getABMinus() {
		return ABMinus;
	}
	public void setABMinus(int aBMinus) {
		ABMinus = aBMinus;
	}
	public int getOMinus() {
		return OMinus;
	}
	public void setOMinus(int oMinus) {
		OMinus = oMinus;
	}
	
	
}
