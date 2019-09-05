package package_insideFunctions;

import java.util.List;


public class KeepAliveBase {
	private  List<KeepAlive> keepAliveBase;
	private static KeepAliveBase instance;
	private KeepAliveBase() {
	}
	public static KeepAliveBase getInstance() {
		if (instance == null){
			instance = new KeepAliveBase(); 
		}
		return instance;
	}
	public List<KeepAlive> getKeepAliveBase() {
		return keepAliveBase;
	}
	public void setKeepAliveBase(List<KeepAlive> keepAliveBase) {
		this.keepAliveBase = keepAliveBase;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keepAliveBase == null) ? 0 : keepAliveBase.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeepAliveBase other = (KeepAliveBase) obj;
		if (keepAliveBase == null) {
			if (other.keepAliveBase != null)
				return false;
		} else if (!keepAliveBase.equals(other.keepAliveBase))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "KeepAliveBase [keepAliveBase=" + keepAliveBase + "]";
	}
	
}
