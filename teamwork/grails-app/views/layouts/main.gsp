<!DOCTYPE html>
<html>
  <head>
    <title><g:layoutTitle default="Grails" /></title>
    <link rel="stylesheet" href="${createLinkTo(dir: 'css', file:'teamwork.css')}"/>
    <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
  <g:layoutHead />
  <g:javascript library="application" />
</head>
<body>
  <div id="header">
    <shiro:isLoggedIn>
      <div id="profileActions">
<span class="signout">
          <g:link controller="auth" action="signOut">Sign out</g:link>
        </span>
      </div>
    </shiro:isLoggedIn>
    <h1>
      <g:link controller="home">Teamwork</g:link>
    </h1>
  </div>
<shiro:isLoggedIn>
  <div id="navigationcontainer">
    <span id="navigation">
      <g:link controller="home" class="navigationitem">Home</g:link>
    </span>
  </div>
</shiro:isLoggedIn>

<g:layoutBody />
</body>
</html>