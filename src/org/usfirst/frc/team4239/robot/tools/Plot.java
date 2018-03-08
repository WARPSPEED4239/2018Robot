package org.usfirst.frc.team4239.robot.tools;


import java.util.Map;
import java.util.TreeMap;

public class Plot {
	private String mXAxisLabel;
	private String mYAxisLabel;
	private Map<Double, Double> mData;
	
	public Plot(String xAxisLabel, String yAxisLabel) {
		mXAxisLabel = xAxisLabel;
		mYAxisLabel = yAxisLabel;
		mData = new TreeMap<>();
	}
	
	public void addDataPoint(double xData, double yData) {
		mData.put(xData, yData);
	}
	
	public Plot getDerivative(String yName) {
		Plot derivative = new Plot(mXAxisLabel, yName);
		
		Map.Entry<Double, Double> lastEntry = null;
		for (Map.Entry<Double, Double> entry: mData.entrySet()) {
			if (lastEntry == null) {
				lastEntry = entry;
				continue;
			}
			double deltaX = entry.getKey() - lastEntry.getKey();
			double deltaY = entry.getValue() - lastEntry.getValue();
			derivative.addDataPoint(lastEntry.getKey(), deltaY / deltaX);
			lastEntry = entry;
		}
		
		return derivative;
	}
	
	public Fit getLinearRegression() {
		Fit result = new Fit();
		
		int length = mData.size();
		double xSum = 0.0;
		double ySum = 0.0;
		for (Map.Entry<Double, Double> entry: mData.entrySet()) {
			xSum += entry.getKey();
			ySum += entry.getValue();
		}
		
		double xMean = xSum / length;
		double yMean = ySum / length;
		
		double slopeNumerator = 0;
		double slopeDenominator = 0;
		for (Map.Entry<Double, Double> entry: mData.entrySet()) {
			slopeNumerator += (entry.getKey() - xMean) * (entry.getValue() - yMean);
			slopeDenominator += Math.pow((entry.getKey() - xMean), 2);
		}
		
		result.meanX = xMean;
		result.meanY = yMean;
		result.slope = slopeNumerator / slopeDenominator;
		result.yIntercept = yMean - result.slope * xMean;
		
		double sumSquaredTotal = 0.0;
		double sumSquaredError = 0.0;
		for (Map.Entry<Double, Double> entry: mData.entrySet()) {
			double xVal = entry.getKey();
			double yVal = entry.getValue();
			double yPred = result.slope * xVal + result.yIntercept;
			
			sumSquaredError += Math.pow(yVal - yPred, 2);
			sumSquaredTotal += Math.pow(yVal - yMean, 2);
		}
		
		result.fit = (sumSquaredTotal - sumSquaredError) / sumSquaredTotal;
		return result;
	}
	
	@Override
	public String toString() {
		return toString(false);
	}
	
	public String toString(boolean includeRegression) {
		StringBuilder sb = new StringBuilder(20 * mData.size() + 150);
		sb.append("\n###### PLOT START ######\n");
		sb.append(mXAxisLabel + ", " + mYAxisLabel + "\n");
		
		for (Map.Entry<Double, Double> entry: mData.entrySet()) {
			sb.append(entry.getKey() + ", " + entry.getValue() + "\n");
		}
		
		if (includeRegression) {
			sb.append("\nRegression Fit:\n");
			sb.append(getLinearRegression());
		}
		
		sb.append("###### PLOT END ######");

		return sb.toString();
	}
	
	class Fit {
		public double slope;
		public double yIntercept;
		public double meanX;
		public double meanY;
		public double fit;
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(150);
			sb.append("slope = " + slope + "\n");
			sb.append("yIntercept = " + yIntercept + "\n");
			sb.append("R^2 = " + fit + "\n");
			sb.append("meanX = " + meanX + "\n");
			sb.append("meanY = " + meanY + "\n");
			return sb.toString();
		}
		
	}

}
