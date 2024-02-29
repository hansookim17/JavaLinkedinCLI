package edu.institution.asn9;

public class MetricData {
	private SortAlgorithm sortAlgorithm;

	private TimeComplexity timeComplexity;

	private long executionTime;
	
	//CONSTRUCTOR
	public MetricData(SortAlgorithm inAlgorithm) {
		this.sortAlgorithm = inAlgorithm;
	}
	
	public SortAlgorithm getSortAlgorithm() {
		return sortAlgorithm;
	}

	public void setSortAlgorithm(SortAlgorithm sortAlgorithm) {
		this.sortAlgorithm = sortAlgorithm;
	}

	public TimeComplexity getTimeComplexity() {
		return timeComplexity;
	}

	public void setTimeComplexity(TimeComplexity timeComplexity) {
		this.timeComplexity = timeComplexity;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sortAlgorithm == null) ? 0 : sortAlgorithm.hashCode());
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
		MetricData other = (MetricData) obj;
		if (sortAlgorithm != other.sortAlgorithm)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MetricData [sortAlgorithm=" + sortAlgorithm + ", timeComplexity=" + timeComplexity + ", executionTime="
				+ executionTime + "]";
	}
	
	

	
}
