import java.util.*;
public class Class
{
	private String className;
	private String semester;

	private int numHomework;
	private double pctHomework;
	private int numHomeworkDropped;
	private boolean homework;

	private int numTests;
	private double[] pctTests;
	private boolean tests;

	private int numOther;
	private double[] pctOther;
	private boolean other;

	private boolean finalExam;
	private double pctFinalExam;
	public Class()
	{
		homework = false;
		tests = false;
		other = false;
		finalExam = false;

	}
	public Class(String className, String semester)
	{
		this.semester = semester;
		this.className = className;
		homework = false;
		tests = false;
		other = false;
		finalExam = false;
	}
	private void setHomework(int numHomework, double pctHomework, int numHomeworkDropped)
	{
		homework = true;
		this.numHomework = numHomework;
		this.pctHomework = pctHomework;
		this.numHomeworkDropped = numHomeworkDropped;
	}
	private void setTests(int numTests, String testPercentages)
	{
		tests = true;
		this.numTests = numTests;

		StringTokenizer token = new StringTokenizer(testPercentages);
		pctTests = new double[token.countTokens()];

		int count = 0;
		while(token.hasMoreTokens())
		{
			pctTests[count] = Double.parseDouble(token.nextToken());
			count++;
		}
	}
	private void setOther(int numOther, String otherPercentages)
	{
		other = true;
		this.numOther = numOther;

		StringTokenizer token = new StringTokenizer(otherPercentages);
		pctOther = new double[token.countTokens()];

		int count = 0;
		while(token.hasMoreTokens())
		{
			pctOther[count] = Double.parseDouble(token.nextToken());
			count++;
		}
	}
}