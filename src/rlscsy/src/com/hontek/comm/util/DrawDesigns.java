package com.hontek.comm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 画图工具,主要用来画二维和三维图形(饼状图,柱状图,曲线图) 
 * 使用说明：
 * 1, 创建该类的一个构造函数,如果构造函数里的参数不是你想要的,你可以通过get/set方法设置. 
 * 2, 根据自己的需要画什么样的图就调用什么方法。 
 * 3, 例子： DrawDesigns d = new DrawDesigns(......); String str = d.drawPie2D();
 * 这个str参数就是全部数据,前台可以直接使用这个参数(str),注意页面不用导入js文件。
 * 前台页面:(比如jsp页面) ${str} 或者 <%=str%>
 * 
 * @author joe
 * 
 */
public class DrawDesigns {
	
	// default params
	private static final String CAPTION = "统计分析图";

	private static final String XAXISNAME = "数据源名称";

	private static final String YAXISNAME = "Value";

	private static final Integer WIDTH = 600;// 默认的宽度,意思同width变量一样

	private static final Integer HEIGHT = 400;// 默认的高度,意思同height变量一样

	private static final String JSPATH = "FusionCharts/FusionCharts.js";

	private static final String SWFPATH = "FusionCharts/";

	// private static final String XMLHEADER = "<?xml version=\"1.0\"
	// encoding=\"UTF-8\"?>";

	// user-defind
	private String caption;// 图形的标题名称

	private String xAxisName;// 横向坐标轴(x轴)名称

	private String yAxisName;// 纵向坐标轴(y轴)名称

	private Integer width;// x轴宽,其实设置成Double类型的变量也可以,但是没有必要精算到浮点型,Integer就够了,除非业务有必要的说明

	private Integer height;// y轴宽,其实设置成Double类型的变量也可以,但是没有必要精算到浮点型,Integer就够了,除非业务有必要的说明

	private String jsPath;// 这种写法意思是FusionCharts这个包和你自己写的文件包处于同一个目录下,默认的js路径就是这个

	private String swfPath;// 这个只能指定包名,因为这个Charts包下面全是swf文件,只能根据客户需求加swf文件

	private String divId = "drawDiv" + UUID.randomUUID().toString();// 把封装好的xml数据放到前台页面显示在哪个区域,比如div,所以areaName意思是指定这个区域的名字,这里给他一个默认的。

	private String colors[] = { "1D8BD1" };// 指定颜色,可以根据自己的喜欢随便写,这个数组的长度不限,主要是显示图形时好区分,如果你弄成统一的颜色,会很单调,可以考虑编写个随机颜色程序。

	private String myChartName;

	// 一维数据(意思是比如曲线图上只显示一条曲线)
	private List<Map<Object, Object>> oneDimensionsList = new ArrayList<Map<Object, Object>>();

	// 多维数据(意思是比如曲线图上显示多条曲线)
	private Map<Object, Map<Object, Object>> manyDimensionsMap = new HashMap<Object, Map<Object, Object>>();

	// x轴名称集合(是用来做多条曲线用的,主要是多个集合共享x轴名称)
	private List<String> xAxisNameList = new ArrayList<String>();

	private boolean benchmark = false;//是否显示基准线

	private String benchmarkName;//基准线名称

	private Object benchmarkValue;//基准线值

	/**
	 * 默认的构造方法
	 */
	public DrawDesigns() {
		this.verifyParams();
	}

	/**
	 * 针对一维数据所建立的构造方法
	 * @param caption
	 * @param xAxisName
	 * @param yAxisName
	 * @param width
	 * @param height
	 * @param oneDimensionsList
	 */
	public DrawDesigns(String caption, String xAxisName, String yAxisName,
			Integer width, Integer height, List<Map<Object, Object>> oneDimensionsList) {
		this.caption = caption;
		this.xAxisName = xAxisName;
		this.yAxisName = yAxisName;
		this.width = width;
		this.height = height;
		this.oneDimensionsList = oneDimensionsList;
		this.verifyParams();
	}

	/**
	 * 针对一维数据所建立的构造方法,该方法含有js路径
	 * @param caption
	 * @param xAxisName
	 * @param yAxisName
	 * @param width
	 * @param height
	 * @param jsPath
	 * @param swfPath
	 * @param oneDimensionsList
	 */
	public DrawDesigns(String caption, String xAxisName, String yAxisName,
			Integer width, Integer height, String jsPath, String swfPath,
			List<Map<Object, Object>> oneDimensionsList) {
		this.caption = caption;
		this.xAxisName = xAxisName;
		this.yAxisName = yAxisName;
		this.width = width;
		this.height = height;
		this.jsPath = jsPath;
		this.swfPath = swfPath;
		this.oneDimensionsList = oneDimensionsList;
		this.verifyParams();
	}

	/**
	 * 该构造方法是为多维数据写的：
	 * List<String>含义是x轴的名称,
	 * Map<Object, Map<Object, Object>>含义是每一维所对应的值的集合.
	 * 例子:List<String> = {"一月","二月","三月","四月","五月"}
	 * Map<Map<Object, Map<Object, Object>>> 
	 * = {
	 * {2009年,{{"一月",100},{"二月",200},{"三月",300}...}},
	 * {2010年,{{"一月",100},{"二月",200},{"三月",300}...}}...
	 * }
	 * @param caption
	 * @param xAxisName
	 * @param yAxisName
	 * @param width
	 * @param height
	 * @param jsPath
	 * @param swfPath
	 * @param xAxisNameList
	 * @param manyDimensionsMap
	 */
	public DrawDesigns(String caption, String xAxisName, String yAxisName,
			Integer width, Integer height, String jsPath, String swfPath,
			List<String> xAxisNameList,
			Map<Object, Map<Object, Object>> manyDimensionsMap) {
		this.caption = caption;
		this.xAxisName = xAxisName;
		this.yAxisName = yAxisName;
		this.width = width;
		this.height = height;
		this.jsPath = jsPath;
		this.swfPath = swfPath;
		this.xAxisNameList = xAxisNameList;
		this.manyDimensionsMap = manyDimensionsMap;
	}
	
	/**
	 * 针对多维数据所建立的构造方法
	 * 同上一样,只不过是不带js路径
	 * @param caption
	 * @param xAxisName
	 * @param yAxisName
	 * @param width
	 * @param height
	 * @param xAxisNameList
	 * @param manyDimensionsMap
	 */
	public DrawDesigns(String caption, String xAxisName, String yAxisName,
			Integer width, Integer height, List<String> xAxisNameList,
			Map<Object, Map<Object, Object>> manyDimensionsMap) {
		this.caption = caption;
		this.xAxisName = xAxisName;
		this.yAxisName = yAxisName;
		this.width = width;
		this.height = height;
		this.xAxisNameList = xAxisNameList;
		this.manyDimensionsMap = manyDimensionsMap;
	}

	/**
	 * 获取swf动画的路径
	 * 
	 * @param swfName
	 * @return
	 */
	private String getSWFPath(String swfName) {
		return this.swfPath + swfName;
	}

	/**
	 * 客户有可能需要达标线,所以这里设置一下达标线的参数
	 * 
	 * @param ifBenchmark
	 *            是否显示达标线
	 * @param benchmarkName
	 *            达标线名称
	 * @param benchmarkValue
	 *            达标线值
	 */
	public void setBenchmark(boolean ifBenchmark, String benchmarkName,
			Object benchmarkValue) {
		this.benchmark = ifBenchmark;
		this.benchmarkName = benchmarkName;
		this.benchmarkValue = benchmarkValue;
	}

	/**
	 * 如果用户在前台不设置参数,例如这个参数为null或者是"",那么这里给他一个默认的参数值
	 */
	private void verifyParams() {
		if (this.width == null || this.width <= 0) {
			this.width = WIDTH;
		}
		if (this.height == null || this.height <= 0) {
			this.height = HEIGHT;
		}
		if (this.xAxisName == null || this.xAxisName.equals("")) {
			this.xAxisName = XAXISNAME;
		}
		if (this.yAxisName == null || this.yAxisName.equals("")) {
			this.yAxisName = YAXISNAME;
		}
		if (this.caption == null || this.caption.equals("")) {
			this.caption = CAPTION;
		}
		if (this.jsPath == null || this.jsPath.equals("")) {
			this.jsPath = JSPATH;
		}
		if (this.swfPath == null || this.swfPath.equals("")) {
			this.swfPath = SWFPATH;
		}
		if (this.myChartName == null || this.myChartName.equals("")) {
			this.myChartName = "myChart" + (new Random()).nextInt(10000);
		}
		if (this.oneDimensionsList.isEmpty()) {
			this.oneDimensionsList = new ArrayList<Map<Object,Object>>();
		}
		if (this.manyDimensionsMap.isEmpty()) {
			this.manyDimensionsMap = new HashMap<Object, Map<Object,Object>>();
		}
		if(this.xAxisNameList.isEmpty()) {
			this.xAxisNameList = new ArrayList<String>();
		}
		// 以下代码(三个for循环)是做测试用的
		/*for (int i = 0; i < 10; i++) {			//一维
			int value = (new Random()).nextInt(100);
			Map<Object, Object> map = new HashMap<Object,Object>();
			map.put("x", "你好" + i);
			map.put("y", value);
			this.oneDimensionsList.add(map);
		}
		
		//多维
		for (int i = 1; i <= 12; i++) {
			this.xAxisNameList.add(i + "");
		}
		
		for (int i = 0; i < 2; i++) {
			Map<Object, Object> m = new HashMap<Object, Object>();
			for (int j = 1; j <= this.xAxisNameList.size(); j++) {
				m.put(this.xAxisNameList.get(j - 1), (new Random())
						.nextInt(100));
			}
			this.manyDimensionsMap.put("201" + i, m);
		}*/
	}

	/**
	 * 加载js
	 * 
	 * @return
	 */
	private String getDivAndJs() {
		return "<div style=\"width: 0px; height: 0px\"><script type=\"text/javascript\" src=\""
				+ this.jsPath
				+ "\"></script></div>"
				+ "<div id=\""
				+ this.divId + "\" align = \"center\">Fusion Chart.</div>";
	}

	/**
	 * 解析一维图形的xml数据
	 * 
	 * @return
	 */
	private String getOneDimensionsXmlData() {
		StringBuffer buffer = new StringBuffer("");
		buffer
				.append("<chart caption='")
				.append(this.caption)
				.append("' xAxisName='")
				.append(this.xAxisName)
				.append("' yAxisName='")
				.append(this.yAxisName)
				.append(
						"' showNames='1' decimalPrecision='0' baseFontSize='12'  formatNumberScale='0' >");
		for (Map<Object, Object> map : this.oneDimensionsList) {
			buffer.append("<set label='").append(map.get("x")).append(
					"' value='").append(map.get("y")).append("' color='")
					.append(this.getRandomColor()).append("' />");
		}
		buffer.append("</chart>");
		return buffer.toString();
	}

	/**
	 * 画图,主要是把js,xml,swf等数据封装起来
	 * 
	 * @param swfName
	 * @param xmlData
	 * @return
	 */
	private String getDrawChart(String swfName, String xmlData) {
		this.divId = "drawDiv" + UUID.randomUUID().toString();
		StringBuffer buffer = new StringBuffer(this.getDivAndJs()
				+ "<script type=\"text/javascript\"> ");
		buffer.append("var ").append(this.myChartName).append(
				"= new FusionCharts(\"" + this.getSWFPath(swfName)
						+ "\", \"myChart" + UUID.randomUUID().toString()
						+ "\", \"" + this.width + "\", \"" + this.height
						+ "\", \"0\", \"0\"); ").append(this.myChartName)
				.append(".setDataXML(\"" + xmlData + "\"); ").append(
						this.myChartName).append(".render(\"").append(
						this.divId).append("\");</script>");
		return buffer.toString();
	}

	/**
	 * 解析多维图形的xml数据
	 * 
	 * @return
	 */
	private String getManyDimensionsXmlData() {
		StringBuffer buffer = new StringBuffer("");
		buffer
				.append("<chart caption='")
				.append(this.caption)
				.append("' xAxisName='")
				.append(this.xAxisName)
				.append("' yAxisName='")
				.append(this.yAxisName)
				.append(
						"' showValues='0' baseFontSize='12' palette='1' showFCMenuItem='1' imageSave='1'>");
		buffer.append("<categories>");
		for (String str : xAxisNameList) {
			buffer.append("<category label='" + str + "' />");
		}
		buffer.append("</categories>");
		for (Map.Entry<Object, Map<Object, Object>> values : this.manyDimensionsMap.entrySet()) {
			buffer.append("<dataset seriesName='").append(values.getKey()).append("'>");
			for (String str : this.xAxisNameList) {
				buffer.append("<set value='").append(values.getValue().get(str)).append("'/>");
			}
			buffer.append("</dataset>");
		}
		buffer.append(this.benchmark());
		buffer.append("</chart>");
		return buffer.toString();
	}

	/**
	 * 封装达标线的xml数据
	 * @return
	 */
	private String benchmark() {
		StringBuffer buffer = new StringBuffer("");
		if (this.benchmark
				&& (this.benchmarkName != null && !this.benchmarkName
						.equals(""))
				&& (this.benchmarkValue != null && !this.benchmarkValue
						.equals(""))) {
			buffer.append("<trendlines><line startValue='").append(
					this.benchmarkValue).append(
					"' color='91C728' displayValue='").append(
					this.benchmarkName).append(
					"' showOnTop='1' /></trendlines>");
		}
		return buffer.toString();
	}
	
	/**
	 * 从colors数组随机选取一个颜色
	 * 
	 * @return
	 */
	private String getColor() {
		if (oneDimensionsList.size() <= 0) {
			return this.colors[(new Random()).nextInt(this.colors.length)];
		} else {
			return this.colors[(new Random()).nextInt(oneDimensionsList.size())];
		}
	}

	/**
	 * 生产随机颜色
	 * 
	 * @return
	 */
	private String getRandomColor() {
		return (UUID.randomUUID().toString()).substring(0, 6);
	}

	/**
	 * 2维饼状图
	 * 
	 * @return
	 */
	public String drawPie2D() {
		return this.getDrawChart("Pie2D.swf", this.getOneDimensionsXmlData());

	}

	/**
	 * 3维饼状图
	 * 
	 * @return
	 */
	public String drawPie3D() {
		return this.getDrawChart("Pie3D.swf", this.getOneDimensionsXmlData());
	}

	/**
	 * 2维柱状图
	 * 
	 * @return
	 */
	public String drawColumn2D() {
		return this.getDrawChart("Column2D.swf", this.getOneDimensionsXmlData());
	}

	/**
	 * 3维柱状图
	 * 
	 * @return
	 */
	public String drawColumn3D() {
		return this.getDrawChart("Column3D.swf", this.getOneDimensionsXmlData());
	}

	/**
	 * 2维曲线图
	 * 
	 * @return
	 */
	public String drawLine2D() {
		return this.getDrawChart("Line.swf", this.getOneDimensionsXmlData());
	}

	/**
	 * 3维曲线图
	 * 这个方法暂时不能使用,因为关于曲线图一维数据的swf文件暂时没找到
	 * @return
	 */
	public String drawLine3D() {
		return this.getDrawChart("Line.swf", this.getOneDimensionsXmlData());
	}
	


	// ==============以下是多维数据柱状图,曲线图===============
	

	/**
	 * 多维2D柱状图
	 * 
	 * @return
	 */
	public String drawColumn2DGroup() {
		return this.getDrawChart("MSColumn2D.swf", this
				.getManyDimensionsXmlData());
	}

	/**
	 * 多维2D曲线图
	 * 
	 * @return
	 */
	public String drawLine2DGroup() {
		return this.getDrawChart("MSLine.swf", this.getManyDimensionsXmlData());
	}

	/**
	 * 多维3D柱状图
	 * 
	 * @return
	 */
	public String drawColumn3DGroup() {
		return this.getDrawChart("MSColumn3D.swf", this
				.getManyDimensionsXmlData());
	}
	


	/**
	 * 多维3D曲线图
	 * 
	 * @return
	 */
	public String drawLine3DGroup() {
		return this.getDrawChart("MSLine.swf", this
				.getManyDimensionsXmlData());
	}
	
	/**
	 * 多维3D柱状曲线图
	 * 
	 * @return
	 */
	public String drawMSColumn3DLineDY() {
		return this.getDrawChart("MSColumn3DLineDY.swf", this
				.getManyDimensionsXmlData());
	}

	// ==========get/set方法begin==============

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getXAxisName() {
		return xAxisName;
	}

	public void setXAxisName(String axisName) {
		xAxisName = axisName;
	}

	public String getYAxisName() {
		return yAxisName;
	}

	public void setYAxisName(String axisName) {
		yAxisName = axisName;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getJsPath() {
		return jsPath;
	}

	public void setJsPath(String jsPath) {
		this.jsPath = jsPath;
	}

	public String getSwfPath() {
		return swfPath;
	}

	public void setSwfPath(String swfPath) {
		this.swfPath = swfPath;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}

	public String getMyChartName() {
		return myChartName;
	}

	public void setMyChartName(String myChartName) {
		this.myChartName = myChartName;
	}

	public List<Map<Object, Object>> getOneDimensionsList() {
		return oneDimensionsList;
	}

	public void setOneDimensionsList(List<Map<Object, Object>> oneDimensionsList) {
		this.oneDimensionsList = oneDimensionsList;
	}

	public Map<Object, Map<Object, Object>> getManyDimensionsMap() {
		return manyDimensionsMap;
	}

	public void setManyDimensionsMap(
			Map<Object, Map<Object, Object>> manyDimensionsMap) {
		this.manyDimensionsMap = manyDimensionsMap;
	}

	public List<String> getXAxisNameList() {
		return xAxisNameList;
	}

	public void setXAxisNameList(List<String> axisNameList) {
		xAxisNameList = axisNameList;
	}

	public boolean isBenchmark() {
		return benchmark;
	}

	public void setBenchmark(boolean benchmark) {
		this.benchmark = benchmark;
	}

	public String getBenchmarkName() {
		return benchmarkName;
	}

	public void setBenchmarkName(String benchmarkName) {
		this.benchmarkName = benchmarkName;
	}

	public Object getBenchmarkValue() {
		return benchmarkValue;
	}

	public void setBenchmarkValue(Object benchmarkValue) {
		this.benchmarkValue = benchmarkValue; 
	}
	// ================get/set方法end============
}