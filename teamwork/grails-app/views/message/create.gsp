<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Post Message</title>
  </head>
  <body>
    
  <g:if test="${flash.toUser}">
    <div id="userMessage" class="info">
      ${flash.toUser}
    </div>
  </g:if>
  <g:hasErrors bean="${message}">
    <div class="validationerror">
      <g:renderErrors bean="${message}" as="list"/>
    </div>
  </g:hasErrors>

  <g:form action="save" class="inputform">
    <fieldset>
      <dl>
        <dt>Title
        <span class="requiredfield">required</span>
        </dt>
        <dd><g:textField name="title" value="${message.title}"
                         size="35" class="largeinput"/></dd>
        <dt>Message detail
        <span class="requiredfield">required</span>
        </dt>
        <dd><g:textArea name="detail" value="${message.detail}"
                        cols="40" rows="10"/></dd>
      </dl>
    </fieldset>
    <g:submitButton name="Save" value="Save"/>
    |
    <g:link action="create">Cancel</g:link>
  </g:form>
</body>
</html>
