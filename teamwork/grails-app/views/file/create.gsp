<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Post File</title>
  </head>
  <body>
  <g:hasErrors bean="${file}">
    <div class="validationerror">
      <g:renderErrors bean="${file}" as="list"/>
    </div>
  </g:hasErrors>
  <g:form action="save" method="post" enctype="multipart/form-data" class="inputform">
    <fieldset>
      <dl>
        <dt>Title <span class="requiredfield">required</span></dt>
        <dd><g:textField name="name" value="${file.name}" size="35" class="largeinput"/></dd>
        <dt>File <span class="requiredfield">required</span></dt>
        <dd><input type="file" name="fileData.data"/></dd>
        <dt>File description <span class="requiredfield">required
        </span></dt>
        <dd><g:textArea name="description" value="${file.description}" cols="40" rows="10"/></dd>
      </dl>
    </fieldset>
    <g:submitButton name="Save" value="Save"/> |
    <g:link controller="home">Cancel</g:link>
  </g:form>
</body>
</html>
