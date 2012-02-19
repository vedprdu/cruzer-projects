import org.apache.shiro.authc.UnknownAccountException
import org.apache.shiro.authc.SimpleAccount
import org.apache.shiro.authc.IncorrectCredentialsException
import app.User

class TeamworkRealm {
    
    static authTokenClass = org.apache.shiro.authc.UsernamePasswordToken
    def credentialMatcher

    def authenticate(authToken) 
    {
        def user = User.findByUsername(authToken.username)
        if (!user) 
        {
            throw new UnknownAccountException("No account found for user [${authToken.username}]")
        }
            
        def account = new SimpleAccount(user.username, user.password, "TeamworkRealm")
        if (!credentialMatcher.doCredentialsMatch(authToken, account)) 
        {
            throw new IncorrectCredentialsException("Invalid password for user '${authToken.username}'")
        }
        return account
    }

    def hasRole(principal, roleName) {
        def users = User.withCriteria 
        {
            eq( 'username', principal )
            role 
            {
                eq( 'name', roleName )
            }
        }
        return users.size() > 0
    }

    def isPermitted(principal, permission) {
        return true;
    }
}
