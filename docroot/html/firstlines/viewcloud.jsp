<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<%-- <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %> --%>
<portlet:defineObjects />


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2>Results</h2>
<% 
int resultSize = Integer.parseInt(renderRequest.getParameter("resultSize"));
for(int i=0;i<resultSize;i++)  {
%> 
	<h4> <%=renderRequest.getParameter("title"+i) %></h4>
	<p> <%=renderRequest.getParameter("result"+i) %> </p>
	<p> File Count:<%=renderRequest.getParameter("filecounts"+i) %> </p>
	<br/>
<% 
} 
%>

<script src="<%=request.getContextPath()%>/js/d3.js"></script>
<script src="<%=request.getContextPath()%>/js/d3.layout.cloud.js"></script>
<script>
  var fill = d3.scale.category20();

  d3.layout.cloud().size([300, 300])
      .words([
        "Hello", "world", "normally", "you", "want", "more", "words",
        "than", "this"].map(function(d) {
        return {text: d, size: 10 + Math.random() * 90};
      }))
      .padding(5)
      .rotate(function() { return ~~(Math.random() * 2) * 90; })
      .font("Impact")
      .fontSize(function(d) { return d.size; })
      .on("end", draw)
      .start();

  function draw(words) {
    d3.select("body").append("svg")
        .attr("width", 300)
        .attr("height", 300)
      .append("g")
        .attr("transform", "translate(150,150)")
      .selectAll("text")
        .data(words)
      .enter().append("text")
        .style("font-size", function(d) { return d.size + "px"; })
        .style("font-family", "Impact")
        .style("fill", function(d, i) { return fill(i); })
        .attr("text-anchor", "middle")
        .attr("transform", function(d) {
          return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
        })
        .text(function(d) { return d.text; });
  }
</script>