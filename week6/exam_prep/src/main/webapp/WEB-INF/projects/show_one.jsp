<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/general/taglibs.jspf" %>
<general:layout>
    <projects:details/>
    <div class="container">
        <a href="/projects/${project.id}/tasks">See tasks!</a>
    </div>
</general:layout>